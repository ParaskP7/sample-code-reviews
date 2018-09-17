package io.petros.reviews.domain.repository.review

import io.petros.reviews.domain.model.review.ReviewsResultPage
import io.reactivex.Single

interface ReviewsRepository {

    fun loadReviews(): Single<ReviewsResultPage>

}
