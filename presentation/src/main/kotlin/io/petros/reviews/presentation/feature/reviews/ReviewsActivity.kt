package io.petros.reviews.presentation.feature.reviews

import io.petros.reviews.R
import io.petros.reviews.presentation.feature.BaseActivity

class ReviewsActivity : BaseActivity<ReviewsActivityViewModel>() {

    /* CONTRACT */

    override fun constructContentView() = R.layout.activity_reviews

    override fun constructViewModel() = ReviewsActivityViewModel::class.java

}
