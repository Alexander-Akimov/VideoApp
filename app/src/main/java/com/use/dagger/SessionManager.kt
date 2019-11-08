package com.use.dagger

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.use.dagger.models.User
import com.use.dagger.ui.auth.AuthResource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {
    private val TAG = SessionManager::class.simpleName

    private var cachedUser: MediatorLiveData<AuthResource<User>> = MediatorLiveData()

    var authUser: LiveData<AuthResource<User>> = cachedUser

    fun auhtenticateWithId(source: LiveData<AuthResource<User>>) {
        cachedUser.value = AuthResource.loading(null)

        cachedUser.addSource(source) { user ->
            cachedUser.value = user
            cachedUser.removeSource(source)
        }
    }

    fun logOut() {
        Log.d(TAG, "logOut: logging out...")
        cachedUser.value = AuthResource.logout()
    }


}