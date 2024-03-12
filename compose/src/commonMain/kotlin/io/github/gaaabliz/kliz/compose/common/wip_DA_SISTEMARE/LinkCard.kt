package io.github.gaaabliz.kliz.compose.common.wip_DA_SISTEMARE


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Web
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import io.github.gaaabliz.kliz.compose.common.theme.doveGray
import io.github.gaaabliz.kliz.compose.common.theme.titanWhite
import io.github.gaaabliz.kliz.compose.common.theme.uniupo


@Composable
fun LinkCard(
    icon : ImageVector = Icons.Default.Web,
    iconColor : Color = doveGray,
    title : String = "Titolo",
    payload : String = "https://www.google.com",
    payloadTextShown : String = payload,
    onClick : (String) -> Unit = {},
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = titanWhite,
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable {
                onClick(payload)
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                icon,
                contentDescription = "Icon of $title",
                tint = iconColor,
                modifier = Modifier
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Text(text = title, color = uniupo, style = MaterialTheme.typography.subtitle2)
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = payloadTextShown, color = Color.Black, style = MaterialTheme.typography.subtitle1)
            }
        }
    }
}