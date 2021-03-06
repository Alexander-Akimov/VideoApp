package com.use.dagger.di

import androidx.lifecycle.ViewModelProvider
import com.use.dagger.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModel(viewModelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}