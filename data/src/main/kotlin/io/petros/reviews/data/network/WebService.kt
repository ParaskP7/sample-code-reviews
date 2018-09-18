package io.petros.reviews.data.network

import io.petros.reviews.domain.model.place.Tour
import io.petros.reviews.domain.model.review.ReviewsResultPage
import io.reactivex.Single

interface WebService {

    fun loadReviews(tour: Tour, page: Int?): Single<ReviewsResultPage>

}
