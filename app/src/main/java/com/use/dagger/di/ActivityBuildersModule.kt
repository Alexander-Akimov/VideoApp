package com.use.dagger.di

import com.use.dagger.di.auth.AuthModule
import com.use.dagger.di.auth.AuthViewModelsModule
import com.use.dagger.di.main.MainFragmentBuildersModule
import com.use.dagger.di.main.MainModule
import com.use.dagger.di.main.MainViewModelsModule
import com.use.dagger.ui.auth.AuthActivity
import com.use.dagger.ui.auth.AuthViewModel
import com.use.dagger.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    // let Dagger to Know that AuthActivity is potential client,
    // that I can inject into
    @ContributesAndroidInjector(modules = [AuthViewModelsModule::class, AuthModule::class])
    abstract fun contributeAuthActivity(): AuthActivity

    @ContributesAndroidInjector(modules = [MainFragmentBuildersModule::class,/**/
        MainViewModelsModule::class, MainModule::class])
    abstract fun contributeMainActivity(): MainActivity
}