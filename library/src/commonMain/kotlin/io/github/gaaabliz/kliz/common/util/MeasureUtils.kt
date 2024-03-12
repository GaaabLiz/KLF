package io.github.gaaabliz.kliz.common.util

import java.lang.NumberFormatException

object MeasureUtils {

    private const val IRRIGATION_UNIT = "l/s"
    private const val TEMPERATURE_UNIT = "°C"
    private const val BRIGHTNESS_UNIT = "lux"
    private const val HUMIDITY_UNIT = "g/m^3"
    private const val VALUE_TEMPERATURE_MIN = 0
    private const val VALUE_TEMPERATURE_MAX = 50
    private const val VALUE_LIGHT_MAX = 1000
    private const val VALUE_LIGHT_MIN = 0
    private const val VALUE_HUMIDITY_MIN = 0
    private const val VALUE_HUMIDITY_MAX = 50
    private const val VALUE_IRRIGATION_MAX = 100
    private const val VALUE_IRRIGATION_MIN = 0

    fun convertMsecToSec(msec: Long): Long = (msec / 1000) % 60
    fun convertMillisecToSec(millisec: Long): Long = millisec / 1000
    fun convertMetersToKm(metri: Number): Number = (metri.toDouble()) / 1000
    fun convertKmToMeters(km: Number): Number = (km.toDouble()) * 1000

    fun isValidTemperatureValue(value: String): Boolean {
        return try {
            val valueInt = value.toInt()
            valueInt in VALUE_TEMPERATURE_MIN..VALUE_TEMPERATURE_MAX
        } catch(e: NumberFormatException) {
            false
        }
    }

    fun isValidLightValue(value: String): Boolean {
        return try {
            val valueInt = value.toInt()
            valueInt in VALUE_LIGHT_MIN..VALUE_LIGHT_MAX
        } catch(e: NumberFormatException) {
            false
        }
    }

    fun isValidHumidityValue(value: String): Boolean {
        return try {
            val valueInt = value.toInt()
            valueInt in VALUE_HUMIDITY_MIN..VALUE_HUMIDITY_MAX
        } catch(e: NumberFormatException) {
            false
        }
    }

    fun isValidIrrigationValue(value: String): Boolean {
        return try {
            val valueInt = value.toInt()
            valueInt in VALUE_IRRIGATION_MIN..VALUE_IRRIGATION_MAX
        } catch(e: NumberFormatException) {
            false
        }
    }

}