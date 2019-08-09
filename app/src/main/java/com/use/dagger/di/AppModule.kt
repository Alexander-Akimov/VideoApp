package com.use.dagger.di

import android.app.Application
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

    @Provides
    fun getApp(application: Application?): Boolean {
        return application == null
    }
}