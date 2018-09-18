package io.petros.reviews.presentation.feature.reviews.list

import android.content.Context
import android.support.annotation.VisibleForTesting
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import io.petros.reviews.R
import io.petros.reviews.domain.model.review.Review
import io.petros.reviews.presentation.feature.common.list.adapter.AdapterStatus
import io.petros.reviews.presentation.feature.common.list.holder.ErrorViewHolder
import io.petros.reviews.presentation.feature.common.list.holder.ProgressViewHolder
import io.petros.reviews.presentation.feature.common.list.item.ErrorItemView
import io.petros.reviews.presentation.feature.common.list.item.ProgressItemView
import io.petros.reviews.presentation.feature.reviews.view.ReviewItemView
import io.petros.reviews.presentation.toast

class ReviewsAdapter(
    val items: ArrayList<Review> = arrayListOf()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        internal const val VIEW_TYPE_PROGRESS = 0
        internal const val VIEW_TYPE_REVIEW = 1
        internal const val VIEW_TYPE_ERROR = 101
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    var context: Context? = null

    var status = AdapterStatus.IDLE
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    /* CONTEXT */

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        context = recyclerView.context
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        context = null
        super.onDetachedFromRecyclerView(recyclerView)
    }

    /* ITEMS */

    fun setItems(newItems: List<Review>) {
        items.clear()
        items.plusAssign(newItems)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        val statusCount = if (status != AdapterStatus.IDLE) 1 else 0
        return items.size + statusCount
    }

    /* ITEM VIEW HOLDER */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context?.let {
            return when (viewType) {
                VIEW_TYPE_PROGRESS -> ProgressViewHolder(ProgressItemView(it))
                VIEW_TYPE_REVIEW -> ReviewViewHolder(ReviewItemView(it))
                VIEW_TYPE_ERROR -> ErrorViewHolder(ErrorItemView(it)) { it.toast(R.string.retry_loading) }
                else -> throw IllegalArgumentException("View type out of range. [View Type: $viewType]")
            }
        }
        throw IllegalArgumentException("Context not initialised.")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == VIEW_TYPE_REVIEW) {
            (holder as ReviewViewHolder).bind(items[position])
        }
    }

    /* NAVIGATION */

    override fun getItemViewType(position: Int): Int {
        return when {
            isAtLastPosition(position) -> when (status) {
                AdapterStatus.IDLE -> VIEW_TYPE_REVIEW
                AdapterStatus.LOADING -> VIEW_TYPE_PROGRESS
                AdapterStatus.ERROR -> VIEW_TYPE_ERROR
            }
            else -> VIEW_TYPE_REVIEW
        }
    }

    private fun isAtLastPosition(position: Int) = position == Math.max(itemCount - 1, 0)

}
