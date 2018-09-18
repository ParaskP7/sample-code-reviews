package io.petros.reviews.domain.interactor.review

import io.petros.reviews.domain.interactor.UseCaseSingle
import io.petros.reviews.domain.model.place.Tour
import io.petros.reviews.domain.model.review.ReviewsResultPage
import io.petros.reviews.domain.reactive.rx.RxSchedulers
import io.petros.reviews.domain.repository.review.ReviewsRepository
import io.reactivex.Single
import javax.inject.Inject

class LoadReviewsUseCase @Inject constructor(
    private val reviewsRepository: ReviewsRepository,
    rxSchedulers: RxSchedulers
) : UseCaseSingle<ReviewsResultPage, LoadReviewsUseCase.Params>(rxSchedulers) {

    override fun buildUseCaseObservable(params: Params): Single<ReviewsResultPage> {
        return reviewsRepository.loadReviews(params.tour, params.page)
    }

    data class Params constructor(val tour: Tour, val page: Int?) {

        companion object {

            fun with(tour: Tour, page: Int?): Params {
                return Params(tour, page)
            }

        }

    }

}
