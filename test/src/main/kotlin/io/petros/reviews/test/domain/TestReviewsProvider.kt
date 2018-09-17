package io.petros.reviews.test.domain

import io.petros.reviews.domain.model.review.Review
import io.petros.reviews.domain.model.review.ReviewsResultPage

class TestReviewsProvider {

    companion object {

        private const val ID = 1

        fun provideReview(
            id: Int = ID
        ): Review {
            return Review(
                id
            )
        }

        fun provideReviewsResultPage(
            reviews: List<Review> = arrayListOf(provideReview(), provideReview(), provideReview())
        ): ReviewsResultPage {
            return ReviewsResultPage(
                reviews
            )
        }

    }

}
