@file:Suppress("DuplicatedCode")

package io.github.gaaabliz.kliz.compose.common.wip_DA_SISTEMARE


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Adb
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp



@Composable
fun BtnLiz(
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
fun BtnLizNoSize(
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


@Composable
fun BtnDebug(
    text: String = "Testo",
    imageVectorIcon: ImageVector? = Icons.Default.Adb,
    onClick: () -> Unit = {}
) {
    BtnLiz(
        text = text,
        imageVectorIcon = imageVectorIcon,
        size = 300.dp,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Blue,
            contentColor = Color.White,
            disabledBackgroundColor = Color.LightGray,
            disabledContentColor = Color.DarkGray,
        ),
        onClick = onClick
    )
}
