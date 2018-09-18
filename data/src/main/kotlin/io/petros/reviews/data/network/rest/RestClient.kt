package io.petros.reviews.data.network.rest

import io.petros.reviews.data.network.WebService
import io.petros.reviews.data.network.rest.mapper.review.ReviewsMapper
import io.petros.reviews.domain.model.place.Tour
import io.petros.reviews.domain.model.review.ReviewsResultPage
import io.reactivex.Single
import javax.inject.Inject

class RestClient @Inject constructor(
    private val restApi: RestApi
) : WebService {

    override fun loadReviews(tour: Tour, page: Int?): Single<ReviewsResultPage> {
        return restApi.loadReviews(tour.city, tour.place, page)
            .map { ReviewsMapper.transform(it, page ?: 0) }
    }

}
