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



