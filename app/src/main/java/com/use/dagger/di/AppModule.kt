package com.use.dagger.di

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.use.dagger.R
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideRequestOptions(): RequestOptions =
        RequestOptions
            .placeholderOf(R.drawable.white_background)
            .error(R.drawable.white_background)

    @Provides
    fun provideGlideInstance(application: Application, requestOptions: RequestOptions): RequestManager =
        Glide.with(application)
            .setDefaultRequestOptions(requestOptions)

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