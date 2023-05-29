package com.sychev.bankclient.ui.screen.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.sychev.bankclient.utils.collectAsEffect

@Composable
fun RegistrationScreen(
    viewModel: RegistrationViewModel,
    goToLogin: () -> Unit,
    onAuthSuccess: () -> Unit,
) {

    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val passwordVisible = remember { mutableStateOf(false) }

    viewModel.registerSuccessEventStream.collectAsEffect(block = {
        onAuthSuccess.invoke()
    })

    val isRegisterButtonEnabled = remember {
        mutableStateOf(false)
    }.also {
        it.value = email.value.isNotEmpty() && password.value.isNotEmpty()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Spacer(modifier = Modifier.height(84.dp))
        Text(text = "Register new account", color = MaterialTheme.colors.secondary)
        Spacer(modifier = Modifier.height(32.dp))


        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            value = email.value,
            onValueChange = {
                email.value = it
            },
            label = { Text(text = "Email") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = MaterialTheme.colors.onPrimary,
                focusedBorderColor = MaterialTheme.colors.secondary,
                unfocusedBorderColor = MaterialTheme.colors.onSurface,
                focusedLabelColor = MaterialTheme.colors.secondary,
                unfocusedLabelColor = MaterialTheme.colors.onSurface,
                cursorColor = MaterialTheme.colors.secondary,
            ),
            textStyle = TextStyle(fontSize = TextUnit(16f, TextUnitType.Sp)),
            singleLine = true,
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            value = password.value,
            onValueChange = {
                password.value = it
            },
            label = { Text(text = "Password") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = MaterialTheme.colors.onPrimary,
                focusedBorderColor = MaterialTheme.colors.secondary,
                unfocusedBorderColor = MaterialTheme.colors.onSurface,
                focusedLabelColor = MaterialTheme.colors.secondary,
                unfocusedLabelColor = MaterialTheme.colors.onSurface,
                cursorColor = MaterialTheme.colors.secondary,
            ),
            textStyle = TextStyle(fontSize = TextUnit(16f, TextUnitType.Sp)),
            singleLine = true,
            visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (passwordVisible.value)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff
                IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                    Icon(imageVector = image, null)
                }
            }
        )

        Spacer(modifier = Modifier.height(84.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = {
                viewModel.registerUser(
                    email = email.value,
                    password = password.value
                )
            },
            enabled = isRegisterButtonEnabled.value,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.secondary,
                contentColor = MaterialTheme.colors.onSecondary,
                disabledBackgroundColor = MaterialTheme.colors.secondary.copy(alpha = 0.8f),
                disabledContentColor = MaterialTheme.colors.onSecondary.copy(alpha = 0.8f),
            )
        ) {
            Text(
                modifier = Modifier.padding(4.dp),
                text = "Create new account",
                fontSize = TextUnit(20f, TextUnitType.Sp),
            )
        }
        Spacer(modifier = Modifier.height(4.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Already have an account?",
                color = MaterialTheme.colors.onSurface,
                fontSize = TextUnit(15f, TextUnitType.Sp),
            )
            TextButton(onClick = goToLogin) {
                Text(
                    text = "Log in.",
                    color = MaterialTheme.colors.secondary,
                    fontSize = TextUnit(15f, TextUnitType.Sp),
                )
            }
        }
    }

}