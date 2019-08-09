package com.use.dagger

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {

    companion object {
        private val TAG = AuthActivity::class.simpleName
    }

    var logo: Drawable? = null
        @Inject set

    lateinit var requestManager: RequestManager
        @Inject set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        setLogo()
    }

    private fun setLogo() {
        requestManager
            .load(logo)
            .into(login_logo)
    }
}