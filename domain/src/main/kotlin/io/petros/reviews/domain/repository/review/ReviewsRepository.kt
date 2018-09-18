package io.petros.reviews.domain.repository.review

import io.petros.reviews.domain.model.place.Tour
import io.petros.reviews.domain.model.review.ReviewsResultPage
import io.reactivex.Single

interface ReviewsRepository {

    fun loadReviews(tour: Tour, page: Int?): Single<ReviewsResultPage>

}
