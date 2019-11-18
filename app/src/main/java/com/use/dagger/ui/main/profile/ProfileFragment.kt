package com.use.dagger.ui.main.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.use.dagger.R
import com.use.dagger.models.User
import com.use.dagger.ui.auth.AuthResource
import com.use.dagger.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject


class ProfileFragment : DaggerFragment() {

    private val TAG = ProfileFragment::class.simpleName

    private lateinit var viewModel: ProfileViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Toast.makeText(activity, "Profile Fragment", Toast.LENGTH_SHORT).show()
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel = ViewModelProvider(this, providerFactory).get(ProfileViewModel::class.java)

        subscribeObservers()
    }


    private fun subscribeObservers() {
        viewModel.observeAuthState.removeObservers(viewLifecycleOwner)

        val userObserver = Observer<AuthResource<User>> { userAuthResource ->
            if (userAuthResource != null) {
                when (userAuthResource.status) {
                    AuthResource.AuthStatus.AUTHENTICATED -> {
                        setUserDetails(userAuthResource.data)
                    }
                    AuthResource.AuthStatus.ERROR -> {
                        setErrorDetails(userAuthResource.message)
                    }
                }
            }
        }

        viewModel.observeAuthState.observe(viewLifecycleOwner, userObserver)
    }

    private fun setErrorDetails(message: String?) {
        email.text = message
        username.text = "error"
        website.text = "error"
    }

    private fun setUserDetails(data: User?) {
        email.text = data?.email
        username.text = data?.userName
        website.text = data?.website
    }
}