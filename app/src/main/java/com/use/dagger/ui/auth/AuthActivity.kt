package com.use.dagger.ui.auth

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.use.dagger.R
import com.use.dagger.models.User
import com.use.dagger.ui.main.MainActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject
import  com.use.dagger.viewmodels.ViewModelProviderFactory

class AuthActivity : DaggerAppCompatActivity() {

    private val TAG = AuthActivity::class.simpleName

    private lateinit var viewModel: AuthViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    var logo: Drawable? = null
        @Inject set

    lateinit var requestManager: RequestManager
        @Inject set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        viewModel = ViewModelProvider(this, providerFactory).get(AuthViewModel::class.java)

        //setLogo()

        subscribeObservers()
    }

    private fun subscribeObservers() {
        // Create the observer which log user email
        val userObserver = Observer<AuthResource<User>> { userAuthResource ->
            if (userAuthResource != null) {
                when (userAuthResource.status) {
                    AuthResource.AuthStatus.LOADING -> {
                        showProgressBar(true)
                    }
                    AuthResource.AuthStatus.AUTHENTICATED -> {
                        showProgressBar(false)
                        Log.d(TAG, "onChanged: LOGIN SUCCESS: ${userAuthResource.data?.email}")
                        onLoginSuccess()
                    }
                    AuthResource.AuthStatus.ERROR -> {
                        showProgressBar(false)
                        Toast.makeText(this, userAuthResource.message, Toast.LENGTH_SHORT).show()
                    }
                    AuthResource.AuthStatus.NOT_AUTHENTICATED -> {
                        showProgressBar(false)
                    }
                }
            }
        }

        viewModel.observeAuthState.observe(this, userObserver)
    }

    private fun showProgressBar(isVisible: Boolean) {
        if (isVisible) {
            progress_bar.visibility = View.VISIBLE
        } else {
            progress_bar.visibility = View.GONE
        }
    }

    private fun onLoginSuccess() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun setLogo() {
        requestManager
            .load(logo)
            .into(login_logo)
    }

    fun loginBtnClicked(view: View) {
        val userIdStr = user_id_input.text.toString()
        if (TextUtils.isEmpty(userIdStr))
            return
        viewModel.authenticateWithId(userIdStr.toInt())
    }
}