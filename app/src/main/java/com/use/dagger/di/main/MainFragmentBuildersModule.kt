package com.use.dagger.di.main

import com.use.dagger.ui.main.posts.PostsFragment
import com.use.dagger.ui.main.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun contributePostsFragment(): PostsFragment
}