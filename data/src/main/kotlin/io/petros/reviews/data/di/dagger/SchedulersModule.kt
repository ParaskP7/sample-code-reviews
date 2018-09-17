package io.petros.reviews.data.di.dagger

import dagger.Module
import dagger.Provides
import io.petros.reviews.data.reactive.rx.RxSchedulersProvider
import io.petros.reviews.domain.reactive.rx.RxSchedulers

@Module
class SchedulersModule {

    @Provides
    fun provideRxSchedulers(): RxSchedulers = RxSchedulersProvider.rxSchedulers()

}
