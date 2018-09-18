package io.petros.reviews.presentation.feature.reviews.view

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

    @Test
    fun `When review is bind, then the review id is set`() {
        testedClass.bind(review)

        assertThat(testedClass.tv_review_id.text).isEqualTo(review.id.toString())
    }

}
