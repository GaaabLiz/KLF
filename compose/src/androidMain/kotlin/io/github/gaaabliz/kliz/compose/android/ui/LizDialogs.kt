package io.github.gaaabliz.kliz.compose.android.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import io.github.gaaabliz.kliz.compose.common.ui.btn.BtnLiz1NoSize


@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun AlertDialogYesNo(
    isVisible : Boolean = true,
    title : String = "",
    text : String = "",
    onDismissRequest : () -> Unit = {},
    onYes: () -> Unit = {},
    onNo: () -> Unit = {},

    ) {
    if(isVisible) {
        AlertDialog(
            onDismissRequest = onDismissRequest,
            title = { Text(text = title, style = MaterialTheme.typography.h6) },
            text = { Text(text = text, style = MaterialTheme.typography.body1) },
            confirmButton = { BtnLiz1NoSize(text = "Yes", onClick = onYes) },
            dismissButton = { BtnLiz1NoSize(text = "No", onClick = onNo) },
            shape = RoundedCornerShape(8.dp),
            properties = DialogProperties(dismissOnClickOutside = false),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
    }

}