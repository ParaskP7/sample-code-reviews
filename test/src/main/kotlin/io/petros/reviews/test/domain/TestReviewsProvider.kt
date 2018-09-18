package io.petros.reviews.test.domain

import io.petros.reviews.domain.model.review.Language
import io.petros.reviews.domain.model.review.Review
import io.petros.reviews.domain.model.review.Reviewer
import io.petros.reviews.domain.model.review.ReviewsResultPage
import java.util.*

class TestReviewsProvider {

    companion object {

        const val NEXT_PAGE = 1

        private const val REVIEW_ID = 1
        private const val REVIEW_RATING = 5.0
        private val REVIEW_DATE = GregorianCalendar(2018, Calendar.SEPTEMBER, 18).time
        private const val REVIEW_TITLE = "TITLE"
        private const val REVIEW_MESSAGE = "MESSAGE"

        private const val REVIEWER_TYPE = "REVIEWER_TYPE"
        private const val REVIEWER_NAME = "REVIEWER_NAME"
        private const val REVIEWER_COUNTRY = "REVIEWER_COUNTRY"

        private const val LANGUAGE_IS_FOREIGN = false
        private const val LANGUAGE_CODE = "LANGUAGE_CODE"

        fun provideReview(
            id: Int = REVIEW_ID,
            rating: Double = REVIEW_RATING,
            date: Date = REVIEW_DATE,
            title: String? = REVIEW_TITLE,
            message: String? = REVIEW_MESSAGE,
            reviewer: Reviewer = provideReviewer(),
            language: Language = provideLanguage()
        ): Review {
            return Review(
                id,
                rating,
                date,
                title,
                message,
                reviewer,
                language
            )
        }

        fun provideReviewer(
            type: String? = REVIEWER_TYPE,
            name: String = REVIEWER_NAME,
            country: String = REVIEWER_COUNTRY
        ): Reviewer {
            return Reviewer(
                type,
                name,
                country
            )
        }

        fun provideLanguage(
            isForeign: Boolean = LANGUAGE_IS_FOREIGN,
            code: String = LANGUAGE_CODE
        ): Language {
            return Language(
                isForeign,
                code
            )
        }

        fun provideReviewsResultPage(
            nextPage: Int = NEXT_PAGE,
            reviews: List<Review> = arrayListOf(provideReview(), provideReview(), provideReview())
        ): ReviewsResultPage {
            return ReviewsResultPage(
                nextPage,
                reviews
            )
        }

    }

}
