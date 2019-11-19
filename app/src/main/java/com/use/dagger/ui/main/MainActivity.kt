package com.use.dagger.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.use.dagger.BaseActivity

import com.use.dagger.R
import com.use.dagger.ui.main.posts.PostsFragment
import com.use.dagger.ui.main.profile.ProfileFragment
import com.use.dagger.ui.main.profile.ProfileViewModel
import com.use.dagger.viewmodels.ViewModelProviderFactory

import kotlinx.android.synthetic.main.activity_main.toolbar
import javax.inject.Inject


class MainActivity : BaseActivity() {

    private val TAG = MainActivity::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        //Toast.makeText(this, "MainActivity", Toast.LENGTH_SHORT).show()
        testFragment()
    }

    private fun testFragment(){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, PostsFragment())
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.logout -> {
                sessionManager.logOut()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
