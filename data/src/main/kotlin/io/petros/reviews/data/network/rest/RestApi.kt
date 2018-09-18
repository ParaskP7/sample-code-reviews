package io.petros.reviews.data.network.rest

import io.petros.reviews.data.network.rest.response.review.ReviewsResultPageResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

@Suppress("LongParameterList")
interface RestApi {

    companion object {

        const val REVIEWS_COUNT = 20

    }

    @GET("{city}/{place}/reviews.json")
    fun loadReviews(
        @Path("city") city: String,
        @Path("place") place: String,
        @Query("page") page: Int? = null,
        @Query("count") count: Int = REVIEWS_COUNT,
        @Query("rating") rating: Int = 0,
        @Query("sortBy") sortBy: String = "date_of_review",
        @Query("direction") direction: String = "DESC"
    ): Single<ReviewsResultPageResponse>

}
