package com.use.dagger.di

import com.use.dagger.BaseApplication
import dagger.Component
import dagger.android.AndroidInjector

@Component
interface AppComponent : AndroidInjector<BaseApplication> {

}