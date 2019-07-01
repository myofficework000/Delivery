package net.app.delivery.ui.post

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import net.app.delivery.R
import net.app.delivery.base.BaseViewModel
import net.app.delivery.model.Post
import net.app.delivery.model.DeliveryDao
import net.app.delivery.network.PostApi
import net.app.delivery.utils.Limit
import net.app.delivery.utils.Ofset
import net.app.delivery.utils.RecyclerViewClickListener
import javax.inject.Inject

class PostListViewModel(private val deliveryDao: DeliveryDao, private val listener: RecyclerViewClickListener): BaseViewModel(){
    @Inject
    lateinit var postApi: PostApi
    val postListAdapter: PostListAdapter = PostListAdapter(listener)

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPosts(Ofset, Limit) }

    private lateinit var subscription: Disposable

    init{
        loadPosts(Ofset , Limit)
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

     fun loadPosts(offset:Int , limit : Int){
        subscription = Observable.fromCallable { deliveryDao.all }
                .concatMap {
                    dbPostList ->
                        if(dbPostList.isEmpty())
                            postApi.getPosts(offset , limit).concatMap {
                                            apiPostList -> deliveryDao.insertAll(*apiPostList.toTypedArray())
                                 Observable.just(apiPostList)
                                       }
                        else
                            Observable.just(dbPostList)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrievePostListStart() }
                .doOnTerminate { onRetrievePostListFinish() }
                .subscribe(
                        { result -> onRetrievePostListSuccess(result) },
                        { error-> onRetrievePostListError(error) }
                )
    }

    private fun onRetrievePostListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrievePostListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(postList:List<Post>){
        postListAdapter.updatePostList(postList)
    }

    private fun onRetrievePostListError(error: Throwable) {
        Log.e("Error Rx: ", error.localizedMessage)
        errorMessage.value = R.string.post_error
    }

}