package io.petros.reviews.presentation.di.dagger.activity

import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.petros.reviews.presentation.di.dagger.viewmodel.ViewModelFactory
import io.petros.reviews.presentation.feature.reviews.ReviewsActivity
import io.petros.reviews.presentation.feature.reviews.ReviewsActivitySubModule

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector(modules = [ReviewsActivitySubModule::class])
    abstract fun bindsReviewsActivity(): ReviewsActivity

    /* VIEW MODEL */

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}
