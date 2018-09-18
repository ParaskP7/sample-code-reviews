package io.petros.reviews.data.network.rest.response.review

import io.petros.reviews.data.network.rest.RestApi.Companion.REVIEWS_COUNT

data class ReviewsResultPageResponse(
    val status: Boolean,
    val total_reviews_comments: Int,
    val data: List<ReviewResponse>?,
    val message: String?
) {

    fun nextPage(currentPage: Int): Int? {
        val nextPage = currentPage + 1
        return if (nextPage * REVIEWS_COUNT < total_reviews_comments) nextPage else null
    }

}
