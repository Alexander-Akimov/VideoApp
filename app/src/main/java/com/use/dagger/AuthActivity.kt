package com.use.dagger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {

    companion object {
        private val TAG = AuthActivity::class.simpleName
    }

    @Inject
    lateinit var someString: String

    var isAppNull: Boolean? = null
        @Inject set/**/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        Log.d(TAG, "onCreate: $someString")
        Log.d(TAG, "onCreate: is app null? $isAppNull")
    }
}