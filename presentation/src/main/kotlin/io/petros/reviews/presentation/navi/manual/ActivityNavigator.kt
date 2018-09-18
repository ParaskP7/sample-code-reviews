package io.petros.reviews.presentation.navi.manual

import javax.inject.Inject

open class ActivityNavigator : Navigator { // MUT

    @Inject lateinit var launcher: Launcher

    override fun finish() {
        launcher.finish()
    }

}
