package io.petros.reviews.presentation.feature.reviews

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.petros.reviews.domain.interactor.review.LoadReviewsUseCase
import io.petros.reviews.presentation.feature.common.list.adapter.AdapterStatus
import io.petros.reviews.test.domain.TestToursProvider.Companion.provideTour
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
    private val loadReviewsUseCaseMock = mock<LoadReviewsUseCase>()

    @Before
    fun setUp() {
        testedClass = ReviewsActivityViewModel(loadReviewsUseCaseMock)
        testedClass.isRefreshingObservable.observeForever(isRefreshingObservableMock)
        testedClass.statusObservable.observeForever(statusObservableMock)
    }

    @Test
    fun `When reload reviews is triggered, then the is refreshing true flag is posted`() {
        testedClass.reloadReviews(tour)

        verify(isRefreshingObservableMock).onChanged(true)
    }

    @Test
    fun `When reload reviews is triggered, then a loading status is posted`() {
        testedClass.reloadReviews(tour)

        verify(statusObservableMock).onChanged(AdapterStatus.LOADING)
    }

    @Test
    fun `When reload reviews is triggered, then load reviews use case executes`() {
        testedClass.reloadReviews(tour)

        verify(loadReviewsUseCaseMock).execute(any(), eq(LoadReviewsUseCase.Params.with(tour)))
    }

    @Test
    fun `When load reviews is triggered, then a loading status is posted`() {
        testedClass.loadReviews(tour)

        verify(statusObservableMock).onChanged(AdapterStatus.LOADING)
    }

    @Test
    fun `When load reviews is triggered, then load reviews use case executes`() {
        testedClass.loadReviews(tour)

        verify(loadReviewsUseCaseMock).execute(any(), eq(LoadReviewsUseCase.Params.with(tour)))
    }

    @Test
    fun `When view model is destroyed, then load reviews use case is disposed`() {
        testedClass.onCleared()

        verify(loadReviewsUseCaseMock).dispose()
    }

}
