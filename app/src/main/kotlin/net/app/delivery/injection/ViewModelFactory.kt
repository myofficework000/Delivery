package net.app.delivery.injection

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import net.app.delivery.model.database.AppDatabase
import net.app.delivery.ui.post.PostListViewModel
import net.app.delivery.ui.post.PostViewModel
import net.app.delivery.utils.RecyclerViewClickListener

class ViewModelFactory(private val context : Context, private val listener: RecyclerViewClickListener): ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostListViewModel::class.java)) {
           val db = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "deliveries").build()
            @Suppress("UNCHECKED_CAST")
            return PostListViewModel(db.postDao(),listener) as T
        }
        if (modelClass.isAssignableFrom(PostViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PostViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}