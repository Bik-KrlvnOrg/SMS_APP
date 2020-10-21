package com.cheise_proj.sms_app.di.module

import android.app.Application
import android.content.Context
import com.cheise_proj.core.utils.NetworkState
import com.cheise_proj.sms_app.utils.NetworkStateImpl
import com.cheise_proj.sms_app.di.module.remote.RemoteModule
import com.cheise_proj.sms_app.di.module.repository.RepositoryModule
import com.cheise_proj.sms_app.di.module.room.RoomModule
import com.cheise_proj.sms_app.di.module.viewmodel.ViewModelModule
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes =
    [AppModule.Binders::class,
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
    }

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext


}