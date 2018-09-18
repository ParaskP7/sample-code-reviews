package io.petros.reviews.presentation.feature.reviews

import android.arch.lifecycle.Observer
import android.os.Bundle
import io.petros.reviews.R
import io.petros.reviews.domain.model.place.Tour
import io.petros.reviews.presentation.feature.BaseActivity
import io.petros.reviews.presentation.feature.reviews.list.ReviewsAdapter
import kotlinx.android.synthetic.main.activity_reviews.*

class ReviewsActivity : BaseActivity<ReviewsActivityViewModel>() {

    private val tour = Tour("berlin-l17", "tempelhof-2-hour-airport-history-tour-berlin-airlift-more-t23776")

    private val adapter = ReviewsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecyclerView()
        initObservers()
        loadData()
    }

    /* OBSERVERS */

    private fun initRecyclerView() {
        recycler_view.adapter = adapter
    }

    private fun initObservers() {
        observeStatus()
        observeReviews()
    }

    private fun observeStatus() {
        viewModel.statusObservable.observe(this, Observer { it ->
            it?.let { adapter.status = it }
        })
    }

    private fun observeReviews() {
        viewModel.reviewsObservable.observe(this, Observer { it ->
            it?.let { adapter.setItems(it.reviews) }
        })
    }

    /* DATA LOADING */

    private fun loadData() {
        viewModel.loadReviews(tour)
    }

    /* CONTRACT */

    override fun constructContentView() = R.layout.activity_reviews

    override fun constructViewModel() = ReviewsActivityViewModel::class.java

}
