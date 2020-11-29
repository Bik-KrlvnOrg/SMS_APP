package com.cheise_proj.sms_app.di.module

import android.app.Application
import android.content.Context
import com.cheise_proj.core.utils.NetworkState
import com.cheise_proj.infrastructure.utils.JwtService
import com.cheise_proj.sms_app.di.module.domain.DomainModule
import com.cheise_proj.sms_app.di.module.rxjava.RxModule
import com.cheise_proj.sms_app.di.module.infrastructure.remote.RemoteModule
import com.cheise_proj.sms_app.di.module.infrastructure.repository.RepositoryModule
import com.cheise_proj.sms_app.di.module.infrastructure.local.RoomModule
import com.cheise_proj.sms_app.di.module.infrastructure.utils.JwtServiceImpl
import com.cheise_proj.sms_app.di.module.viewmodel.ViewModelModule
import com.cheise_proj.sms_app.utils.NetworkStateImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes =
    [AppModule.Binders::class,
        RxModule::class,
        DomainModule::class,
        RepositoryModule::class,
        RemoteModule::class,
        ViewModelModule::class,
        RoomModule::class
    ]
)
class AppModule {

    @Module
    interface Binders {
        @Binds
        fun bindNetworkState(networkStateImpl: NetworkStateImpl): NetworkState

        @Binds
        fun bindJwtService(jwtServiceImpl: JwtServiceImpl): JwtService
    }

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext


}