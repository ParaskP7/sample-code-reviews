package io.petros.reviews.presentation.feature.common.list.adapter

import android.content.Context
import android.support.annotation.VisibleForTesting
import android.support.v7.widget.RecyclerView
import io.petros.reviews.domain.model.common.PaginationData

@Suppress("TooManyFunctions")
abstract class InfiniteAdapter<T>(
    val items: ArrayList<T> = arrayListOf()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    var context: Context? = null

    var status = AdapterStatus.IDLE
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var paginationData: PaginationData<T>? = null

    fun nextPage() = paginationData?.nextPage

    /* STATUS */

    fun isLoading(): Boolean {
        return status == AdapterStatus.LOADING
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

    fun setItems(paginationData: PaginationData<T>) {
        val shouldReload = shouldReload(paginationData)
        this.paginationData = paginationData
        if (shouldReload) reloadItems(paginationData) else appendItems(paginationData)
        notifyDataSetChanged()
    }

    private fun shouldReload(paginationData: PaginationData<T>): Boolean {
        return paginationData.isFirstPage() ||
                paginationData == this.paginationData
    }

    private fun reloadItems(paginationData: PaginationData<T>) {
        items.clear()
        items.addAll(paginationData.allPageItems)
    }

    private fun appendItems(paginationData: PaginationData<T>) {
        paginationData.latestPage?.items()?.let { items.addAll(it) }
    }

    override fun getItemCount(): Int {
        val statusCount = if (status != AdapterStatus.IDLE) 1 else 0
        return items.size + statusCount
    }

    /* NAVIGATION */

    protected fun isAtLastPosition(position: Int) = position == Math.max(itemCount - 1, 0)

}
