package io.petros.reviews.presentation.feature.reviews

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.petros.reviews.domain.interactor.review.LoadReviewsUseCase
import io.petros.reviews.test.domain.TestToursProvider.Companion.provideTour
import org.junit.Before
import org.junit.Test

class ReviewsActivityViewModelTest {

    private val tour = provideTour()

    private lateinit var testedClass: ReviewsActivityViewModel
    private val loadReviewsUseCaseMock = mock<LoadReviewsUseCase>()

    @Before
    fun setUp() {
        testedClass = ReviewsActivityViewModel(loadReviewsUseCaseMock)
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
