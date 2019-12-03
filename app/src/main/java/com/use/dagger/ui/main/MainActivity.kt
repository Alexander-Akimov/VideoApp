package com.use.dagger.ui.main

import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.use.dagger.BaseActivity

import com.use.dagger.R
import com.use.dagger.ui.main.posts.PostsFragment
import com.use.dagger.ui.main.profile.ProfileFragment
import com.use.dagger.ui.main.profile.ProfileViewModel
import com.use.dagger.viewmodels.ViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_main.*

import javax.inject.Inject


class MainActivity : BaseActivity() {

    private val TAG = MainActivity::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setSupportActionBar(toolbar)
        //Toast.makeText(this, "MainActivity", Toast.LENGTH_SHORT).show()
        // testFragment()
        init()

    }

    private fun init() {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawer_layout)
        NavigationUI.setupWithNavController(nav_view, navController)

        nav_view.setNavigationItemSelectedListener { this.onNavigationItemSelected(it) }/**/
    }

    /*private fun testFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, PostsFragment())
            .commit()
    }*/

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
            android.R.id.home -> {
                if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                    drawer_layout.closeDrawer(GravityCompat.START)
                    true
                } else
                    false //do not consume a click
            }/**/
            else -> super.onOptionsItemSelected(item)
        }
    }

    //Changes the way back navigation works
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            Navigation.findNavController(this, R.id.nav_host_fragment),
            drawer_layout
        )
    }

    private fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile -> {
                val navOptions = NavOptions.Builder()
                    .setPopUpTo(R.id.main, true)
                    .build()

                Navigation
                    .findNavController(this, R.id.nav_host_fragment)
                    .navigate(
                        R.id.profileScreen,
                        null,
                        navOptions
                    )
            }

            R.id.nav_posts -> {
                if (isValidDestination(R.id.postsScreen)) {
                    Navigation
                        .findNavController(this, R.id.nav_host_fragment)
                        .navigate(R.id.postsScreen)
                }
            }
        }
        item.isChecked = true
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun isValidDestination(destination: Int): Boolean {
        return destination != Navigation.findNavController(
            this,
            R.id.nav_host_fragment
        ).currentDestination?.id
    }
}
