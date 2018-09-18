package io.petros.reviews.presentation.feature.reviews.view

import android.view.View
import io.petros.reviews.domain.REVIEW_DATE_FORMAT
import io.petros.reviews.domain.emptyString
import io.petros.reviews.domain.toString
import io.petros.reviews.presentation.PreconfiguredRobolectricTestRunner
import io.petros.reviews.presentation.RobolectricTestProvider.Companion.provideContext
import io.petros.reviews.test.domain.TestReviewsProvider.Companion.provideReview
import kotlinx.android.synthetic.main.item_review.view.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(PreconfiguredRobolectricTestRunner::class)
class ReviewItemViewRobolectricTest {

    private val review = provideReview()

    private lateinit var testedClass: ReviewItemView
    private val context = provideContext()

    @Before
    fun setUp() {
        testedClass = ReviewItemView(context)
    }

    /* REVIEW DETAILS */

    @Test
    fun `When review is bind, then the review rating is set`() {
        testedClass.bind(review)

        assertThat(testedClass.tv_review_rating.text).isEqualTo(review.stars())
    }

    @Test
    fun `When review is bind, then the review date is set`() {
        testedClass.bind(review)

        assertThat(testedClass.tv_review_date.text).isEqualTo(review.date.toString(REVIEW_DATE_FORMAT))
    }

    @Test
    fun `When review is bind, then the review reviewer is set`() {
        testedClass.bind(review)

        assertThat(testedClass.tv_review_reviewer.text).isEqualTo(review.reviewer.formatted())
    }

    @Test
    fun `When review is bind, then the review language is set`() {
        testedClass.bind(review)

        assertThat(testedClass.tv_review_language.text).isEqualTo(review.language.formatted())
    }

    /* REVIEW TITLE */

    @Test
    fun `Given a title, when review is bind, then the review title gets visible`() {
        testedClass.bind(review)

        assertThat(testedClass.tv_review_title.visibility).isEqualTo(View.VISIBLE)
    }

    @Test
    fun `Given no title, when review is bind, then the review title remains invisible`() {
        testedClass.bind(provideReview(title = null))

        assertThat(testedClass.tv_review_title.visibility).isEqualTo(View.GONE)
    }

    @Test
    fun `Given a title, when review is bind, then the review title is set`() {
        testedClass.bind(review)

        assertThat(testedClass.tv_review_title.text).isEqualTo(review.title)
    }

    @Test
    fun `Given no title, when review is bind, then the review title is not set`() {
        testedClass.bind(provideReview(title = null))

        assertThat(testedClass.tv_review_title.text).isEqualTo(emptyString())
    }

    /* REVIEW MESSAGE */

    @Test
    fun `Given a message, when review is bind, then the review message gets visible`() {
        testedClass.bind(review)

        assertThat(testedClass.tv_review_message.visibility).isEqualTo(View.VISIBLE)
    }

    @Test
    fun `Given no message, when review is bind, then the review message remains invisible`() {
        testedClass.bind(provideReview(message = null))

        assertThat(testedClass.tv_review_message.visibility).isEqualTo(View.GONE)
    }

    @Test
    fun `Given a message, when review is bind, then the review message is set`() {
        testedClass.bind(review)

        assertThat(testedClass.tv_review_message.text).isEqualTo(review.message)
    }

    @Test
    fun `Given no message, when review is bind, then the review message is not set`() {
        testedClass.bind(provideReview(message = null))

        assertThat(testedClass.tv_review_message.text).isEqualTo(emptyString())
    }

}
