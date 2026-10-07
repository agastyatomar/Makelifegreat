package com.aadi.makelifegreat.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val AadiDark = darkColorScheme(
    primary = MlgSky,
    onPrimary = MlgInk,
    primaryContainer = PrimaryContainer,
    onPrimaryContainer = OnPrimaryContainer,
    secondary = MlgMint,
    onSecondary = MlgInk,
    secondaryContainer = SecondaryContainer,
    onSecondaryContainer = OnSecondaryContainer,
    tertiary = MlgViolet,
    onTertiary = MlgInk,
    background = MlgInk,
    onBackground = MlgText,
    surface = MlgNavy,
    onSurface = MlgText,
    surfaceVariant = MlgPanel,
    onSurfaceVariant = MlgMuted,
    error = MlgDanger,
    onError = Color.White,
    errorContainer = ErrorContainer,
    onErrorContainer = OnErrorContainer,
    outline = Outline,
    outlineVariant = OutlineVariant,
)

@Composable
fun MakeLifeGreatTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = AadiDark, typography = Typography, content = content)
}
