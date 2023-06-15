package com.sychev.bankclient.ui.screen.pin_code

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.sychev.shared.backend.models.base.ResultSuccess
import kotlinx.coroutines.launch

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PinCodeScreen(
    viewModel: PinCodeViewModel,
    onVerificationSuccess: () -> Unit,
    onVerificationFailure: () -> Unit,
) {

    val initialSavedPin = viewModel.getSavedPin()
    val firstEnteredPin = remember { mutableStateOf("") }
    val pinValueActualText = remember {
        mutableStateOf("")
    }
    val pinValueHiddenText = pinValueActualText.value.map { "*" }.joinToString(" ")
    val pinError = viewModel.showError.collectAsState()
    val scope = rememberCoroutineScope()
    val screenState = remember {
        mutableStateOf(
            when {
                initialSavedPin.isEmpty() && firstEnteredPin.value.isNotEmpty() ->
                    PinCodeScreenState.RepeatPin

                initialSavedPin.isEmpty() ->
                    PinCodeScreenState.CreatePin

                else ->
                    PinCodeScreenState.EnterPin
            }
        )
    }
    if (pinValueActualText.value.length == 4) {
        when (screenState.value) {
            PinCodeScreenState.EnterPin -> {
                if (pinValueActualText.value != initialSavedPin) {
                    viewModel.onEnteredWrongPin()
                    pinValueActualText.value = ""
                } else {
                    pinValueActualText.value = ""
                    LaunchedEffect(key1 = Unit, block = {
                        scope.launch {
                            val isTokenValid = viewModel.validateToken() is ResultSuccess
                            if (isTokenValid) {
                                onVerificationSuccess.invoke()
                            } else {
                                onVerificationFailure.invoke()
                            }
                        }
                    })

                }
            }

            PinCodeScreenState.RepeatPin -> {
                if (firstEnteredPin.value != pinValueActualText.value) {
                    firstEnteredPin.value = ""
                    pinValueActualText.value = ""
                    screenState.value = PinCodeScreenState.CreatePin
                    viewModel.onRepeatWrong()
                } else {
                    viewModel.saveNewPin(pinValueActualText.value)
                    firstEnteredPin.value = ""
                    pinValueActualText.value = ""
                    onVerificationSuccess.invoke()
                }
            }

            PinCodeScreenState.CreatePin -> {
                firstEnteredPin.value = pinValueActualText.value
                pinValueActualText.value = ""
                screenState.value = PinCodeScreenState.RepeatPin
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.width(1.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = screenState.value.subText,
                color = MaterialTheme.colors.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = pinValueHiddenText, color = MaterialTheme.colors.onSurface
            )
            val outlineColor = if (pinError.value) {
                MaterialTheme.colors.error
            } else {
                MaterialTheme.colors.onSurface
            }
            Box(
                modifier = Modifier
                    .height(4.dp)
                    .width(124.dp)
                    .border(2.dp, outlineColor, CircleShape)
                    .background(outlineColor)
            )
        }
        FlowRow(
            horizontalArrangement = Arrangement.Center,
            maxItemsInEachRow = 3,
        ) {
            repeat(10) { num ->
                val buttonNumText = if (num == 9) "0" else "${num + 1}"
                Button(
                    modifier = Modifier
                        .size(84.dp)
                        .padding(12.dp)
                        .weight(1f),
                    onClick = {
                        if (pinValueActualText.value.length < 4) {
                            pinValueActualText.value = pinValueActualText.value + buttonNumText
                        }
                    },
                    shape = CircleShape,
                ) {
                    Text(
                        text = buttonNumText,
                        fontSize = TextUnit(26f, TextUnitType.Sp)
                    )
                }
            }
            Button(
                modifier = Modifier
                    .size(84.dp)
                    .padding(12.dp)
                    .weight(2f),
                onClick = {
                    try {
                        pinValueActualText.value =
                            pinValueActualText.value.substring(
                                0,
                                pinValueActualText.value.length - 1
                            )
                    } catch (e: Exception) {
                        //It is ok, pin value already empty
                    }
                },
                shape = CircleShape,
            ) {
                Icon(Icons.Filled.Clear, null)
            }
        }
    }

}

enum class PinCodeScreenState(val subText: String) {
    CreatePin("Create your pin"),
    RepeatPin("Repeat entered pin"),
    EnterPin("Enter your pin"),
}