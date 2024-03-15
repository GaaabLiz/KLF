package io.github.gaaabliz.kliz.common.model

import java.time.DayOfWeek

enum class DayOfWork(
    val code: String,
    val string: String,
    val dayOfTheWeek: DayOfWeek
) {
    LUNEDI("L", "Lunedì", DayOfWeek.MONDAY),
    MARTEDI("MA", "Martedì", DayOfWeek.TUESDAY),
    MERCOLEDI("ME", "Mercoledì", DayOfWeek.WEDNESDAY),
    GIOVEDI("G", "Giovedì", DayOfWeek.THURSDAY),
    VENERDI("V", "Venerdì", DayOfWeek.FRIDAY),
    WEEKEND("END", "Week-end", DayOfWeek.SUNDAY)
}