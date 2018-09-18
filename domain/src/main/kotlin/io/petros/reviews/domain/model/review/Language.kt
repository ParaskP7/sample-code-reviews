package io.petros.reviews.domain.model.review

import io.petros.reviews.domain.emptyString
import io.petros.reviews.domain.withParentheses

data class Language(
    val isForeign: Boolean,
    val code: String
) {

    fun formatted() = if (isForeign) code.withParentheses() else emptyString()

}
