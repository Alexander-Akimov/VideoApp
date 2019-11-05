package com.use.dagger.di.auth

import com.use.dagger.di.auth.AuthModule
import com.use.dagger.di.auth.AuthViewModelsModule
import com.use.dagger.ui.auth.AuthActivity
import com.use.dagger.ui.auth.AuthViewModel
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AuthActivityBuildersModule {

    // let Dagger to Know that AuthActivity is potential client,
    // that I can inject into
    @ContributesAndroidInjector(modules = [AuthViewModelsModule::class, AuthModule::class])
    abstract fun contributeAuthActivity(): AuthActivity
}