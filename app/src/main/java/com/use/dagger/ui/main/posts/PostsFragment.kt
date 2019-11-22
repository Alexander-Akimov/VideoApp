package com.use.dagger.ui.main.posts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.use.dagger.R
import com.use.dagger.models.Post
import com.use.dagger.ui.main.Resource
import com.use.dagger.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import kotlin.reflect.typeOf

class PostsFragment : DaggerFragment() {

    private val TAG = PostsFragment::class.simpleName

    private lateinit var viewModel: PostsViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this, providerFactory).get(PostsViewModel::class.java)

        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.observePosts().removeObservers(viewLifecycleOwner)

        val postsObserver = Observer<Resource<List<Post>>> { listResource ->
            if (listResource.data != null)
                Log.d(TAG, "onChanged: " + listResource.data)

        }

        viewModel.observePosts().observe(viewLifecycleOwner, postsObserver)
    }
}