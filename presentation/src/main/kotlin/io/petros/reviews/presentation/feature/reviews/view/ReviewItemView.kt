package io.petros.reviews.presentation.feature.reviews.view

import android.content.Context
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import io.petros.reviews.R
import io.petros.reviews.domain.REVIEW_DATE_FORMAT
import io.petros.reviews.domain.model.review.Review
import io.petros.reviews.domain.toString
import io.petros.reviews.presentation.getDimension
import io.petros.reviews.presentation.inflate
import kotlinx.android.synthetic.main.item_review.view.*

class ReviewItemView : CardView {

    constructor(ctx: Context) : super(ctx)

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)

    init {
        inflate(R.layout.item_review)
        initView()
    }

    private fun initView() {
        val lp = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        lp.leftMargin = context.getDimension(R.dimen.item_review_margin)
        lp.rightMargin = context.getDimension(R.dimen.item_review_margin)
        lp.bottomMargin = context.getDimension(R.dimen.item_review_space)
        layoutParams = lp
        radius = context.getDimension(R.dimen.item_review_radius).toFloat()
        cardElevation = context.getDimension(R.dimen.item_review_elevation).toFloat()
    }

    fun bind(review: Review) {
        bindReviewDetails(review)
        bindReviewTitle(review)
        bindReviewMessage(review)
    }

    private fun bindReviewDetails(review: Review) {
        tv_review_rating.text = review.stars()
        tv_review_date.text = review.date.toString(REVIEW_DATE_FORMAT)
        tv_review_reviewer.text = review.reviewer.formatted()
        tv_review_language.text = review.language.formatted()
    }

    private fun bindReviewTitle(review: Review) {
        review.title?.let {
            tv_review_title.visibility = View.VISIBLE
            tv_review_title.text = it
        }
    }

    private fun bindReviewMessage(review: Review) {
        review.message?.let {
            tv_review_message.visibility = View.VISIBLE
            tv_review_message.text = it
        }
    }

}
