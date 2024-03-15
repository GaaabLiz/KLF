package it.uniupo.cantieriapp.common.domain.model

import it.gabliz.kliz.common.util.DataUtils

data class Developer(
    val avatarId : Int,
    val name : String,
    val surname : String,
    val email : String,
) {
    companion object {
        fun random() : Developer {
            return Developer(
                avatarId = DataUtils.generateRandomInt(1, 10),
                name = "Mario",
                surname = "Rossi",
                email = ""
            )
        }
    }
}
