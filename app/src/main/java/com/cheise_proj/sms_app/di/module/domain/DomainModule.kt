package com.cheise_proj.sms_app.di.module.domain

import com.cheise_proj.domain.qualifiers.Background
import com.cheise_proj.domain.rx.qualifier.Foreground
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

@Module
class DomainModule {

    @Provides
    @Background
    fun provideBackgroundScheduler(): Scheduler = Schedulers.io()

    @Provides
    @Foreground
    fun provideForegroundScheduler(): Scheduler = AndroidSchedulers.mainThread()

}
