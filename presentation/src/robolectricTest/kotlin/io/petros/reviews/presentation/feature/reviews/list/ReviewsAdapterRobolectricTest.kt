package io.petros.reviews.presentation.feature.reviews.list

import android.support.v7.widget.RecyclerView
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import io.petros.reviews.domain.model.review.Review
import io.petros.reviews.presentation.PreconfiguredRobolectricTestRunner
import io.petros.reviews.presentation.RobolectricTestProvider.Companion.provideContext
import io.petros.reviews.presentation.feature.common.list.adapter.AdapterStatus
import io.petros.reviews.presentation.feature.common.list.holder.ErrorViewHolder
import io.petros.reviews.presentation.feature.common.list.holder.ProgressViewHolder
import io.petros.reviews.presentation.feature.reviews.list.ReviewsAdapter.Companion.VIEW_TYPE_ERROR
import io.petros.reviews.presentation.feature.reviews.list.ReviewsAdapter.Companion.VIEW_TYPE_PROGRESS
import io.petros.reviews.presentation.feature.reviews.list.ReviewsAdapter.Companion.VIEW_TYPE_REVIEW
import io.petros.reviews.test.domain.TestReviewsProvider.Companion.provideReview
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(PreconfiguredRobolectricTestRunner::class)
class ReviewsAdapterRobolectricTest {

    private val context = provideContext()
    private val recyclerView = RecyclerView(context)

    private val allItems = ArrayList<Review>()
    private val currentItems = listOf(provideReview(id = 1), provideReview(id = 2), provideReview(id = 3))
    private val newItems = listOf(provideReview(id = 4), provideReview(id = 5), provideReview(id = 6))

    private lateinit var testedClass: ReviewsAdapter

    @Before
    fun setUp() {
        allItems.addAll(currentItems)

        testedClass = ReviewsAdapter(allItems)
    }

    /* CONTEXT */

    @Test
    fun `When adapter is set, then status is idle`() {
        assertThat(testedClass.status).isEqualTo(AdapterStatus.IDLE)
    }

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
    fun `When adapter is set, then all items are used`() {
        assertThat(testedClass.itemCount).isEqualTo(currentItems.size)
    }

    @Test
    fun `When setting items to adapter, then new items replace the current items`() {
        assertThat(testedClass.items).isEqualTo(currentItems)

        testedClass.setItems(newItems)

        assertThat(testedClass.items).isEqualTo(newItems)
    }

    /* VIEW HOLDER */

    @Test
    fun `When creating a view holder for a progress item, then the correct view holder is returned`() {
        testedClass.onAttachedToRecyclerView(recyclerView)

        val viewHolder = testedClass.onCreateViewHolder(mock(), VIEW_TYPE_PROGRESS)

        assertThat(viewHolder).isInstanceOf(ProgressViewHolder::class.java)
    }

    @Test
    fun `When creating a view holder for a review item, then the correct view holder is returned`() {
        testedClass.onAttachedToRecyclerView(recyclerView)

        val viewHolder = testedClass.onCreateViewHolder(mock(), VIEW_TYPE_REVIEW)

        assertThat(viewHolder).isInstanceOf(ReviewViewHolder::class.java)
    }

    @Test
    fun `When creating a view holder for an error item, then the correct view holder is returned`() {
        testedClass.onAttachedToRecyclerView(recyclerView)

        val viewHolder = testedClass.onCreateViewHolder(mock(), VIEW_TYPE_ERROR)

        assertThat(viewHolder).isInstanceOf(ErrorViewHolder::class.java)
    }

    @Test
    fun `Given a review view type, when binding a view holder, then a review item is bind`() {
        testedClass.onAttachedToRecyclerView(recyclerView)
        testedClass.onCreateViewHolder(mock(), VIEW_TYPE_REVIEW)
        val position = 1
        val viewHolderMock = mock<ReviewViewHolder>()

        testedClass.onBindViewHolder(viewHolderMock, position)

        verify(viewHolderMock).bind(currentItems[position])
    }

    @Test
    fun `Given a non-review view type, when binding a view holder, then a review item is not bind`() {
        testedClass.onAttachedToRecyclerView(recyclerView)
        testedClass.onCreateViewHolder(mock(), VIEW_TYPE_PROGRESS)
        testedClass.status = AdapterStatus.LOADING
        val position = currentItems.size
        val viewHolderMock = mock<ReviewViewHolder>()

        testedClass.onBindViewHolder(viewHolderMock, position)

        verifyZeroInteractions(viewHolderMock)
    }

    /* NAVIGATION */

    @Test
    fun `When getting the item view type of a review item, then a review view type is returned`() {
        val result = testedClass.getItemViewType(1)

        assertThat(result).isEqualTo(VIEW_TYPE_REVIEW)
    }

    @Test
    fun `Given at last position and idle, when getting the item view type, then a review item view type is returned`() {
        testedClass.status = AdapterStatus.IDLE

        val result = testedClass.getItemViewType(currentItems.size)

        assertThat(result).isEqualTo(VIEW_TYPE_REVIEW)
    }

    @Test
    fun `Given at last position and loading, when getting the item view type, then a progress view type is returned`() {
        testedClass.status = AdapterStatus.LOADING

        val result = testedClass.getItemViewType(currentItems.size)

        assertThat(result).isEqualTo(VIEW_TYPE_PROGRESS)
    }

    @Test
    fun `Given at last position and error, when getting the item view type, then an error view type is returned`() {
        testedClass.status = AdapterStatus.ERROR

        val result = testedClass.getItemViewType(currentItems.size)

        assertThat(result).isEqualTo(VIEW_TYPE_ERROR)
    }

}
