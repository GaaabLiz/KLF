package io.github.gaaabliz.kliz.compose.android.preview

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.github.gaaabliz.kliz.compose.common.ui.card.LinkCard
import io.github.gaaabliz.kliz.compose.common.ui.scaffold.LizTopAppBar

@Preview
@Composable
fun LizTopAppBarPreview() {
    MaterialTheme {
        LizTopAppBar("Title")
    }
}