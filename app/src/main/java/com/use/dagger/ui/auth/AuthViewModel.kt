package com.use.dagger.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import com.use.dagger.models.User
import com.use.dagger.network.auth.AuthApi
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.internal.operators.single.SingleInternalHelper.toObservable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class AuthViewModel : ViewModel {

    private val TAG = AuthViewModel::class.simpleName
    private val authApi: AuthApi?

    @Inject
    constructor(authApi: AuthApi) {
        Log.d(TAG, "AuthViewModel: viewmodel is working...")
        this.authApi = authApi

        /*val res = if(this.authApi != null) " NOT" else ""
        Log.d(TAG, "AuthApi is$res NULL")*/

        var getUserSubscription = authApi.getUser(1)
            .toObservable()
            .subscribeOn(Schedulers.io())
            .subscribe(
                { value -> getUserSuccess(value) },
                { error -> errorHandle(error) })
    }

    private fun getUserSuccess(user: User) =
        Log.d(TAG, "onNext: " + user.email)

    private fun errorHandle(e: Throwable) =
        Log.e(TAG, "onError: ", e)

}