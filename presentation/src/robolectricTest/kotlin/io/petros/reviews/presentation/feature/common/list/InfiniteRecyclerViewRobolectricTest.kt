package io.petros.reviews.presentation.feature.common.list

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.petros.reviews.domain.model.review.Review
import io.petros.reviews.presentation.PreconfiguredRobolectricTestRunner
import io.petros.reviews.presentation.RobolectricTestProvider.Companion.provideContext
import io.petros.reviews.presentation.feature.common.list.adapter.InfiniteAdapter
import io.petros.reviews.test.domain.TestReviewsProvider.Companion.NEXT_PAGE
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(PreconfiguredRobolectricTestRunner::class)
class InfiniteRecyclerViewRobolectricTest {

    private val context = provideContext()

    private var adapterMock: InfiniteAdapter<Review> = mock()

    private lateinit var testedClass: InfiniteRecyclerView
    private var listenerMock: InfiniteRecyclerView.Listener = mock()

    @Before
    fun setUp() {
        testedClass = InfiniteRecyclerView(context)
        testedClass.adapter = adapterMock
        testedClass.listener = listenerMock
    }

    @Test
    fun `When requesting next page, then adapter gets asked for the page`() {
        testedClass.nextPage()

        verify(adapterMock).nextPage()
    }

    @Test
    fun `When load more is triggered, then infinite scrolling listener's load data is triggered for next page`() {
        whenever(adapterMock.nextPage()).thenReturn(NEXT_PAGE)
        testedClass.adapter = adapterMock

        testedClass.loadMore()

        verify(listenerMock).loadData(NEXT_PAGE)
    }

    @Test
    fun `When requesting loading status, then adapter gets asked for the loading status`() {
        testedClass.adapter = adapterMock

        testedClass.isLoading()

        verify(adapterMock).isLoading()
    }

}
