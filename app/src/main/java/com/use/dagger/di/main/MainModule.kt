package com.use.dagger.di.main

import com.use.dagger.network.main.MainApi
import com.use.dagger.ui.main.posts.PostsRecyclerAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {

    @Provides
    fun provideMainApi(retrofit: Retrofit): MainApi =
        retrofit.create(MainApi::class.java)

    @Provides
    fun providePostsRecyclerAdapter() = PostsRecyclerAdapter()

}