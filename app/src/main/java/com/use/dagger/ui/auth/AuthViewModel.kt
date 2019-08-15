package com.use.dagger.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import javax.inject.Inject


class AuthViewModel : ViewModel {

    private val TAG = AuthViewModel::class.simpleName

    @Inject
    constructor() {
        Log.d(TAG, "AuthViewModel: viewmodel is working...")
    }
}