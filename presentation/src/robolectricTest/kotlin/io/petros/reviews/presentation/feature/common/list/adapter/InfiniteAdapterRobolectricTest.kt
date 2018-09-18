package io.petros.reviews.presentation.feature.common.list.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.nhaarman.mockito_kotlin.mock
import io.petros.reviews.domain.model.common.PaginationData
import io.petros.reviews.domain.model.review.Review
import io.petros.reviews.domain.model.review.ReviewsResultPage
import io.petros.reviews.presentation.PreconfiguredRobolectricTestRunner
import io.petros.reviews.presentation.RobolectricTestProvider.Companion.provideContext
import io.petros.reviews.test.domain.TestReviewsProvider.Companion.NEXT_PAGE
import io.petros.reviews.test.domain.TestReviewsProvider.Companion.provideReview
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(PreconfiguredRobolectricTestRunner::class)
class InfiniteAdapterRobolectricTest {

    private val context = provideContext()
    private val recyclerView = RecyclerView(context)

    private val firstPageItems = listOf(provideReview(id = 1), provideReview(id = 2), provideReview(id = 3))
    private val secondPageItems = listOf(provideReview(id = 4), provideReview(id = 5), provideReview(id = 6))
    private val anotherPageItems = listOf(provideReview(id = 7), provideReview(id = 8), provideReview(id = 9))

    private lateinit var testedClass: InfiniteAdapter<Review>

    @Before
    fun setUp() {
        testedClass = object : InfiniteAdapter<Review>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = mock<RecyclerView.ViewHolder>()
            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {}
        }
    }

    /* STATUS */

    @Test
    fun `When adapter is set, then status is idle`() {
        assertThat(testedClass.status).isEqualTo(AdapterStatus.IDLE)
    }

    /* CONTEXT */

    @Test
    fun `When attaching to recycler view, then context is set`() {
        assertThat(testedClass.context).isNull()

        testedClass.onAttachedToRecyclerView(recyclerView)

        assertThat(testedClass.context).isEqualTo(context)
    }

    @Test
    fun `When detaching from recycler view, then context is unset`() {
        testedClass.onAttachedToRecyclerView(recyclerView)
        assertThat(testedClass.context).isEqualTo(context)

        testedClass.onDetachedFromRecyclerView(recyclerView)

        assertThat(testedClass.context).isNull()
    }

    /* ITEMS */

    @Test
    fun `Given first page load, when setting items to adapter, then the first page items are set`() {
        assertThat(testedClass.items).isEqualTo(emptyList<Review>())

        testedClass.setItems(PaginationData<Review>().addPage(ReviewsResultPage(NEXT_PAGE, firstPageItems)))

        assertThat(testedClass.items).isEqualTo(firstPageItems)
    }

    @Test
    fun `Given second page load, when setting items to adapter, then new items are appended on the current items`() {
        val paginationData = PaginationData<Review>()
        paginationData.addPage(ReviewsResultPage(NEXT_PAGE, firstPageItems))
        testedClass.setItems(paginationData)
        assertThat(testedClass.items).isEqualTo(firstPageItems)
        paginationData.addPage(ReviewsResultPage(NEXT_PAGE + 1, secondPageItems))

        testedClass.setItems(paginationData)

        assertThat(testedClass.items).isEqualTo(firstPageItems + secondPageItems)
    }

    @Test
    fun `Given another page reload, when setting items to adapter, then this another page items are reloaded`() {
        testedClass.setItems(PaginationData<Review>().addPage(ReviewsResultPage(NEXT_PAGE, firstPageItems)))
        assertThat(testedClass.items).isEqualTo(firstPageItems)

        testedClass.setItems(PaginationData<Review>().addPage(ReviewsResultPage(NEXT_PAGE, anotherPageItems)))

        assertThat(testedClass.items).isEqualTo(anotherPageItems)
    }

    @Test
    fun `Given page restore, when setting items to adapter, then all page items are restored`() {
        val paginationData = PaginationData<Review>()
        paginationData.addPage(ReviewsResultPage(NEXT_PAGE, firstPageItems))
        testedClass.setItems(paginationData)
        paginationData.addPage(ReviewsResultPage(NEXT_PAGE + 1, secondPageItems))
        testedClass.setItems(paginationData)
        assertThat(testedClass.items).isEqualTo(firstPageItems + secondPageItems)

        testedClass.setItems(paginationData)

        assertThat(testedClass.items).isEqualTo(firstPageItems + secondPageItems)
    }

}
