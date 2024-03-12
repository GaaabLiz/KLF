package io.github.gaaabliz.kliz.common.model

data class LoginDetails(val username:String, val password:String) {
    fun someDetailsEmpty() : Boolean = username.isEmpty() || password.isEmpty()
}
