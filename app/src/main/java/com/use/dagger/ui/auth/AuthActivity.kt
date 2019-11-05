package com.use.dagger.ui.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.use.dagger.R
import com.use.dagger.models.User
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject
import  com.use.dagger.viewmodels.ViewModelProviderFactory
import kotlin.math.log

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

        setLogo()

        // Create the observer which log user email
        val userObserver = Observer<User> { user ->
            if (user != null) {
                Log.d(TAG, "onChanged:${user.email}")
            }
        }

        viewModel.authUser.observe(this, userObserver)
    }

    private fun setLogo() {
        requestManager
            .load(logo)
            .into(login_logo)
    }

    public fun loginBtnClicked(v: View) {
        val userIdStr = user_id_input.text.toString()
        if (TextUtils.isEmpty(userIdStr))
            return
        viewModel.authenticateWithId(userIdStr.toInt())
    }
}