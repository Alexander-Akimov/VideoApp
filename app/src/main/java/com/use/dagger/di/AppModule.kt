package com.use.dagger.di

import dagger.Module
import dagger.Provides

@Module
public class AppModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun someString() = "this is a test string"
    }
}