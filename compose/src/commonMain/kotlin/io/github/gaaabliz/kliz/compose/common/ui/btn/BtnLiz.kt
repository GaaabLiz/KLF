package io.github.gaaabliz.kliz.compose.common.ui.btn

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun BtnLiz1(
    text: String,
    imageVectorIcon: ImageVector? = null,
    enabled: Boolean = true,
    paddingH: Double = 0.0,
    paddingV: Double = 0.0,
    size : Dp = 200.dp,
    colors : ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.White,
        disabledBackgroundColor = Color.LightGray,
        disabledContentColor = Color.DarkGray,
    ),
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .width(size)
            .height(50.dp),
        onClick = onClick,
        enabled = enabled,
        colors = colors,
    ) {
        if(imageVectorIcon != null) {
            Icon(
                imageVectorIcon,
                contentDescription = "Button :\"$text\"",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        }
        Text(text = text, modifier = Modifier.padding(paddingH.dp, paddingV.dp),
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
fun BtnLiz1NoSize(
    text: String,
    imageVectorIcon: ImageVector? = null,
    enabled: Boolean = true,
    paddingH: Double = 0.0,
    paddingV: Double = 0.0,
    colors : ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.White,
        disabledBackgroundColor = Color.LightGray,
        disabledContentColor = Color.DarkGray,
    ),
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        colors = colors,
    ) {
        if(imageVectorIcon != null) {
            Icon(
                imageVectorIcon,
                contentDescription = "Button :\"$text\"",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        }
        Text(text = text, modifier = Modifier.padding(paddingH.dp, paddingV.dp), fontWeight = FontWeight.Bold)
    }
}