package io.petros.reviews.presentation.feature.reviews

import android.arch.lifecycle.Observer
import android.os.Bundle
import io.petros.reviews.R
import io.petros.reviews.domain.model.place.Tour
import io.petros.reviews.presentation.feature.BaseActivity
import io.petros.reviews.presentation.feature.common.list.InfiniteRecyclerView
import io.petros.reviews.presentation.feature.reviews.list.ReviewsAdapter
import kotlinx.android.synthetic.main.activity_reviews.*

@Suppress("TooManyFunctions")
class ReviewsActivity : BaseActivity<ReviewsActivityViewModel>(), InfiniteRecyclerView.Listener {

    private val tour = Tour("berlin-l17", "tempelhof-2-hour-airport-history-tour-berlin-airlift-more-t23776")

    private val adapter = ReviewsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSwipeToRefresh()
        initRecyclerView()
        initObservers()
        loadDataOrRestore()
    }

    /* OBSERVERS */

    private fun initSwipeToRefresh() {
        swipe_refresh.setOnRefreshListener { reloadData() }
    }

    private fun initRecyclerView() {
        recycler_view.adapter = adapter
        recycler_view.listener = this
    }

    private fun initObservers() {
        observeRefreshStatus()
        observeStatus()
        observeReviews()
    }

    private fun observeRefreshStatus() {
        viewModel.isRefreshingObservable.observe(this, Observer { it ->
            it?.let { swipe_refresh.isRefreshing = it }
        })
    }

    private fun observeStatus() {
        viewModel.statusObservable.observe(this, Observer { it ->
            it?.let { adapter.status = it }
        })
    }

    private fun observeReviews() {
        viewModel.reviewsObservable.observe(this, Observer { it ->
            it?.let { adapter.setItems(it) }
        })
    }

    /* DATA LOADING */

    override fun loadDataOrRestore() {
        viewModel.loadReviewsOrRestore(tour)
    }

    private fun reloadData() {
        viewModel.reloadReviews(tour)
    }

    override fun loadData(page: Int?) {
        viewModel.loadReviews(tour, page)
    }

    /* CONTRACT */

    override fun constructContentView() = R.layout.activity_reviews

    override fun constructViewModel() = ReviewsActivityViewModel::class.java

}
