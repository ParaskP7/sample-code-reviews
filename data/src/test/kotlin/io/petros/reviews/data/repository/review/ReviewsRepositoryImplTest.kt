package io.petros.reviews.data.repository.review

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.petros.reviews.data.network.WebService
import io.petros.reviews.test.domain.TestToursProvider.Companion.provideTour
import org.junit.Before
import org.junit.Test

class ReviewsRepositoryImplTest {

    private val tour = provideTour()

    private lateinit var testedClass: ReviewsRepositoryImpl
    private val webServiceMock = mock<WebService>()

    @Before
    fun setUp() {
        testedClass = ReviewsRepositoryImpl(webServiceMock)
    }

    @Test
    fun `When load reviews is triggered, then web service triggers load reviews`() {
        testedClass.loadReviews(tour)

        verify(webServiceMock).loadReviews(tour)
    }

}
