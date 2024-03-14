package io.github.gaaabliz.kliz.compose.android.preview

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import io.github.gaaabliz.kliz.compose.common.ui.input.LizTextfield

@Preview
@Composable
fun LizTextFieldPreview() {
    var text by remember { mutableStateOf("") }
    MaterialTheme {
        LizTextfield(
            value = text,
            onValueChange = { newText, _ -> text = newText }
        )
    }
}