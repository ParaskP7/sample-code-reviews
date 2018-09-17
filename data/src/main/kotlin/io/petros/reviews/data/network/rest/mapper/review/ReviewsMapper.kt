package io.petros.reviews.data.network.rest.mapper.review

import io.petros.reviews.data.network.rest.response.review.ReviewResponse
import io.petros.reviews.data.network.rest.response.review.ReviewsResultPageResponse
import io.petros.reviews.domain.model.review.Review
import io.petros.reviews.domain.model.review.ReviewsResultPage

class ReviewsMapper {

    companion object {

        internal fun transform(reviewsResultPageResponse: ReviewsResultPageResponse): ReviewsResultPage {
            val reviews = arrayListOf<Review>()
            for (reviewResponse in reviewsResultPageResponse.data) {
                reviews.add(reviewResponse.toReview())
            }
            return ReviewsResultPage(reviews)
        }

    }

}

private fun ReviewResponse.toReview(): Review {
    return Review(
        id = review_id
    )
}
