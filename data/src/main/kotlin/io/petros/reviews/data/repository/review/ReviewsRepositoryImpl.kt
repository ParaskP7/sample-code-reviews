package io.petros.reviews.data.repository.review

import io.petros.reviews.domain.model.review.Review
import io.petros.reviews.domain.model.review.ReviewsResultPage
import io.petros.reviews.domain.repository.review.ReviewsRepository
import io.reactivex.Single
import javax.inject.Inject

class ReviewsRepositoryImpl @Inject constructor() : ReviewsRepository {

    @Suppress("MagicNumber")
    override fun loadReviews(): Single<ReviewsResultPage> {
        return Single.just(ReviewsResultPage(listOf(Review(1), Review(2), Review(3))))
    }

}
