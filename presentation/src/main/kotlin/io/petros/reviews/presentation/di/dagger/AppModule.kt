package io.petros.reviews.presentation.di.dagger

import android.content.Context
import dagger.Module
import dagger.Provides
import io.petros.reviews.data.di.dagger.NetworkModule
import io.petros.reviews.data.di.dagger.RepositoriesModule
import io.petros.reviews.data.di.dagger.SchedulersModule
import io.petros.reviews.presentation.App

@Module(
    includes = [
        SchedulersModule::class,
        RepositoriesModule::class,
        NetworkModule::class
    ]
)
class AppModule {

    @Provides
    fun provideContext(app: App): Context = app.applicationContext

}
