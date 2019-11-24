package com.use.dagger.ui.main.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.use.dagger.R
import com.use.dagger.models.Post
import kotlinx.android.synthetic.main.layout_post_list_item.view.*

class PostsRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var posts: List<Post> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_post_list_item, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? PostViewHolder)?.bind(posts[position])
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    fun setPosts(posts: List<Post>) {
        this.posts = posts
        notifyDataSetChanged()
    }

    class PostViewHolder : RecyclerView.ViewHolder {

         var title: TextView

        constructor(view: View) : super(view) {
             title = view.findViewById(R.id.title)
        }

        fun bind(post: Post) {
            title.text = post.title
            //itemView.title.text = post.title
        }
    }
}








