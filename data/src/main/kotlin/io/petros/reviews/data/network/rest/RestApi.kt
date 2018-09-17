package io.petros.reviews.data.network.rest

import io.petros.reviews.data.network.rest.response.review.ReviewsResultPageResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

@Suppress("LongParameterList")
interface RestApi {

    @GET("{city}/{place}/reviews.json")
    fun loadReviews(
        @Path("city") city: String,
        @Path("place") place: String,
        @Query("page") page: Int = 0,
        @Query("count") count: Int = 5,
        @Query("rating") rating: Int = 0,
        @Query("sortBy") sortBy: String = "date_of_review",
        @Query("direction") direction: String = "DESC"
    ): Single<ReviewsResultPageResponse>

}
