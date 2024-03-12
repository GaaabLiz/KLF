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
    val TIME_FORMATTER_THREE_SECTION : DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    val LOCAL_DATE_TIME_FORMATTER : DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")

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
    fun getSimpleStringTimeFromDate(date : Date) : String = BASIC_TIME_FORMAT.format(date)

    /** Get american date string from java Date */
    fun getAmericanStringDateFromDate(date : Date) : String = AMERICAN_DATE_FORMAT.format(date)

    fun subtractDaysFromDate(inputDate : Date, days : Int) : Date {
        val date = convertDateToLocalDate(inputDate)
        val newLocalDate = date?.minusDays(days.toLong())
        return convertToDateViaInstant(newLocalDate!!)!!
    }

    fun convertAmericanDateStringToItalian(string : String) : String {
        val arr = string.split("-")
        return arr[2] + "-" + arr[1] + "-" + arr[0]
    }

    fun convertItalianDateStringToAmerican(string : String) : String {
        val arr = string.split("-")
        return arr[2] + "-" + arr[1] + "-" + arr[0]
    }

    fun convertToDateViaInstant(dateToConvert: LocalDate): Date? {
        return Date.from(
            dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant()
        )
    }

    fun generateRandomDateBetween(startInclusive: Date, endExclusive: Date): Date {
        val startMillis = startInclusive.time
        val endMillis = endExclusive.time
        val randomMillisSinceEpoch: Long = ThreadLocalRandom
            .current()
            .nextLong(startMillis, endMillis)
        return Date(randomMillisSinceEpoch)
    }


    fun getOrarioToStringAmPm(): String {
        val tempAmPn: String
        val time: LocalTime = LocalTime.now()
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        val stringWithoutAmPm = time.format(formatter)
        val arrayOfHourMinute = stringWithoutAmPm.split(':')
        tempAmPn = if(arrayOfHourMinute[0] < 13.toString()) {
            " AM"
        } else {
            " PM"
        }
        val defString = java.lang.StringBuilder()
        assert(arrayOfHourMinute.size == 2)
        val hours = arrayOfHourMinute[0]
        val min = arrayOfHourMinute[1]
        defString.append(hours)
        defString.append(":")
        defString.append(min)
        defString.append(tempAmPn)
        return defString.toString()
    }

    fun getDateObjectFromItaString(date: String): Date = ITALIAN_DATE_FORMAT.parse(date)
    fun getDateObjectFromUsaString(date: String): Date = AMERICAN_DATE_FORMAT.parse(date)

    fun convertDateToLocalDate(dateToConvert: Date): LocalDate? {
        return dateToConvert.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
    }

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

    fun getZonedNowTime(): ZonedDateTime {
        val z = ZoneId.of("Europe/Paris")
        return ZonedDateTime.now(z)
    }


    /*
    * fun getCurrentTime() : String {
        val time = LocalDateTime.now()
        return "${time.hour}:${time.minute}:${time.second}"
    }

    fun calcolateTimeElapsedFromString(timeStarted : String, timeEnded : String) : LocalTime {
        val timeStartedSplit = timeStarted.split(":")
        val timeStartedObject = LocalTime.of(timeStartedSplit[0].toInt(), timeStartedSplit[1].toInt(), timeStartedSplit[2].toInt())
        val timeEndedSplit = timeEnded.split(":")
        val timeEndedObject = LocalTime.of(timeEndedSplit[0].toInt(), timeEndedSplit[1].toInt(), timeEndedSplit[2].toInt())
        val timeElapsed = timeEndedObject.minusHours(timeStartedObject.hour.toLong()).minusMinutes(timeStartedObject.minute.toLong()).minusSeconds(timeStartedObject.second.toLong())
        return timeElapsed
    }
    * */

}
