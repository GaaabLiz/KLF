@file:Suppress("NOTHING_TO_INLINE")
package io.github.gaaabliz.kliz.common.base

import java.io.File
import java.util.*
import java.util.regex.Pattern
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract


inline val randomUUID: String get() = UUID.randomUUID().toString()
inline val currentTimeMillis: Long get() = System.currentTimeMillis()
inline val currentThreadName: String get() = Thread.currentThread().name
inline val currentThreadID: Long get() = Thread.currentThread().id
inline val emptyString : String get() = ""
inline fun String?.safeToBoolean(default: Boolean = false): Boolean = runCatching(::toBoolean).getOrDefault(default)
inline fun String?.safeToInt(default: Int = 0): Int = orEmpty().runCatching(String::toInt).getOrDefault(default)
inline fun String?.safeToLong(default: Long = 0L): Long = orEmpty().runCatching(String::toLong).getOrDefault(default)
inline fun String?.safeToFloat(default: Float = 0f): Float = orEmpty().runCatching(String::toFloat).getOrDefault(default)
inline fun String?.safeToDouble(default: Double = 0.0): Double = orEmpty().runCatching(String::toDouble).getOrDefault(default)
fun File.notExists(): Boolean = exists().not()


fun CharSequence.isValidFilename(): Boolean {
    val filenameRegex = Pattern.compile("[\\\\/:*?\"<>|\\x01-\\x1F\\x7F]", Pattern.CASE_INSENSITIVE)
    return !filenameRegex.matcher(this).find() && "." != this && ".." != this
}

fun CharSequence.isValidUrl(): Boolean {
    val urlRegex = Pattern.compile("^(https?|ftp)://.*$", Pattern.CASE_INSENSITIVE)
    return urlRegex.matcher(this).find()
}

@OptIn(ExperimentalContracts::class)
inline fun CharSequence?.isNotNullOrEmpty(): Boolean {
    contract {
        returns(true) implies (this@isNotNullOrEmpty != null)
    }
    return this.isNullOrEmpty().not()
}

inline fun checkAllTrue (vararg booleans : Boolean) : Boolean {
    for (boolean in booleans) {
        if(!boolean) { return false }
    }
    return true
}

inline fun <T> checkIfNullExist(vararg elements: T): Boolean {
    elements.forEach {
        if(it == null) return true
    }
    return false
}


/*
// TODO GABRI: da sistemare

/**
 * Prende ora, minuti e secondi da un LocalDateTime e li converte in un formato HH:mm:ss
 * @receiver LocalDateTime
 * @return String
 */
inline fun LocalDateTime.toProjectTime() : String {
    val hour = if(this.hour < 10) "0${this.hour}" else "${this.hour}"
    val minute = if(this.minute < 10) "0${this.minute}" else "${this.minute}"
    val second = if(this.second < 10) "0${this.second}" else "${this.second}"
    return "$hour:$minute:$second"
}

inline fun LocalTime.toStringTime(): String {
    val hour = if(this.hour < 10) "0${this.hour}" else "${this.hour}"
    val minute = if(this.minute < 10) "0${this.minute}" else "${this.minute}"
    val second = if(this.second < 10) "0${this.second}" else "${this.second}"
    return "$hour:$minute"
}

inline fun String.toLocalTime(): LocalTime {
    val array = this.split(":")
    return LocalTime.of(array[0].toInt(), array[1].toInt())
}

inline fun LocalDateTime.toProjectDate(): String {
    return "${this.dayOfMonth}/${this.monthValue}/${this.year}"
}

/**
 * Prende data e ora da un LocalDateTime e li converte in un formato dd/MM/yyyy HH:mm:ss
 * @receiver LocalDateTime
 * @return String
 */
inline fun LocalDateTime.toProjectDateTime(sep: String = " ") : String {
    return "${this.toProjectDate()}$sep${this.toProjectTime()}"
}

inline fun LocalDateTime.toDateTimeAndDayWeek(sep: String = " ") : String {
    return "${this.dayOfWeek}$sep${this.toProjectDate()}$sep${this.toProjectTime()}"
}

inline fun LocalDateTime.toElapsedTimeStringItaFromNow() : String {
    val currentTime = LocalDateTime.now()
    val hoursDiff = ChronoUnit.HOURS.between(this, currentTime)
    val minutesDiff = ChronoUnit.MINUTES.between(this, currentTime) % 60
    val daysDiff = ChronoUnit.DAYS.between(this, currentTime)

    return when {
        daysDiff > 0 -> {
            val remainingHours = hoursDiff - (daysDiff * 24)
            when {
                remainingHours > 0 && minutesDiff > 0 ->
                    "$daysDiff gg, $remainingHours ore e $minutesDiff min. fa"
                remainingHours > 0 ->
                    "$daysDiff gg e $remainingHours ore fa"
                minutesDiff > 0 ->
                    "$daysDiff gg e $minutesDiff min. fa"
                else ->
                    "$daysDiff gg fa"
            }
        }
        hoursDiff > 0 -> "$hoursDiff ore e $minutesDiff min. fa"
        minutesDiff > 0 -> "$minutesDiff min. fa"
        else -> "Meno di un min. fa"
    }
}

 */


