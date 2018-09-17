package io.petros.reviews.data.di.dagger

import dagger.Binds
import dagger.Module
import io.petros.reviews.data.repository.review.ReviewsRepositoryImpl
import io.petros.reviews.domain.repository.review.ReviewsRepository

@Module
abstract class RepositoriesModule {

    @Binds
    abstract fun bindReviewsRepository(reviewsRepository: ReviewsRepositoryImpl): ReviewsRepository

}
