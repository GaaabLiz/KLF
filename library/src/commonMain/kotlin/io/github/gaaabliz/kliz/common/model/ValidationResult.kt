package io.github.gaaabliz.kliz.common.model

data class ValidationResult(
    val successful : Boolean,
    val errorMessage : String? = null
)
