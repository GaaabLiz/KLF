package io.github.gaaabliz.kliz.common.util

import io.github.gaaabliz.kliz.common.model.ValidationResult

object Validator {

    private val emailAddressRegex = Regex(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

    suspend fun handle(list : List<ValidationResult>, onAllSuccess: suspend () -> Unit, onFirstFailure: (error: String) -> Unit) {
        list.forEach {
            if(!it.successful) {
                onFirstFailure(it.errorMessage.toString())
                return
            }
        }
        onAllSuccess()
    }

    fun email(email : String? = null) : ValidationResult {
        if(email == null) return ValidationResult(false, "Email non inserita")
        if(email.isBlank()) return ValidationResult(false, "Email non inserita")
        if(!email.matches(emailAddressRegex)) return ValidationResult(false, "Email inserita non valida")
        return ValidationResult(true)
    }

    fun <T> checkNullObj(obj : T? = null) : ValidationResult {
        if(obj == null) return ValidationResult(false, "Campo non inserito")
        return ValidationResult(true)
    }

    fun message(
        message : String? = null,
        maxLength : Int = 1000,
    ) : ValidationResult {
        if(message == null) return ValidationResult(false, "Messaggio non inserito")
        if(message.isBlank()) return ValidationResult(false, "Messaggio non inserito")
        if(message.length > maxLength) return ValidationResult(false, "Messaggio troppo lungo")
        return ValidationResult(true)
    }

    fun password(
        password : String? = null,
        minLength : Int = 6,
        mustContainLetterAndDigits : Boolean = true
    ) : ValidationResult {
        if(password == null) return ValidationResult(false, "Password non inserita")
        if(password.isBlank()) return ValidationResult(false, "Password non inserita")
        if(password.length < minLength) return ValidationResult(false, "Password troppo corta")
        if(mustContainLetterAndDigits) {
            val cond = password.any { it.isDigit() } && password.any { it.isLetter() }
            if(!cond) return ValidationResult(false, "Password deve contenere almeno una lettera e un numero")
        }
        return ValidationResult(true)
    }

    fun repeatedPassword(password: String? = null, repeatedPassword: String? = null) : ValidationResult {
        if(password == null || repeatedPassword == null) return ValidationResult(false, "Una delle due password non inserita")
        if(password.isBlank() || repeatedPassword.isBlank()) return ValidationResult(false, "Una delle due password non inserita")
        if(password != repeatedPassword) return ValidationResult(false, "Le password non coincidono")
        return ValidationResult(true)
    }

    fun code(codeToValidate : String? = null, length: Int = 6) : ValidationResult {
        if(codeToValidate == null) return ValidationResult(false, "Codice non inserito")
        if(codeToValidate.isBlank()) return ValidationResult(false, "Codice non inserito")
        if(codeToValidate.length != length) return ValidationResult(false, "Codice non valido")
        return ValidationResult(true)
    }

    fun nullable(obj : Any?) : ValidationResult {
        if(obj == null) return ValidationResult(false, "Errore campo")
        return ValidationResult(true)
    }

    fun nameSurname (name : String? = null, minLength: Int = 2, maxLength: Int = 100) : ValidationResult {
        if(name == null) return ValidationResult(false, "Nome non inserito")
        if(name.isBlank()) return ValidationResult(false, "Nome non inserito")
        if(name.length < minLength) return ValidationResult(false, "Nome troppo corto")
        if(name.length > maxLength) return ValidationResult(false, "Nome troppo lungo")
        return ValidationResult(true)
    }

    fun isValidNonEmptyLatitude(latitude : String) : Boolean {
        if(latitude == "") return false
        val pattern = Regex("^[-+]?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?)$")
        return pattern.matches(latitude)
    }

    fun isValidNonEmptyLongitude(latitude : String) : Boolean {
        if(latitude == "") return false
        val pattern = Regex("^[-+]?((1?[0-7]?\\d(\\.\\d+)?)|180(\\.0+)?)$")
        return pattern.matches(latitude)
    }

    fun isValidPhone(newPhone: String?): ValidationResult {
        if (newPhone == null) return ValidationResult(false, "Telefono non inserito")
        if (newPhone.isBlank()) return ValidationResult(false, "Telefono non inserito")
        val regex = Regex("[0-9+]+")
        val result = regex.matches(newPhone)
        return if (result) ValidationResult(true) else ValidationResult(
            false,
            "Telefono non valido"
        )
    }
}