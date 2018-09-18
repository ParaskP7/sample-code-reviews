package io.petros.reviews.presentation.feature.reviews.list

import android.support.v7.widget.RecyclerView
import io.petros.reviews.domain.model.review.Review
import io.petros.reviews.presentation.feature.reviews.view.ReviewItemView

class ReviewViewHolder(
    itemView: ReviewItemView
) : RecyclerView.ViewHolder(itemView) {

    fun bind(review: Review) {
        bindReview(review)
    }

    private fun bindReview(review: Review) {
        (itemView as ReviewItemView).bind(review)
    }

}
