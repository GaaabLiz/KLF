package io.github.gaaabliz.kliz.common.model

import io.github.gaaabliz.kliz.common.util.DataUtils
import io.github.gaaabliz.kliz.common.util.GenUtils

@Deprecated(message = "", level = DeprecationLevel.WARNING)
data class Log(
    val id: String = GenUtils.generateRandomId(20),
    val date: String,
    val time: String,
    val type: LogType,
    val message: String
)
