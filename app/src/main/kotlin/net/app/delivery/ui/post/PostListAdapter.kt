package net.app.delivery.ui.post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.squareup.picasso.Picasso
import net.app.delivery.R
import net.app.delivery.databinding.ItemPostBinding
import net.app.delivery.model.Post
import net.app.delivery.utils.RecyclerViewClickListener

class PostListAdapter(private val listener: RecyclerViewClickListener): androidx.recyclerview.widget.RecyclerView.Adapter<PostListAdapter.ViewHolder>() {
    private var postList:MutableList<Post> = arrayListOf()
    lateinit var binding :ItemPostBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListAdapter.ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_post, parent, false)
        return ViewHolder(binding,listener)
    }

    override fun onBindViewHolder(holder: PostListAdapter.ViewHolder, position: Int) {
        holder.bind(postList[position])
    }

    override fun getItemCount(): Int {
        return  postList.size
    }

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)

        Picasso.with(holder.itemView.getContext())
                .cancelRequest(binding.postBody);
        Picasso.with(holder.itemView.getContext())
                .load(android.R.drawable.ic_menu_gallery)
                .centerCrop()
                .resize(50, 50)
                .into(binding.postBody);
    }

    override fun getItemId(position: Int): Long {
        return postList.get(position).id.toLong()
    }

    fun updatePostList(postList:List<Post>){
        this.postList.addAll(postList)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemPostBinding, private val listener: RecyclerViewClickListener): androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root){
        private val viewModel = PostViewModel()

        fun bind(post:Post){
            viewModel.bind(post)
            binding.viewModel = viewModel
            binding.root.setOnClickListener(View.OnClickListener {
                listener.onClick(viewModel.getLocationDetails().value!!,viewModel.getPostTitle().value!!,viewModel.getPostBody().value!!)
            })
        }
    }
}