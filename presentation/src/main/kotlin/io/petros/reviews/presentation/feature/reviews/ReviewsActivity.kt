package io.petros.reviews.presentation.feature.reviews

import android.arch.lifecycle.Observer
import android.os.Bundle
import io.petros.reviews.R
import io.petros.reviews.presentation.feature.BaseActivity
import kotlinx.android.synthetic.main.activity_reviews.*

class ReviewsActivity : BaseActivity<ReviewsActivityViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservers()
        loadData()
    }

    /* OBSERVERS */

    private fun initObservers() {
        observeReviews()
    }

    private fun observeReviews() {
        viewModel.reviewsObservable.observe(this, Observer { it ->
            it?.let { tv_reviews.text = it.reviews.toString() }
        })
    }

    /* DATA LOADING */

    private fun loadData() {
        viewModel.loadReviews()
    }

    /* CONTRACT */

    override fun constructContentView() = R.layout.activity_reviews

    override fun constructViewModel() = ReviewsActivityViewModel::class.java

}
