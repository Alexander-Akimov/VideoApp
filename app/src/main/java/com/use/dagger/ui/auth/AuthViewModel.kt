package com.use.dagger.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import com.use.dagger.network.auth.AuthApi
import javax.inject.Inject


class AuthViewModel : ViewModel {

    private val TAG = AuthViewModel::class.simpleName
    private val authApi: AuthApi?

    @Inject
    constructor(authApi: AuthApi?) {
        Log.d(TAG, "AuthViewModel: viewmodel is working...")
        this.authApi = authApi

        val res = if(this.authApi != null) " NOT" else ""
        Log.d(TAG, "AuthApi is$res NULL")
    }
}