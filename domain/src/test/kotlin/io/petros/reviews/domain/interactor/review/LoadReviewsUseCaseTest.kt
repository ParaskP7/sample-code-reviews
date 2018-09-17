package io.petros.reviews.domain.interactor.review

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.petros.reviews.domain.repository.review.ReviewsRepository
import io.petros.reviews.test.domain.TestReviewsProvider.Companion.provideReviewsResultPage
import io.petros.reviews.test.domain.TestToursProvider.Companion.provideTour
import io.petros.reviews.test.reactive.rx.TestRxSchedulersProvider.Companion.provideRxSchedulers
import io.reactivex.Single
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class LoadReviewsUseCaseTest {

    private val tour = provideTour()
    private val params = LoadReviewsUseCase.Params.with(tour)

    private val reviewsResultPage = provideReviewsResultPage()

    private lateinit var testedClass: LoadReviewsUseCase
    private val reviewsRepositoryMock = mock<ReviewsRepository>()

    @Before
    fun setUp() {
        testedClass = LoadReviewsUseCase(reviewsRepositoryMock, provideRxSchedulers())
    }

    @Test
    fun `When load reviews use case is build, then reviews repository triggers load reviews`() {
        testedClass.buildUseCaseObservable(params)

        verify(reviewsRepositoryMock).loadReviews(tour)
    }

    @Test
    fun `When load reviews returns, then the reviews result page is the expected one`() {
        whenever(reviewsRepositoryMock.loadReviews(tour)).thenReturn(Single.just(reviewsResultPage))

        val result = testedClass.buildUseCaseObservable(params).blockingGet()

        assertThat(result).isEqualTo(reviewsResultPage)
    }

}
