package com.cheise_proj.sms_app.di.module.rxjava

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


interface RxSchedulers {
    fun provideIOSchedulers(): Scheduler
    fun provideAndroidSchedulers(): Scheduler
}

class RxSchedulersImpl @Inject constructor() : RxSchedulers {
    override fun provideIOSchedulers(): Scheduler = Schedulers.io()

    override fun provideAndroidSchedulers(): Scheduler = AndroidSchedulers.mainThread()
}