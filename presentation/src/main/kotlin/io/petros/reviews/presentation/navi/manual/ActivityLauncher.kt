package io.petros.reviews.presentation.navi.manual

import android.support.v7.app.AppCompatActivity
import javax.inject.Inject

open class ActivityLauncher @Inject constructor( // MRT
    private val activity: AppCompatActivity
) : Launcher {

    override fun finish() {
        activity.finish()
    }

}
