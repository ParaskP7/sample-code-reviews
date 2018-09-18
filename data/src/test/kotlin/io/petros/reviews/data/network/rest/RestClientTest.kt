package io.petros.reviews.data.network.rest

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.petros.reviews.test.domain.TestReviewsProvider.Companion.NEXT_PAGE
import io.petros.reviews.test.domain.TestToursProvider.Companion.provideTour
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class RestClientTest {

    private val tour = provideTour()

    private lateinit var testedClass: RestClient
    private val restApiMock = mock<RestApi>()

    @Before
    fun setUp() {
        testedClass = RestClient(restApiMock)
    }

    @Test
    fun `When load reviews is triggered, then rest api triggers load reviews`() {
        whenever(restApiMock.loadReviews(tour.city, tour.place, NEXT_PAGE)).thenReturn(Single.just(mock()))

        testedClass.loadReviews(tour, NEXT_PAGE)

        verify(restApiMock).loadReviews(tour.city, tour.place, NEXT_PAGE)
    }

}
