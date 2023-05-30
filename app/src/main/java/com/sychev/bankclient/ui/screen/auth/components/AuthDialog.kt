package com.sychev.bankclient.ui.screen.auth.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp

@Composable
fun AuthDialog(
    modifier: Modifier = Modifier,
    title: String,
    message: String,
    isVisible: MutableState<Boolean>,
) {

    if (isVisible.value) {
        AlertDialog(
            modifier = modifier,
            onDismissRequest = {
                isVisible.value = false
            },
            title = {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = title,
                    color = MaterialTheme.colors.onPrimary,
                )
            },
            text = {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = message,
                    color = MaterialTheme.colors.onPrimary,
                )
            },
            confirmButton = {
                TextButton(
                    modifier = Modifier,
                    onClick = { isVisible.value = false },
                    contentPadding = PaddingValues(12.dp),
                ) {
                    Text(
                        text = "Confirm",
                        color = MaterialTheme.colors.secondary,
                        fontSize = TextUnit(16f, TextUnitType.Sp)
                    )
                }
            },
        )
    }


}