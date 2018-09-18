package io.petros.reviews.data.repository.review

import io.petros.reviews.data.network.WebService
import io.petros.reviews.domain.model.place.Tour
import io.petros.reviews.domain.model.review.ReviewsResultPage
import io.petros.reviews.domain.repository.review.ReviewsRepository
import io.reactivex.Single
import javax.inject.Inject

class ReviewsRepositoryImpl @Inject constructor(
    private val webService: WebService
) : ReviewsRepository {

    override fun loadReviews(tour: Tour, page: Int?): Single<ReviewsResultPage> {
        return webService.loadReviews(tour, page)
    }

}
