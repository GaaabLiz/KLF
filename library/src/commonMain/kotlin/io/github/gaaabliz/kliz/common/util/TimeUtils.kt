package io.github.gaaabliz.kliz.common.util

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.*
import java.util.concurrent.ThreadLocalRandom

/**
 * Classe per la gestione, la formattazione e la creazione di date e orari.
 * @author Gabriele Lizzos
 */
object TimeUtils {

    val ITALIAN_DATE_FORMAT = SimpleDateFormat("dd-MM-yyyy")
    val AMERICAN_DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd")
    val BASIC_TIME_FORMAT = SimpleDateFormat("HH:mm")

    fun getDataToString(): String {
        val date: LocalDate = LocalDate.now()
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return date.format(formatter)
    }

    fun getHourTimeStringFromTripleStringTime(time : String) : String {
        val temp = time.split(":")
        return temp[0]
    }

    fun getSimpleStringTimeFromDate(date : Date) : String = BASIC_TIME_FORMAT.format(date)
    fun getAmericanStringDateFromDate(date : Date) : String = AMERICAN_DATE_FORMAT.format(date)

    fun subtractDaysFromDate(inputDate : Date, days : Int) : Date {
        val date = convertDateToLocalDate(inputDate)
        val newLocalDate = date?.minusDays(days.toLong())
        return convertToDateViaInstant(newLocalDate!!)!!
    }

    fun getCompleteOrarioToString(): String {
        val time: LocalTime = LocalTime.now()
        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
        return time.format(formatter)
    }

    fun getOrarioToString(): String {
        val time: LocalTime = LocalTime.now()
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        return time.format(formatter)
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
