package com.cheise_proj.core.shared.ui.auth.module

import com.cheise_proj.core.shared.ui.auth.login.LoginFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface AuthenticationFragmentModule {
    @ContributesAndroidInjector
    fun contributeLoginFragment(): LoginFragment
}