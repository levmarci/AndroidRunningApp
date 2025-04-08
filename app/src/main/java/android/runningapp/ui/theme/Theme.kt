package android.runningapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFA87800),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFF665100),
    onPrimaryContainer = Color(0xFFFFE08F),

    secondary = Color(0xFFECC24C),
    onSecondary = Color(0xFF000000),
    secondaryContainer = Color(0xFF4A3C00),
    onSecondaryContainer = Color(0xFFFFECB2),

    tertiary = Color(0xFFFFFBFE),
    onTertiary = Color(0xFF000000),

    background = Color(0xFF000000),
    onBackground = Color(0xFFECECEC),

    surface = Color(0xFF1A1A1A),
    onSurface = Color(0xFFECECEC),

    surfaceVariant = Color(0xFF2E2E2E),
    onSurfaceVariant = Color(0xFFBBBBBB),

    outline = Color(0xFF999999),
    inverseSurface = Color(0xFFF5F5F5),
    inverseOnSurface = Color(0xFF1A1A1A),
    inversePrimary = Color(0xFFE7A825)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFA1B2A8),
    onPrimary = Color(0xFF000000),

    primaryContainer = Color(0xFFB2C8B4),
    onPrimaryContainer = Color(0xFF000000),

    secondary = Color(0xFFD9AC17),
    onSecondary = Color(0xFF000000),

    secondaryContainer = Color(0xFFE0E4D7),
    onSecondaryContainer = Color(0xFF000000),

    tertiary = Color(0xFFF0F4E1),
    onTertiary = Color(0xFF000000),

    background = Color(0xFFF0F4E1),
    onBackground = Color(0xFF1C1B1F),

    surface = Color(0xFFE0E4D7),
    onSurface = Color(0xFF000000),

    surfaceVariant = Color(0xFFCED8C9),
    onSurfaceVariant = Color(0xFF2B2B2B),

    outline = Color(0xFF8A8A8A),
    inverseSurface = Color(0xFF2C2C2C),
    inverseOnSurface = Color(0xFFF5F5F5),
    inversePrimary = Color(0xFF789C84)
)


@Composable
fun RunningAppTheme(
    darkTheme: Boolean,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}