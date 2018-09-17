package io.petros.reviews.presentation.feature.splash.navigator

import io.petros.reviews.presentation.feature.reviews.navigator.ReviewsLauncher
import io.petros.reviews.presentation.navi.manual.ActivityNavigator
import javax.inject.Inject

class SplashActivityNavigator @Inject constructor(
    private val reviewsLauncher: ReviewsLauncher
) : ActivityNavigator(), SplashNavigator {

    override fun navigate() {
        reviewsLauncher.launch()
        launcher.finish()
    }

}
