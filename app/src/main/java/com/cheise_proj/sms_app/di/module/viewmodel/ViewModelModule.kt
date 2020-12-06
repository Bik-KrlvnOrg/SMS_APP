package com.cheise_proj.sms_app.di.module.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cheise_proj.core.feature.student.ui.fees.FeesPaidViewModel
import com.cheise_proj.core.feature.student.ui.profile.ProfileViewModel
import com.cheise_proj.core.shared.ui.auth.login.LoginViewModel
import com.cheise_proj.core.shared.ui.auth.splash.SplashViewModel
import com.cheise_proj.core.shared.viewmodel.ViewModelFactory
import com.cheise_proj.sms_app.di.key.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module(includes = [ViewModelModule.Binders::class])
class ViewModelModule {

    @Module
    interface Binders {
        @Binds
        fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory


        @Binds
        @IntoMap
        @ViewModelKey(FeesPaidViewModel::class)
        fun bindFeesPaidViewModel(feesPaidViewModel: FeesPaidViewModel): ViewModel

        @Binds
        @IntoMap
        @ViewModelKey(LoginViewModel::class)
        fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

        @Binds
        @IntoMap
        @ViewModelKey(SplashViewModel::class)
        fun bindSplashViewModel(splashViewModel: SplashViewModel): ViewModel

        @ExperimentalCoroutinesApi
        @Binds
        @IntoMap
        @ViewModelKey(ProfileViewModel::class)
        fun binProfileLViewModel(profileViewModel: ProfileViewModel): ViewModel

    }


}