package io.petros.reviews.presentation.feature.reviews

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.petros.reviews.domain.interactor.review.LoadReviewsUseCase
import io.petros.reviews.domain.model.place.Tour
import io.petros.reviews.domain.model.review.ReviewsResultPage
import io.petros.reviews.presentation.feature.reviews.subscriber.ReviewsSubscriber
import javax.inject.Inject

class ReviewsActivityViewModel @Inject constructor(
    private val loadReviewsUseCase: LoadReviewsUseCase
) : ViewModel() {

    val reviewsObservable = MutableLiveData<ReviewsResultPage>()

    fun loadReviews(tour: Tour) {
        loadReviewsUseCase.execute(
            ReviewsSubscriber(reviewsObservable),
            LoadReviewsUseCase.Params.with(tour)
        )
    }

    override fun onCleared() {
        super.onCleared()
        loadReviewsUseCase.dispose()
    }

}
