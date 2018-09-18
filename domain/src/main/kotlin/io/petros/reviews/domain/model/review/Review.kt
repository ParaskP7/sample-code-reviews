package io.petros.reviews.domain.model.review

import io.petros.reviews.domain.questionMark
import io.petros.reviews.domain.space
import io.petros.reviews.domain.star
import java.util.*

data class Review(
    val id: Int,
    val rating: Double,
    val date: Date,
    val title: String?,
    val message: String?,
    val reviewer: Reviewer,
    val language: Language
) {

    companion object {

        private const val ONE_STAR = 1
        private const val TWO_STARS = 2
        private const val THREE_STARS = 3
        private const val FOUR_STARS = 4
        private const val FIVE_STARS = 5
    }

    fun stars(): String {
        return when (rating.toInt()) {
            ONE_STAR -> star()
            TWO_STARS -> star() + space() + star()
            THREE_STARS -> star() + space() + star() + space() + star()
            FOUR_STARS -> star() + space() + star() + space() + star() + space() + star()
            FIVE_STARS -> star() + space() + star() + space() + star() + space() + star() + space() + star()
            else -> questionMark()
        }
    }

}
