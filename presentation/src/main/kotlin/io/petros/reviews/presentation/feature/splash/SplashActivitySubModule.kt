package io.petros.reviews.presentation.feature.splash

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.petros.reviews.presentation.di.dagger.activity.SubModuleBinding
import io.petros.reviews.presentation.di.dagger.viewmodel.ViewModelKey
import io.petros.reviews.presentation.feature.reviews.navigator.ReviewsActivityLauncher
import io.petros.reviews.presentation.feature.reviews.navigator.ReviewsLauncher
import io.petros.reviews.presentation.feature.splash.navigator.SplashActivityNavigator
import io.petros.reviews.presentation.feature.splash.navigator.SplashNavigator

@Module
abstract class SplashActivitySubModule : SubModuleBinding<SplashActivity> {

    @Binds
    @IntoMap
    @ViewModelKey(SplashActivityViewModel::class)
    abstract fun bindSplashActivityViewModel(splashActivityViewModel: SplashActivityViewModel): ViewModel

    @Binds
    abstract fun bindSplashNavigator(splashNavigator: SplashActivityNavigator): SplashNavigator

    @Binds
    abstract fun bindReviewsLauncher(reviewsLauncher: ReviewsActivityLauncher): ReviewsLauncher

}
