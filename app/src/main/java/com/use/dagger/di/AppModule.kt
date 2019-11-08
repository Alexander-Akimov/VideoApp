package com.use.dagger.di

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.use.dagger.R
import com.use.dagger.SessionManager
import com.use.dagger.util.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun sessionManager() = SessionManager()

    @Singleton
    @Provides
    fun provideRetrofitInstance(): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideRequestOptions(): RequestOptions =
        RequestOptions
            .placeholderOf(R.drawable.white_background)
            .error(R.drawable.white_background)

    @Singleton
    @Provides
    fun provideGlideInstance(application: Application, requestOptions: RequestOptions): RequestManager =
        Glide.with(application)
            .setDefaultRequestOptions(requestOptions)

    @Singleton
    @Provides
    fun provideAppDrawable(application: Application): Drawable? =
        ContextCompat.getDrawable(application, R.drawable.logo)

    /*@Module
    companion object {
    //    @JvmStatic
        @JvmStatic
        @Provides
        fun provideGlideInstance(application: Application, requestOptions: RequestOptions): RequestManager =
            Glide.with(application)
                .setDefaultRequestOptions(requestOptions)

        @Provides
        @JvmStatic
        fun provideAppDrawable(application: Application): Drawable? =
            ContextCompat.getDrawable(application, R.drawable.logo)
    }*/
}