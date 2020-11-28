package com.cheise_proj.core.feature.student.module

import com.cheise_proj.core.feature.student.ui.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface StudentFragmentModule {
    @ContributesAndroidInjector
    fun contributeSProfileFragment(): ProfileFragment
}