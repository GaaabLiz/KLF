@file:Suppress("MemberVisibilityCanBePrivate")

package io.github.gaaabliz.kliz.common.util

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.*
import java.util.concurrent.ThreadLocalRandom

/**
 * Class with utilities for time and date.
 * @author Gabliz
 */
object TimeUtils {

    val ITALIAN_DATE_FORMAT = SimpleDateFormat("dd-MM-yyyy")
    val AMERICAN_DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd")
    val BASIC_TIME_FORMAT = SimpleDateFormat("HH:mm")


    val DATE_FORMATTER_LEFT_TO_RIGHT : DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    val DATE_FORMATTER_RIGHT_TO_LEFT: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val TIME_FORMATTER_TWO_SECTION : DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    val TIME_FORMATTER_TWO_SECTION_AM_PM : DateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a")
    val TIME_FORMATTER_THREE_SECTION : DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    val LOCAL_DATE_TIME_FORMATTER : DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")

    /**
     * Get the current date/time with zone.
     * @return the current date/time with zone
     */
    fun getZonedNowTime(): ZonedDateTime {
        val z = ZoneId.of("Europe/Paris")
        return ZonedDateTime.now(z)
    }

    /**
     *  Convert a LocalDate object to a string using a custom formatter.
     *  @param date the date to convert
     *  @param formatter the formatter to use
     *  @return the date as a string
     */
    fun localDateToString(
        date: LocalDate = LocalDate.now(),
        formatter: DateTimeFormatter = DATE_FORMATTER_LEFT_TO_RIGHT
    ): String {
        return date.format(formatter)
    }

    /**
     * Convert a LocalTime object to a string using a custom formatter.
     * @param time the time to convert
     * @param formatter the formatter to use
     * @return the time as a string
     */
    fun localTimeToString(
        time: LocalTime = LocalTime.now(),
        formatter: DateTimeFormatter = TIME_FORMATTER_TWO_SECTION
    ): String {
        return time.format(formatter)
    }

    /**
     * Convert a LocalDateTime object to a string using a custom formatter.
     * @param date the date to convert
     * @param time the time to convert
     * @param formatter the formatter to use
     * @return the date and time as a string
     */
    fun localDateTimeToString(
        date: LocalDate = LocalDate.now(),
        time: LocalTime = LocalTime.now(),
        formatter: DateTimeFormatter = LOCAL_DATE_TIME_FORMATTER
    ): String {
        return "${date.format(DATE_FORMATTER_LEFT_TO_RIGHT)} ${time.format(TIME_FORMATTER_TWO_SECTION)}"
    }

    /**
     * Convert a LocalDateTime object to a string using a custom formatter.
     * @param localDateTime the date to convert
     * @param formatter the formatter to use
     * @return the date and time as a string
     */
    fun localDateTimeToString(
        localDateTime: LocalDateTime,
        formatter: DateTimeFormatter = LOCAL_DATE_TIME_FORMATTER
    ): String {
        return localDateTime.format(formatter)
    }

    /**
     * Convert a LocalDate object to a java Date object.
     * @param dateToConvert the date to convert
     * @return the date as a java Date object
     */
    fun localDateToJavaDate(dateToConvert: LocalDate): Date {
        return Date.from(
            dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant()
        )
    }

    /**
     * Convert a java Date object to a LocalDate object.
     * @param dateToConvert the date to convert
     * @return the date as a LocalDate object
     */
    fun javaDateToLocalDate(dateToConvert: Date): LocalDate {
        return dateToConvert.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
    }

    /**
     * Get the hour from a string time in the format "HH:mm:ss"
     * @param time the time to parse
     * @return the hour as a string
     */
    fun getHourFromTripleStringTime(time : String) : String {
        if(!time.contains(":")) {
            throw IllegalArgumentException("The time must be in the format HH:mm:ss")
        }
        val temp = time.split(":")
        return temp[0]
    }

    /**
     * Get the minute from a string time in the format "HH:mm:ss"
     */
    fun getMinuteFromTripleStringTime(time : String) : String {
        if(!time.contains(":")) {
            throw IllegalArgumentException("The time must be in the format HH:mm:ss")
        }
        val temp = time.split(":")
        return temp[1]
    }

    /**
     * Get the second from a string time in the format "HH:mm:ss"
     */
    fun getSecondFromTripleStringTime(time : String) : String {
        if (!time.contains(":")) {
            throw IllegalArgumentException("The time must be in the format HH:mm:ss")
        }
        val temp = time.split(":")
        return temp[2]
    }

    /** Get two-section time string from java Date */
    @Deprecated("This is the old method.")
    fun getSimpleStringTimeFromDate(date : Date) : String = BASIC_TIME_FORMAT.format(date)

    /** Get american date string from java Date */
    @Deprecated("This is the old method.")
    fun getAmericanStringDateFromDate(date : Date) : String = AMERICAN_DATE_FORMAT.format(date)

    /**
     * subtract days from a date
     * @param inputDate the date to subtract from
     * @param days the number of days to subtract
     * @return the new date
     */
    fun subtractDaysFromDate(inputDate : Date, days : Int) : Date {
        val date = javaDateToLocalDate(inputDate)
        val newLocalDate = date?.minusDays(days.toLong())
        return localDateToJavaDate(newLocalDate!!)!!
    }

    @Deprecated("This is the old method.")
    fun convertAmericanDateStringToItalian(string : String) : String {
        val arr = string.split("-")
        return arr[2] + "-" + arr[1] + "-" + arr[0]
    }

    @Deprecated("This is the old method.")
    fun convertItalianDateStringToAmerican(string : String) : String {
        val arr = string.split("-")
        return arr[2] + "-" + arr[1] + "-" + arr[0]
    }

    /**
     * Generate a random date between two dates.
     * @param startInclusive the start date
     * @param endExclusive the end date
     * @return the random date
     */
    fun genRandomJavaDate(
        startInclusive: Date = localDateToJavaDate(LocalDate.of(1970, 1, 1)),
        endExclusive: Date = localDateToJavaDate(LocalDate.of(2022, 1, 1))
    ): Date {
        val startMillis = startInclusive.time
        val endMillis = endExclusive.time
        val randomMillisSinceEpoch: Long = ThreadLocalRandom
            .current()
            .nextLong(startMillis, endMillis)
        return Date(randomMillisSinceEpoch)
    }

    /**
     * Get java date object from a string date in the italian format "dd-MM-yyyy"
     * @param date the date to convert as a string
     * @return the date as a java Date object
     */
    fun getDateObjectFromItaString(date: String): Date = ITALIAN_DATE_FORMAT.parse(date)

    /**
     * Get java date object from a string date in the american format "yyyy-MM-dd"
     * @param date the date to convert as a string
     * @return the date as a java Date object
     */
    fun getDateObjectFromUsaString(date: String): Date = AMERICAN_DATE_FORMAT.parse(date)

    /**
     * Check if a string is a valid local time.
     * @param string the string to check
     * @return true if the string is a valid local time, false otherwise
     */
    fun isValidLocalTimeString(string : String) : Boolean {
        return try {
            LocalTime.parse(string)
            true
        } catch (e: DateTimeParseException) {
            false
        } catch (e: NullPointerException) {
            false
        }
    }
}
