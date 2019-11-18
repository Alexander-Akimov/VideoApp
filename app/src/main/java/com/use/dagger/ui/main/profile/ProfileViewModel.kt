package com.use.dagger.ui.main.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.use.dagger.SessionManager
import com.use.dagger.models.User
import com.use.dagger.ui.auth.AuthResource
import javax.inject.Inject

class ProfileViewModel : ViewModel {

    private val TAG = ProfileViewModel::class.simpleName

    private var sessionManager: SessionManager

    var observeAuthState: LiveData<AuthResource<User>>

    @Inject
    constructor(sessionManager: SessionManager) {
        Log.d(TAG, "ProfileViewModel: Viewmodel is ready...")
        this.sessionManager = sessionManager
        this.observeAuthState = this.sessionManager.authUser
    }
}