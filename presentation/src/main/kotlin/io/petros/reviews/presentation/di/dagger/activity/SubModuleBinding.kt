package io.petros.reviews.presentation.di.dagger.activity

import android.support.v7.app.AppCompatActivity
import dagger.Binds
import dagger.Module
import io.petros.reviews.presentation.navi.manual.ActivityLauncher
import io.petros.reviews.presentation.navi.manual.ActivityNavigator
import io.petros.reviews.presentation.navi.manual.Launcher
import io.petros.reviews.presentation.navi.manual.Navigator

@Module
interface SubModuleBinding<in Activity : AppCompatActivity> {

    @Binds
    fun bindActivity(activity: Activity): AppCompatActivity

    @Binds
    fun bindNavigator(navigator: ActivityNavigator): Navigator

    @Binds
    fun bindLauncher(launcher: ActivityLauncher): Launcher

}
