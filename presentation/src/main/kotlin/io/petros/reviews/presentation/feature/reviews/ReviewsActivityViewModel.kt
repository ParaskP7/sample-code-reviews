package io.petros.reviews.presentation.feature.reviews

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.annotation.VisibleForTesting
import io.petros.reviews.domain.interactor.review.LoadReviewsUseCase
import io.petros.reviews.domain.model.common.PaginationData
import io.petros.reviews.domain.model.place.Tour
import io.petros.reviews.domain.model.review.Review
import io.petros.reviews.presentation.feature.common.list.adapter.AdapterStatus
import io.petros.reviews.presentation.feature.reviews.subscriber.ReviewsSubscriber
import javax.inject.Inject

class ReviewsActivityViewModel @Inject constructor(
    private val loadReviewsUseCase: LoadReviewsUseCase
) : ViewModel() {

    val isRefreshingObservable = MutableLiveData<Boolean>()
    val statusObservable = MutableLiveData<AdapterStatus>()
    val reviewsObservable = MutableLiveData<PaginationData<Review>>()

    val paginationData = PaginationData<Review>()

    fun loadReviewsOrRestore(tour: Tour) {
        if (paginationData.isEmpty()) loadReviews(tour) else reviewsObservable.postValue(paginationData)
    }

    fun reloadReviews(tour: Tour) {
        isRefreshingObservable.postValue(true)
        paginationData.clear()
        loadReviews(tour)
    }

    fun loadReviews(tour: Tour, page: Int? = null) {
        statusObservable.postValue(AdapterStatus.LOADING)
        loadReviewsUseCase.execute(
            ReviewsSubscriber(isRefreshingObservable, statusObservable, reviewsObservable, paginationData),
            LoadReviewsUseCase.Params.with(tour, page)
        )
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    public override fun onCleared() {
        super.onCleared()
        loadReviewsUseCase.dispose()
    }

}
