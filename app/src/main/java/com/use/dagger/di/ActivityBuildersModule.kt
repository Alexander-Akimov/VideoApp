package com.use.dagger.di

import com.use.dagger.di.auth.AuthViewModelsModule
import com.use.dagger.ui.auth.AuthActivity
import com.use.dagger.ui.auth.AuthViewModel
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [AuthViewModelsModule::class])
    abstract fun contributeAuthActivity(): AuthActivity //let Dagger to Know that AuthActivity is potential client, that i can inject into
}