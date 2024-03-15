@file:Suppress("RedundantNullableReturnType")

package io.github.gaaabliz.kliz.common.util

import org.apache.commons.lang3.StringUtils
import java.util.Locale

object DataUtils {

    /**
     * Map two list in a map
     * @param list1 the first list
     * @param list2 the second list
     * @return a map with the first list as key and the second list as value
     * @throws IllegalArgumentException if the two lists have different size
     */
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

    /**
     * Convert a multi-word enum string to a display name
     * @param enumName the enum string
     * @return the display name
     *
     * Example: HOME_SECONDARY -> Home Secondary
     */
    @Deprecated("Use adjustEnumString instead", ReplaceWith("adjustEnumString(enumName)"))
    fun convertEnumNameToDisplayName(enumName : String) : String {
        return enumName
            .replace("_", " ")
            .lowercase(Locale.getDefault())
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }

    /**
     * Check if all the boolean condition are true
     * @return TRUE if all are true, false otherwise
     */
    private fun checkAllTrue(vararg b: Boolean):Boolean {
        var status:Boolean = false;
        for (t in b) {
            if(!t) return false
        }
        return true
    }

    fun <T> checkNullThrow(obj: T?, name:String): T? {
        if(obj == null) {
            throw IllegalArgumentException("Object $name cannot be null")
        }
        return obj
    }

    fun <T> limitList(list: List<T>, limit: Int): List<T> {
        return if(list.size > limit) {
            list.subList(0, limit)
        } else {
            list
        }
    }

    /**
     * Convert a multi-word enum string to a display name:
     * @param oldString the enum string
     * @return the display name
     * Example: HOME_SECONDARY -> Home Secondary
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

    /**
     * Create a list with the input list and the input element.
     * If the input list is null, the method will return a list with only the input element.
     * @param listSource the input list
     * @param elementToAdd the input element
     * @return a list with the input list and the input element
     */
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

    /**
     * Convert a string to lower case and capitalize the first letter
     * @param string the input string
     * @return the string in lower case with the first letter capitalized
     */
    fun toLowerAndCap(string: String) : String {
        return string.lowercase().replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }
}