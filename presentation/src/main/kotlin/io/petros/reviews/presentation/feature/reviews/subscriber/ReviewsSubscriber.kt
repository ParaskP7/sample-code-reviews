package io.petros.reviews.presentation.feature.reviews.subscriber

import android.arch.lifecycle.MutableLiveData
import io.petros.reviews.domain.model.common.PaginationData
import io.petros.reviews.domain.model.review.Review
import io.petros.reviews.domain.model.review.ReviewsResultPage
import io.petros.reviews.presentation.feature.common.list.adapter.AdapterStatus
import io.reactivex.observers.DisposableSingleObserver
import timber.log.Timber

class ReviewsSubscriber(
    val isRefreshingObservable: MutableLiveData<Boolean>,
    val statusObservable: MutableLiveData<AdapterStatus>,
    val reviewsObservable: MutableLiveData<PaginationData<Review>>,
    val paginationData: PaginationData<Review>
) : DisposableSingleObserver<ReviewsResultPage>() {

    override fun onSuccess(reviews: ReviewsResultPage) {
        Timber.d("Load reviews success. [Reviews: $reviews]")
        isRefreshingObservable.postValue(false)
        statusObservable.postValue(AdapterStatus.IDLE)
        reviewsObservable.postValue(paginationData.addPage(reviews))
    }

    override fun onError(exception: Throwable) {
        Timber.w(exception, "Load reviews error.")
        isRefreshingObservable.postValue(false)
        statusObservable.postValue(AdapterStatus.ERROR)
        reviewsObservable.postValue(null)
    }

}
