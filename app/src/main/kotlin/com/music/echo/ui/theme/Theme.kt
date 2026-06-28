

package sauravthakur.opm.ui.theme

import android.graphics.Bitmap
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.palette.graphics.Palette
import com.materialkolor.score.Score

val DefaultThemeColor = Color.White

@Composable
fun opmTheme(
    darkTheme: Boolean = true,
    pureBlack: Boolean = false,
    themeColor: Color = DefaultThemeColor,
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current
    
    val baseColorScheme = opmColorScheme()
    
    val colorScheme = remember(baseColorScheme, pureBlack) {
        if (pureBlack) {
            baseColorScheme.pureBlack(true)
        } else {
            baseColorScheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography, 
        content = content
    )
}

fun Bitmap.extractThemeColor(): Color {
    val colorsToPopulation = Palette.from(this)
        .maximumColorCount(8)
        .generate()
        .swatches
        .associate { it.rgb to it.population }
    val rankedColors = Score.score(colorsToPopulation)
    return Color(rankedColors.first())
}

fun Bitmap.extractGradientColors(): List<Color> {
    val extractedColors = Palette.from(this)
        .maximumColorCount(64)
        .generate()
        .swatches
        .associate { it.rgb to it.population }

    val orderedColors = Score.score(extractedColors, 2, 0xff4285f4.toInt(), true)
        .sortedByDescending { Color(it).luminance() }

    return if (orderedColors.size >= 2)
        listOf(Color(orderedColors[0]), Color(orderedColors[1]))
    else
        listOf(Color(0xFF595959), Color(0xFF0D0D0D))
}

fun ColorScheme.pureBlack(apply: Boolean) =
    if (apply) copy(
        surface = Color.Black,
        background = Color.Black,
        surfaceContainerLowest = Color.Black,
        surfaceContainerLow = Color.Black,
        surfaceContainer = Color.Black,
        surfaceContainerHigh = Color.Black,
        surfaceContainerHighest = Color.Black,
    ) else this

private fun opmColorScheme(): ColorScheme {
    val base = darkColorScheme()
    return base.copy(
        primary = Color.White,
        onPrimary = Color.Black,
        primaryContainer = Color.White,
        onPrimaryContainer = Color.Black,
        secondary = Color(0xFFE5E5E5),
        onSecondary = Color.Black,
        secondaryContainer = Color(0xFF1C1C1C),
        onSecondaryContainer = Color.White,
        tertiary = Color(0xFFBDBDBD),
        onTertiary = Color.Black,
        tertiaryContainer = Color(0xFF222222),
        onTertiaryContainer = Color.White,
        background = Color.Black,
        onBackground = Color.White,
        surface = Color.Black,
        onSurface = Color.White,
        surfaceVariant = Color(0xFF151515),
        onSurfaceVariant = Color(0xFFB9B9B9),
        surfaceContainerLowest = Color.Black,
        surfaceContainerLow = Color(0xFF050505),
        surfaceContainer = Color(0xFF0B0B0B),
        surfaceContainerHigh = Color(0xFF111111),
        surfaceContainerHighest = Color(0xFF171717),
        outline = Color(0xFF5E5E5E),
        outlineVariant = Color(0xFF2A2A2A),
        inverseSurface = Color.White,
        inverseOnSurface = Color.Black,
        inversePrimary = Color.Black,
        error = Color(0xFFFFB4AB),
        onError = Color(0xFF690005),
    )
}

private fun opmLightColorScheme(): ColorScheme {
    val base = lightColorScheme()
    return base.copy(
        primary = Color.Black,
        onPrimary = Color.White,
        primaryContainer = Color.Black,
        onPrimaryContainer = Color.White,
        secondary = Color(0xFF1A1A1A),
        onSecondary = Color.White,
        secondaryContainer = Color(0xFFE3E3E3),
        onSecondaryContainer = Color.Black,
        tertiary = Color(0xFF424242),
        onTertiary = Color.White,
        tertiaryContainer = Color(0xFFDDDDDD),
        onTertiaryContainer = Color.Black,
        background = Color.White,
        onBackground = Color.Black,
        surface = Color.White,
        onSurface = Color.Black,
        surfaceVariant = Color(0xFFEAEAEA),
        onSurfaceVariant = Color(0xFF464646),
        surfaceContainerLowest = Color.White,
        surfaceContainerLow = Color(0xFFFAFAFA),
        surfaceContainer = Color(0xFFF4F4F4),
        surfaceContainerHigh = Color(0xFFEEEEEE),
        surfaceContainerHighest = Color(0xFFE8E8E8),
        outline = Color(0xFFA1A1A1),
        outlineVariant = Color(0xFFD5D5D5),
        inverseSurface = Color.Black,
        inverseOnSurface = Color.White,
        inversePrimary = Color.White,
        error = Color(0xFFBA1A1A),
        onError = Color.White,
    )
}

val ColorSaver = object : Saver<Color, Int> {
    override fun restore(value: Int): Color = Color(value)
    override fun SaverScope.save(value: Color): Int = value.toArgb()
}
