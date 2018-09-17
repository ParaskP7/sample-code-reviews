package io.petros.reviews.presentation.feature.reviews.subscriber

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.petros.reviews.domain.model.review.ReviewsResultPage
import io.petros.reviews.test.domain.TestReviewsProvider.Companion.provideReviewsResultPage
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ReviewsSubscriberTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val reviewsResultPage = provideReviewsResultPage()

    private lateinit var testedClass: ReviewsSubscriber
    private val reviewsResultPageObservableMock = mock<Observer<ReviewsResultPage>>()

    @Before
    fun setUp() {
        testedClass = ReviewsSubscriber(MutableLiveData())
        testedClass.reviewsObservable.observeForever(reviewsResultPageObservableMock)
    }

    @Test
    fun `When load reviews succeeds, then the reviews result page is posted`() {
        testedClass.onSuccess(reviewsResultPage)

        verify(reviewsResultPageObservableMock).onChanged(reviewsResultPage)
    }

    @Test
    fun `When load reviews fails, then a null reviews result page is posted`() {
        testedClass.onError(Exception())

        verify(reviewsResultPageObservableMock).onChanged(null)
    }

}
