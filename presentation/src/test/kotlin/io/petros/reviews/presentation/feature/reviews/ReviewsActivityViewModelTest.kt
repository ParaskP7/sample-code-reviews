package io.petros.reviews.presentation.feature.reviews

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.petros.reviews.domain.interactor.review.LoadReviewsUseCase
import io.petros.reviews.domain.model.common.PaginationData
import io.petros.reviews.domain.model.review.Review
import io.petros.reviews.domain.model.review.ReviewsResultPage
import io.petros.reviews.presentation.feature.common.list.adapter.AdapterStatus
import io.petros.reviews.test.domain.TestReviewsProvider.Companion.NEXT_PAGE
import io.petros.reviews.test.domain.TestReviewsProvider.Companion.provideReview
import io.petros.reviews.test.domain.TestToursProvider.Companion.provideTour
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ReviewsActivityViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val tour = provideTour()

    private lateinit var testedClass: ReviewsActivityViewModel
    private val isRefreshingObservableMock = mock<Observer<Boolean>>()
    private val statusObservableMock = mock<Observer<AdapterStatus>>()
    private val reviewsObservableMock = mock<Observer<PaginationData<Review>>>()
    private val loadReviewsUseCaseMock = mock<LoadReviewsUseCase>()

    @Before
    fun setUp() {
        testedClass = ReviewsActivityViewModel(loadReviewsUseCaseMock)
        testedClass.isRefreshingObservable.observeForever(isRefreshingObservableMock)
        testedClass.statusObservable.observeForever(statusObservableMock)
        testedClass.reviewsObservable.observeForever(reviewsObservableMock)
    }

    @Test
    fun `Given empty pagination data, when load reviews or restore is triggered, then load reviews is triggered`() {
        testedClass.paginationData.clear()
        assertThat(testedClass.paginationData.isEmpty()).isTrue()

        testedClass.loadReviewsOrRestore(tour)

        verify(statusObservableMock).onChanged(AdapterStatus.LOADING)
        verify(loadReviewsUseCaseMock).execute(any(), eq(LoadReviewsUseCase.Params.with(tour, null)))
    }

    @Test
    fun `Given pagination data, when load reviews or restore is triggered, then restore is triggered`() {
        val reviews = listOf(provideReview(id = 1), provideReview(id = 2), provideReview(id = 3))
        testedClass.paginationData.addPage(ReviewsResultPage(NEXT_PAGE, reviews))
        assertThat(testedClass.paginationData.isEmpty()).isFalse()

        testedClass.loadReviewsOrRestore(tour)

        verify(reviewsObservableMock).onChanged(testedClass.paginationData)
    }

    @Test
    fun `When reload reviews is triggered, then the is refreshing true flag is posted`() {
        testedClass.reloadReviews(tour)

        verify(isRefreshingObservableMock).onChanged(true)
    }

    @Test
    fun `When reload reviews is triggered, then existing pagination data gets cleared before triggering new load`() {
        val reviews = listOf(provideReview(id = 1), provideReview(id = 2), provideReview(id = 3))
        testedClass.paginationData.addPage(ReviewsResultPage(NEXT_PAGE, reviews))
        assertThat(testedClass.paginationData.isEmpty()).isFalse()

        testedClass.reloadReviews(tour)

        assertThat(testedClass.paginationData.isEmpty()).isTrue()
        verify(statusObservableMock).onChanged(AdapterStatus.LOADING)
        verify(loadReviewsUseCaseMock).execute(any(), eq(LoadReviewsUseCase.Params.with(tour, null)))
    }

    @Test
    fun `When reload reviews is triggered, then a loading status is posted`() {
        testedClass.reloadReviews(tour)

        verify(statusObservableMock).onChanged(AdapterStatus.LOADING)
    }

    @Test
    fun `When reload reviews is triggered, then load reviews use case executes`() {
        testedClass.reloadReviews(tour)

        verify(loadReviewsUseCaseMock).execute(any(), eq(LoadReviewsUseCase.Params.with(tour, null)))
    }

    @Test
    fun `When load reviews is triggered, then a loading status is posted`() {
        testedClass.loadReviews(tour)

        verify(statusObservableMock).onChanged(AdapterStatus.LOADING)
    }

    @Test
    fun `When load reviews is triggered, then load reviews use case executes`() {
        testedClass.loadReviews(tour)

        verify(loadReviewsUseCaseMock).execute(any(), eq(LoadReviewsUseCase.Params.with(tour, null)))
    }

    @Test
    fun `When view model is destroyed, then load reviews use case is disposed`() {
        testedClass.onCleared()

        verify(loadReviewsUseCaseMock).dispose()
    }

}
