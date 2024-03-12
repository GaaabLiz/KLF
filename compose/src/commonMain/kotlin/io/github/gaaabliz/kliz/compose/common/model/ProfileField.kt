package io.github.gaaabliz.kliz.compose.common.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

@Suppress("MemberVisibilityCanBePrivate")
class ProfileField(
    val type: ProfileFieldType,
    private val initialValue: String?,
    val isImmutable : Boolean = false,
    val isTextArea : Boolean = false,
) {

    private var fieldValue by mutableStateOf(initialValue)
    private var isInError by mutableStateOf(false)
    private var errorText by mutableStateOf("")
    private var isEnabled by mutableStateOf(true)

    fun handleValueChange(newValue: String, isInLength: Boolean) {
        if (isInLength) {
            fieldValue = newValue
            isInError = false
        }
    }

    fun isChanged() = initialValue != fieldValue
    fun restoreDefaultValue() = run { fieldValue = initialValue }
    fun update(newValue : String) = run { fieldValue = newValue }
    fun getValue() = fieldValue ?: ""
    fun getErrorStatus() = isInError
    fun setErrorStatus(isInError : Boolean, errorText : String? = null) = run {
        this.isInError = isInError
        this.errorText = errorText.toString()
    }
    fun getTextError() = errorText
    fun getEnabledStatus() = kotlin.run { if(isImmutable) false else isEnabled }
    fun setEnabledStatus(isEnabled : Boolean) = run { if(!isImmutable) this.isEnabled = isEnabled }

    fun checkAndSetError(condition : Boolean, description : String) {
        if(isImmutable) return
        if(condition) setErrorStatus(true, description)
        else setErrorStatus(false)
    }

    fun validate() {
        if(isImmutable) return
        when(type) {
            is ProfileFieldType.ID -> {}
            is ProfileFieldType.NAME -> {
                checkAndSetError(
                    condition = (fieldValue?.length ?: 0) > type.minLength!!,
                    description = "Il nome deve essere di almeno ${type.minLength} caratteri"
                )
            }
            is ProfileFieldType.SURNAME -> {
                checkAndSetError(
                    condition = (fieldValue?.length ?: 0) > type.minLength!!,
                    description = "Il nome deve essere di almeno ${type.minLength} caratteri"
                )
            }
            is ProfileFieldType.PHONE -> TODO()
            is ProfileFieldType.EMAIL -> TODO()
            is ProfileFieldType.PASSWORD -> TODO()
        }
    }

}