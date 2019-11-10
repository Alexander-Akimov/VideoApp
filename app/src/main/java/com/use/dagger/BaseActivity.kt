package com.use.dagger

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.use.dagger.models.User
import com.use.dagger.ui.auth.AuthActivity
import com.use.dagger.ui.auth.AuthResource
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var sessionManager: SessionManager

    private val TAG = BaseActivity::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        subscribeObservers()
    }

    private fun subscribeObservers() {
        val userObserver = Observer<AuthResource<User>> { userAuthResource ->
            if (userAuthResource != null) {
                when (userAuthResource.status) {
                    AuthResource.AuthStatus.LOADING -> {
                    }
                    AuthResource.AuthStatus.AUTHENTICATED -> {
                        Log.d(TAG, "onChanged: LOGIN SUCCESS: ${userAuthResource.data?.email}")
                    }
                    AuthResource.AuthStatus.ERROR -> {
                        Toast.makeText(this, userAuthResource.message, Toast.LENGTH_SHORT).show()
                    }
                    AuthResource.AuthStatus.NOT_AUTHENTICATED -> {
                        navLoginScreen()
                    }
                }
            }
        }
        this.sessionManager.authUser.observe(this, userObserver)
    }

    private fun navLoginScreen() {
        val intent: Intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }
}