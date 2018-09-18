package io.petros.reviews.data.network.rest.mapper.review

import io.petros.reviews.data.network.rest.response.review.ReviewResponse
import io.petros.reviews.data.network.rest.response.review.ReviewsResultPageResponse
import io.petros.reviews.domain.REVIEW_DATE_FORMAT
import io.petros.reviews.domain.model.review.Language
import io.petros.reviews.domain.model.review.Review
import io.petros.reviews.domain.model.review.Reviewer
import io.petros.reviews.domain.model.review.ReviewsResultPage
import io.petros.reviews.domain.toDate
import timber.log.Timber

class ReviewsMapper {

    companion object {

        internal fun transform(
            reviewsResultPageResponse: ReviewsResultPageResponse,
            currentPage: Int
        ): ReviewsResultPage {
            return when (reviewsResultPageResponse.status) {
                true -> reviewsResultPage(reviewsResultPageResponse, currentPage)
                false -> emptyReviewsResultPage(reviewsResultPageResponse)
            }
        }

        private fun reviewsResultPage(
            reviewsResultPageResponse: ReviewsResultPageResponse,
            currentPage: Int
        ): ReviewsResultPage {
            return reviewsResultPageResponse.data?.let {
                fullReviewsResultPage(it, reviewsResultPageResponse.nextPage(currentPage))
            } ?: emptyReviewsResultPage(reviewsResultPageResponse)
        }

        private fun fullReviewsResultPage(
            data: List<ReviewResponse>,
            nextPage: Int?
        ): ReviewsResultPage {
            val reviews = arrayListOf<Review>()
            for (reviewResponse in data) {
                reviews.add(reviewResponse.toReview())
            }
            return ReviewsResultPage(nextPage, reviews)
        }

        private fun emptyReviewsResultPage(
            reviewsResultPageResponse: ReviewsResultPageResponse
        ): ReviewsResultPage {
            Timber.w("No more data to load. [Message: ${reviewsResultPageResponse.message}]")
            return ReviewsResultPage(null, emptyList())
        }

    }

}

private fun ReviewResponse.toReview(): Review {
    return Review(
        id = review_id,
        rating = rating.toDouble(),
        date = date.toDate(REVIEW_DATE_FORMAT),
        title = toTitle(),
        message = toMessage(),
        reviewer = toReviewer(),
        language = toLanguage()
    )
}

private fun ReviewResponse.toTitle() = title?.let { if (title.isNotEmpty()) title else null }

private fun ReviewResponse.toMessage() = message?.let { if (message.isNotEmpty()) message else null }

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
