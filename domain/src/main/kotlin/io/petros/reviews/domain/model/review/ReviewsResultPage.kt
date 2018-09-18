package io.petros.reviews.domain.model.review

import io.petros.reviews.domain.model.common.PaginationData

data class ReviewsResultPage(
    val nextPage: Int?,
    val reviews: List<Review>
) : PaginationData.InfiniteScrollPage<Review>() {

    override fun nextPage() = nextPage

    override fun items() = reviews

}

