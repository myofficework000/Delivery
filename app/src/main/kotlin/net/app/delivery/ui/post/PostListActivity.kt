package net.app.delivery.ui.post

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.google.android.material.snackbar.Snackbar
import net.app.delivery.R
import net.app.delivery.databinding.ActivityPostListBinding
import net.app.delivery.injection.ViewModelFactory
import net.app.delivery.model.LocationDetails
import net.app.delivery.model.Post
import net.app.delivery.utils.Limit
import net.app.delivery.utils.RecyclerViewClickListener


class PostListActivity: AppCompatActivity() , RecyclerViewClickListener {

    private lateinit var binding: ActivityPostListBinding
    private lateinit var viewModel: PostListViewModel
    private var errorSnackbar: Snackbar? = null


    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?){

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_post_list)
        binding.postList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewModel = ViewModelProviders.of(this, ViewModelFactory(this,this)).get(PostListViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer {
            errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })
        binding.viewModel = viewModel

        //pagination purpous
        var recyclerView: RecyclerView= binding.postList
        val animator = recyclerView.getItemAnimator()
        if (animator is SimpleItemAnimator) {
            (animator as SimpleItemAnimator).supportsChangeAnimations = false
        }

        recyclerView.addOnScrollListener(object :RecyclerView.OnScrollListener(){

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if(dy>0)
                {
                    var visibleItemCount =binding.postList.layoutManager?.getChildCount()
                    var totalItemCount =binding.postList.layoutManager?.getChildCount()
                    var linearLayoutManager :LinearLayoutManager =binding.postList.layoutManager as LinearLayoutManager
                    var pastVisiblesItems =linearLayoutManager.findFirstVisibleItemPosition()

                    if((visibleItemCount?.plus((pastVisiblesItems))!! >= totalItemCount!!)){
                        viewModel.loadPosts(totalItemCount+1, Limit)
                    }
                }
            }
        })
    }

    private fun showError(@StringRes errorMessage:Int){
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError(){
        errorSnackbar?.dismiss()
    }

    override fun onClick(location: LocationDetails , title : String , imageUrl : String) {
        var post : Post =Post(0,title ,imageUrl ,location)
        val intent = Intent(this,MapMarkerActivity::class.java)
        intent.putExtra("deliveryData",post)
        startActivity(intent)
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
    }
}