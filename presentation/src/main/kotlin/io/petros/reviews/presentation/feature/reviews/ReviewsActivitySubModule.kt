package io.petros.reviews.presentation.feature.reviews

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.petros.reviews.presentation.di.dagger.activity.SubModuleBinding
import io.petros.reviews.presentation.di.dagger.viewmodel.ViewModelKey

@Module
abstract class ReviewsActivitySubModule : SubModuleBinding<ReviewsActivity> {

    @Binds
    @IntoMap
    @ViewModelKey(ReviewsActivityViewModel::class)
    abstract fun bindReviewsActivityViewModel(reviewsActivityViewModel: ReviewsActivityViewModel): ViewModel

}
