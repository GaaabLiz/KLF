package io.github.gaaabliz.kliz.compose.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import io.github.gaaabliz.kliz.compose.model.AnnotatedStringParam

object ComposeUtils {

    fun generateAnnotatedString(vararg params : AnnotatedStringParam) : AnnotatedString {
        return buildAnnotatedString {
            params.forEach { param ->
                if(param.style == null) {
                    append(param.text)
                } else {
                    withStyle(param.style) {
                        append(param.text)
                    }
                }
            }
        }
    }
}