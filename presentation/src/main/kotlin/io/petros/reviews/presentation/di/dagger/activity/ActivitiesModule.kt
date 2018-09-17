package io.petros.reviews.presentation.di.dagger.activity

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.petros.reviews.presentation.feature.reviews.ReviewsActivity
import io.petros.reviews.presentation.feature.reviews.ReviewsActivitySubModule

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector(modules = [ReviewsActivitySubModule::class])
    abstract fun bindsReviewsActivity(): ReviewsActivity

}
