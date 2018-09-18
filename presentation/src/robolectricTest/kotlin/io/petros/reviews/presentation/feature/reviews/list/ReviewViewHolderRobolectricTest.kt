package io.petros.reviews.presentation.feature.reviews.list

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.petros.reviews.presentation.PreconfiguredRobolectricTestRunner
import io.petros.reviews.presentation.feature.reviews.view.ReviewItemView
import io.petros.reviews.test.domain.TestReviewsProvider.Companion.provideReview
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(PreconfiguredRobolectricTestRunner::class)
class ReviewViewHolderRobolectricTest {

    private val review = provideReview()

    private lateinit var testedClass: ReviewViewHolder
    private val itemViewMock = mock<ReviewItemView>()

    @Before
    fun setUp() {
        testedClass = ReviewViewHolder(itemViewMock)
    }

    @Test
    fun `When the view holder binds a review, then the item view is bind with a review`() {
        testedClass.bind(review)

        verify(itemViewMock).bind(review)
    }

}
