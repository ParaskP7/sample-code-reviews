package io.petros.reviews.data.reactive.rx

import io.petros.reviews.domain.reactive.rx.RxSchedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object RxSchedulersProvider {

    fun rxSchedulers(): RxSchedulers = RxSchedulers(Schedulers.io(), AndroidSchedulers.mainThread())

}
