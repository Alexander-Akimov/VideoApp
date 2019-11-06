package com.use.dagger.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.use.dagger.SessionManager
import com.use.dagger.models.User
import com.use.dagger.network.auth.AuthApi
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.internal.operators.single.SingleInternalHelper.toObservable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class AuthViewModel : ViewModel {

    private val TAG = AuthViewModel::class.simpleName
    private val authApi: AuthApi

    private val sessionManager: SessionManager

    @Inject
    constructor(authApi: AuthApi, sessionManager: SessionManager) {
        Log.d(TAG, "AuthViewModel: viewmodel is working...")
        this.authApi = authApi
        this.sessionManager = sessionManager

        /*val res = if(this.authApi != null) " NOT" else ""
        Log.d(TAG, "AuthApi is$res NULL")*/

        /*    var getUserSubscription = authApi.getUser(1)
                .toObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { value -> getUserSuccess(value) },
                    { error -> errorHandle(error) })*/
    }

    private fun getUserSuccess(user: User) =
        Log.d(TAG, "onNext: " + user.email)

    private fun errorHandle(e: Throwable) =
        Log.e(TAG, "onError: ", e)

    fun authenticateWithId(userId: Int) {
        Log.d(TAG, "authenticateWithId: attmpting to login")

        val source: LiveData<AuthResource<User>> = LiveDataReactiveStreams.fromPublisher(
            authApi.getUser(userId)
                .onErrorReturn { User(-1) }//error happens
                .map {
                    if (it.id == -1)
                        AuthResource.error("Could not authenticate", null)
                    else
                        AuthResource.authenticated(it)
                }
                .subscribeOn(Schedulers.io())
        )
        authUser.addSource(source) { user ->
            authUser.value = user
            authUser.removeSource(source)
        }
    }

    //var observeAuthState: LiveData<AuthResource<User>> = this.sessionManager.authUser
}