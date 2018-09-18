package io.petros.reviews.data.network.rest.mapper.review

import io.petros.reviews.data.network.rest.response.review.ReviewResponse
import io.petros.reviews.data.network.rest.response.review.ReviewsResultPageResponse
import io.petros.reviews.domain.REVIEW_DATE_FORMAT
import io.petros.reviews.domain.model.review.Language
import io.petros.reviews.domain.model.review.Review
import io.petros.reviews.domain.model.review.Reviewer
import io.petros.reviews.domain.model.review.ReviewsResultPage
import io.petros.reviews.domain.toDate

class ReviewsMapper {

    companion object {

        internal fun transform(reviewsResultPageResponse: ReviewsResultPageResponse): ReviewsResultPage {
            val reviews = arrayListOf<Review>()
            for (reviewResponse in reviewsResultPageResponse.data) {
                reviews.add(reviewResponse.toReview())
            }
            return ReviewsResultPage(reviews)
        }

    }

}

private fun ReviewResponse.toReview(): Review {
    return Review(
        id = review_id,
        rating = rating.toDouble(),
        date = date.toDate(REVIEW_DATE_FORMAT),
        title = if (title.isNotEmpty()) title else null,
        message = if (message.isNotEmpty()) message else null,
        reviewer = toReviewer(),
        language = toLanguage()
    )
}

private fun ReviewResponse.toReviewer(): Reviewer {
    return Reviewer(
        type = traveler_type,
        name = reviewerName,
        country = reviewerCountry
    )
}

private fun ReviewResponse.toLanguage(): Language {
    return Language(
        isForeign = foreignLanguage,
        code = languageCode
    )
}
