package io.github.gaaabliz.kliz.compose.common.ui.utils


import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.github.gaaabliz.kliz.compose.common.theme.doveGray


@Composable
fun ColumnCenterText(text : String = "Ciao") {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = text, style = MaterialTheme.typography.body2, color = doveGray)
    }
}
