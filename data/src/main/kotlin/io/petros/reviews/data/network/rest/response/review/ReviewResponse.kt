package io.petros.reviews.data.network.rest.response.review

data class ReviewResponse(
    val review_id: Int,
    val rating: String,
    val title: String?,
    val message: String?,
    val author: String,
    val foreignLanguage: Boolean,
    val date: String,
    val languageCode: String,
    val traveler_type: String?,
    val reviewerName: String,
    val reviewerCountry: String
)
