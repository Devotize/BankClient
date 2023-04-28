package com.sychev.bankclient.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.sychev.bankclient.utils.isDarkThemeOn

private val LightColorPalette = lightColors(
    primary = White,
    primaryVariant = Grey300,
    secondary = Blue,
    background = Grey200,
    error = Color.Red,
    onPrimary = Grey900,
    onSecondary = White,
    onSurface = Grey700,
    onBackground = White

)

private val DayNightColorPalette = darkColors(
    primary = Grey900,
    primaryVariant = White,
    secondary = Blue,
    background = Black,
    error = Color.Red,
    onPrimary = Grey300,
    onSecondary = Black,
    onSurface = Grey300,
    onBackground = White
)

@Composable
fun BankClientTheme(content: @Composable () -> Unit) {

    val context = LocalContext.current

    val colorPalette = if (context.isDarkThemeOn()) {
        DayNightColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}