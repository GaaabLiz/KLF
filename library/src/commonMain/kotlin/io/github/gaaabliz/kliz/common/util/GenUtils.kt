@file:Suppress("MemberVisibilityCanBePrivate")

package io.github.gaaabliz.kliz.common.util

import org.apache.commons.lang3.RandomStringUtils
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.concurrent.ThreadLocalRandom

object GenUtils {

    fun generateRandomId(length: Int): String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return(1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    fun generateRandomStringApache( length: Int ) : String = RandomStringUtils.randomAlphanumeric(length)
    fun generateRandomBoolean() : Boolean = (0..1).random() == 1
    fun generateRandomInt(min: Int, max: Int) : Int = (min..max).random()
    fun generateRandomLong(min: Long, max: Long) : Long = (min..max).random()

    private fun generateRandomDoubleBetween(start: Double, end: Double): Double {
        return ThreadLocalRandom.current().nextDouble(start, end)
    }

    fun generateRandomImageUrl(imageWidth : Int , imageHeight : Int) : String {
        val randomInt = GenUtils.generateRandomInt(1, 100)
        return "https://source.unsplash.com/random/${imageWidth}x${imageHeight}?sig=${randomInt}"
    }

    fun generateRandomLocalDate() : LocalDate {
        val minDay = LocalDate.of(1900, 1, 1).toEpochDay()
        val maxDay = LocalDate.of(2021, 1, 1).toEpochDay()
        val randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay)
        return LocalDate.ofEpochDay(randomDay)
    }

    fun generateRandomLocalDateTime() : LocalDateTime {
        val minDay = LocalDateTime.of(1900, 1, 1, 0, 0, 0).toEpochSecond(ZoneOffset.UTC)
        val maxDay = LocalDateTime.of(2021, 1, 1, 0, 0, 0).toEpochSecond(ZoneOffset.UTC)
        val randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay)
        return LocalDateTime.ofEpochSecond(randomDay, 0, ZoneOffset.UTC)
    }
}