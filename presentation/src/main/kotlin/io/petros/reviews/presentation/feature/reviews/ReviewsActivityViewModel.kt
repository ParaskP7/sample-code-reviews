package io.petros.reviews.presentation.feature.reviews

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.annotation.VisibleForTesting
import io.petros.reviews.domain.interactor.review.LoadReviewsUseCase
import io.petros.reviews.domain.model.place.Tour
import io.petros.reviews.domain.model.review.ReviewsResultPage
import io.petros.reviews.presentation.feature.common.list.adapter.AdapterStatus
import io.petros.reviews.presentation.feature.reviews.subscriber.ReviewsSubscriber
import javax.inject.Inject

class ReviewsActivityViewModel @Inject constructor(
    private val loadReviewsUseCase: LoadReviewsUseCase
) : ViewModel() {

    val statusObservable = MutableLiveData<AdapterStatus>()
    val reviewsObservable = MutableLiveData<ReviewsResultPage>()

    fun loadReviews(tour: Tour) {
        statusObservable.postValue(AdapterStatus.LOADING)
        loadReviewsUseCase.execute(
            ReviewsSubscriber(statusObservable, reviewsObservable),
            LoadReviewsUseCase.Params.with(tour)
        )
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    public override fun onCleared() {
        super.onCleared()
        loadReviewsUseCase.dispose()
    }

}
