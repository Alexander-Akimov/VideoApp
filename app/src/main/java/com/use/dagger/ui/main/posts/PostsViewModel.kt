package com.use.dagger.ui.main.posts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.use.dagger.SessionManager
import com.use.dagger.models.Post
import com.use.dagger.network.main.MainApi
import com.use.dagger.ui.main.Resource
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostsViewModel : ViewModel {

    private val TAG = PostsViewModel::class.simpleName

    private val sessionManager: SessionManager
    private val mainApi: MainApi

    private lateinit var posts: MediatorLiveData<Resource<List<Post>>>

    @Inject
    constructor(sessionManager: SessionManager, mainApi: MainApi) {
        this.sessionManager = sessionManager
        this.mainApi = mainApi
        //this.observeAuthState = this.sessionManager.authUser

        Log.d(TAG, "PostsViewModel: viewmodel is working...")
    }

    fun observePosts(): LiveData<Resource<List<Post>>> {

        posts = MediatorLiveData()
        posts.value = Resource.loading(null)

        val userId = this.sessionManager.authUser.value?.data?.id//todo: need to be observing

        if (userId != null) {
            val source = LiveDataReactiveStreams.fromPublisher(

                mainApi.getPostsFromUser(userId)
                    .onErrorReturn {
                        Log.d(TAG, "apply: ", it)
                        val posts: MutableList<Post> = mutableListOf()
                        posts.add(Post(-1))
                        posts
                    }
                    .map { posts ->
                        if (posts.isNotEmpty()) {
                            if (posts[1].id == -1) {
                                Resource.error("Something went wrong", null)
                            }
                        }
                        Resource.success(posts)
                    }
                    .subscribeOn(Schedulers.io())//subscribe on background thread
            )

            posts.addSource(source) { listResource ->
                posts.value = listResource
                posts.removeSource(source)
            }
        }

        return posts
    }
}