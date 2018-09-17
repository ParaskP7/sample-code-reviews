package io.petros.reviews.domain.interactor.review

import io.petros.reviews.domain.interactor.UseCaseSingle
import io.petros.reviews.domain.model.review.ReviewsResultPage
import io.petros.reviews.domain.reactive.rx.RxSchedulers
import io.petros.reviews.domain.repository.review.ReviewsRepository
import io.reactivex.Single
import javax.inject.Inject

class LoadReviewsUseCase @Inject constructor(
    private val reviewsRepository: ReviewsRepository,
    rxSchedulers: RxSchedulers
) : UseCaseSingle<ReviewsResultPage, Unit>(rxSchedulers) {

    override fun buildUseCaseObservable(params: Unit): Single<ReviewsResultPage> {
        return reviewsRepository.loadReviews()
    }

}
