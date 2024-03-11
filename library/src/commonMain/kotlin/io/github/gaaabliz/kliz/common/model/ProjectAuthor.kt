package io.github.gaaabliz.kliz.common.model

data class ProjectAuthor(
    val name : String,
    val surname : String,
    val email : String,
    val github : String? = null,
)
