package dmitriy.losev.core.core.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    titleMedium = TextStyle(
        fontSize = 18.sp,
        fontStyle = FontStyle.Italic,
        fontFamily = FontFamily.SansSerif
    ),
    titleSmall = TextStyle(
        fontSize = 16.sp,
        fontStyle = FontStyle.Normal,
        fontFamily = FontFamily.SansSerif
    ),
    bodySmall = TextStyle(
        fontSize = 14.sp,
        fontStyle = FontStyle.Normal,
        fontFamily = FontFamily.SansSerif,
    ),
    headlineLarge = TextStyle(
        fontSize = 40.sp,
        fontWeight = FontWeight.W800,
        shadow = Shadow(
            Color.Red,
            offset = Offset(x = 5f, y = 5f),
            blurRadius = Float.fromBits(5)
        ),
        textDecoration = TextDecoration.Underline
    ),
    headlineSmall = TextStyle(
        fontSize = 18.sp,
        fontStyle = FontStyle.Italic,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.W600
    )
)