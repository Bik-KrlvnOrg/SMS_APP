package com.cheise_proj.sms_app.di.module.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cheise_proj.core.shared.ui.auth.login.LoginViewModel
import com.cheise_proj.core.shared.viewmodel.ViewModelFactory
import com.cheise_proj.sms_app.di.key.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule.Binders::class])
class ViewModelModule {

    @Module
    interface Binders {
        @Binds
        fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory


        @Binds
        @IntoMap
        @ViewModelKey(LoginViewModel::class)
        fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    }


}