package io.github.gaaabliz.kliz.common.util

import org.apache.commons.lang3.RandomStringUtils
import org.apache.commons.lang3.StringUtils
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneOffset
import java.util.*
import java.util.concurrent.ThreadLocalRandom
import kotlin.collections.ArrayList

object DataUtils {

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
    fun convertMsecToSec(msec: Long): Long = (msec / 1000) % 60
    fun convertMillisecToSec(millisec: Long): Long = millisec / 1000
    fun convertMetersToKm(metri: Number): Number = (metri.toDouble()) / 1000
    fun convertKmToMeters(km: Number): Number = (km.toDouble()) * 1000

    fun <T, S> mapTwoList(list1 : List<T>, list2 : List<S>) : Map<T, S> {
        if(list1.size != list2.size) {
            throw IllegalArgumentException("The two lists must have the same size.")
        }
        val map = mutableMapOf<T, S>()
        for (i in list1.indices) {
            map[list1[i]] = list2[i]
        }
        return map
    }

    fun convertEnumNameToDisplayName(enumName : String) : String {
        return enumName.replace("_", " ").toLowerCase().capitalize()
    }

    fun generateRandomImageUrl(imageWidth : Int , imageHeight : Int) : String {
        val randomInt = generateRandomInt(1, 100)
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

    /**
     * Calcolate the time elapsed between two string time set in the format "HH:mm:ss"
     *
     * @param timeStarted the time started
     * @param timeEnded the time ended
     * @return a LocalTime object with the time elapsed
     */
    fun calcolateTimeElapsedFromString(timeStarted: String, timeEnded: String): LocalTime {
        val timeStartedSplit = timeStarted.split(":")
        val timeStartedObject =
            LocalTime.of(timeStartedSplit[0].toInt(), timeStartedSplit[1].toInt(), timeStartedSplit[2].toInt())
        val timeEndedSplit = timeEnded.split(":")
        val timeEndedObject =
            LocalTime.of(timeEndedSplit[0].toInt(), timeEndedSplit[1].toInt(), timeEndedSplit[2].toInt())
        return timeEndedObject.minusHours(timeStartedObject.hour.toLong())
            .minusMinutes(timeStartedObject.minute.toLong()).minusSeconds(timeStartedObject.second.toLong())
    }

    fun getSimpleInvDateString() : String {
        val now = LocalDate.now()
        return "${now.year}-${now.monthValue}-${now.dayOfMonth}"
    }

    fun getSimpleTimeString() : String {
        val now = LocalTime.now()
        return "${now.hour}:${now.minute}:${now.second}"
    }

    /**
     * Metodo per trasformare un enum convertito in stringa in un stringa leggibile nel seguente formato
     * "VARIABILE_ENUM" -> "Variabile Enum"
     * @param oldString String vecchia stringa da convertire
     * @return String la nuova stringa convertita
     */
    fun adjustEnumString(oldString: String): String {
        /* Sovrascrivo tutti gli underscore con degli spazi */
        val stringWithSpaces = oldString.replace('_', ' ')

        /* Prendo tutte le parole nella stringa e le metto in un array */
        val arrayStrings = stringWithSpaces.split(' ').toTypedArray()

        /* Metto tutte le stringhe in minuscola */
        val arrayLower = ArrayList<String>()
        arrayStrings.forEach { arrayLower.add(it.lowercase(Locale.getDefault())) }

        /* Metto la prima lettera di ogni stringa in maiuscolo */
        val arrayLowerCap = ArrayList<String>()
        arrayLower.forEach { it ->
            arrayLowerCap.add(it.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            }) }

        /* Unisco tutte le stringhe in una */
        val stringBuilder = StringBuilder()
        arrayLowerCap.forEach { stringBuilder.append(it.plus(' ')) }

        /* Rimuovo ultimo char */
        val newString = stringBuilder.toString()

        /* Ritorno la stringa */
        return StringUtils.removeEnd(newString, " ")
    }

    fun <T> addElementToImmutableList(listSource: List<T>?, elementToAdd: T): List<T?> {
        return if(listSource != null) {
            /* Aggiungo nella vecchia lista quelli sorgente */
            val oldList: MutableList<T> = ArrayList()
            listSource.forEach { oldList.add(it) }
            /* Creo nuovo lista con quelli vecchi + quello nuovo */
            val newList: MutableList<T> = ArrayList()
            newList.addAll(oldList)
            newList.add(elementToAdd)
            newList
        } else {
            listOf(elementToAdd)
        }
    }
}