package com.cheise_proj.sms_app.di.module


import com.cheise_proj.core.shared.ui.auth.AuthenticationActivity
import com.cheise_proj.core.shared.ui.auth.module.AuthenticationFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {
    @ContributesAndroidInjector(modules = [AuthenticationFragmentModule::class])
    fun contributeAuthActivity(): AuthenticationActivity
}

@Module
interface FragmentModule {

}
