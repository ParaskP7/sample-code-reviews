package io.petros.reviews.domain.model.review

import io.petros.reviews.domain.dash
import io.petros.reviews.domain.space
import io.petros.reviews.domain.withParentheses

data class Reviewer(
    val type: String?,
    val name: String,
    val country: String
) {

    fun formatted(): String {
        val nameCountry = name + space() + dash() + space() + country
        return type?.let { nameCountry + space() + it.withParentheses() } ?: nameCountry
    }

}
