package io.github.gaaabliz.kliz.common.model

import io.github.gaaabliz.kliz.common.util.GenUtils


data class Developer(
    val avatarId : Int,
    val name : String,
    val surname : String,
    val email : String,
) {
    companion object {
        fun random() : Developer {
            return Developer(
                avatarId = GenUtils.generateRandomInt(1, 10),
                name = "Mario",
                surname = "Rossi",
                email = ""
            )
        }
    }
}
