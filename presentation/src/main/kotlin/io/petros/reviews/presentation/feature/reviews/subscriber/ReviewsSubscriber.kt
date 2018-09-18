package io.petros.reviews.presentation.feature.reviews.subscriber

import android.arch.lifecycle.MutableLiveData
import io.petros.reviews.domain.model.review.ReviewsResultPage
import io.petros.reviews.presentation.feature.common.list.adapter.AdapterStatus
import io.reactivex.observers.DisposableSingleObserver
import timber.log.Timber

class ReviewsSubscriber(
    val statusObservable: MutableLiveData<AdapterStatus>,
    val reviewsObservable: MutableLiveData<ReviewsResultPage>
) : DisposableSingleObserver<ReviewsResultPage>() {

    override fun onSuccess(reviews: ReviewsResultPage) {
        Timber.d("Load reviews success. [Reviews: $reviews]")
        statusObservable.postValue(AdapterStatus.IDLE)
        reviewsObservable.postValue(reviews)
    }

    override fun onError(exception: Throwable) {
        Timber.w(exception, "Load reviews error.")
        statusObservable.postValue(AdapterStatus.ERROR)
        reviewsObservable.postValue(null)
    }

}

