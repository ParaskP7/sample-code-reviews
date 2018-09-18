package io.petros.reviews.domain.model.review

import io.petros.reviews.domain.dash
import io.petros.reviews.domain.space
import io.petros.reviews.domain.withParentheses

data class Reviewer(
    val type: String,
    val name: String,
    val country: String
) {

    fun formatted() = name + space() + dash() + space() + country + space() + type.withParentheses()

}
