package io.petros.reviews.presentation.feature.reviews.navigator

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import io.petros.reviews.presentation.feature.reviews.ReviewsActivity
import io.petros.reviews.presentation.navi.manual.ActivityLauncher
import javax.inject.Inject

class ReviewsActivityLauncher @Inject constructor(
    private val activity: AppCompatActivity
) : ActivityLauncher(activity), ReviewsLauncher {

    override fun launch() {
        activity.startActivity(Intent(activity, ReviewsActivity::class.java))
    }

}
