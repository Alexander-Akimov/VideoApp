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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.use.dagger.R
import com.use.dagger.models.Post
import com.use.dagger.ui.main.Resource
import com.use.dagger.util.VerticalSpacingItemDecoration
import com.use.dagger.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_posts.*
import javax.inject.Inject
import kotlin.reflect.typeOf

class PostsFragment : DaggerFragment() {

    private val TAG = PostsFragment::class.simpleName

    private lateinit var viewModel: PostsViewModel

    private lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var adapter: PostsRecyclerAdapter

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
        recyclerView = recycler_view

        initRecyclerView()
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.observePosts().removeObservers(viewLifecycleOwner)

        val postsObserver = Observer<Resource<List<Post>>> { listResource ->
            if (listResource.data != null) {
                when (listResource.status) {

                    Resource.Status.SUCCESS -> {
                        Log.d(TAG, "onChanged: get posts...")
                        adapter.setPosts(listResource.data)
                    }

                    Resource.Status.ERROR ->
                        Log.d(TAG, "onChanged: ERROR... ${listResource.message}")

                    Resource.Status.LOADING -> Log.d(TAG, "onChanged: LOADING...")
                }
            }
        }

        viewModel.observePosts().observe(viewLifecycleOwner, postsObserver)
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager =
            LinearLayoutManager(activity) // could also provide this as dependency
        val itemDecoration =
            VerticalSpacingItemDecoration(15)// could also provide this as dependency
        recyclerView.addItemDecoration(itemDecoration)
        recyclerView.adapter = this.adapter
    }
}