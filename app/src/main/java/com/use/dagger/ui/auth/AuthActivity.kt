package com.use.dagger.ui.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.use.dagger.R
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


        setLogo()
    }

    private fun setLogo() {
        requestManager
            .load(logo)
            .into(login_logo)
    }
}