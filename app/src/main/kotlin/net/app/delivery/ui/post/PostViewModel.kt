package net.app.delivery.ui.post

import androidx.lifecycle.MutableLiveData
import net.app.delivery.base.BaseViewModel
import net.app.delivery.model.LocationDetails
import net.app.delivery.model.Post


class PostViewModel: BaseViewModel() {
    private val postTitle = MutableLiveData<String>()
    private val postBody = MutableLiveData<String>()
    private val locationDetails = MutableLiveData<LocationDetails>()
    private val addressVal =MutableLiveData<String>()

    fun bind(post: Post){
        postTitle.value = post.description
        postBody.value = post.imageUrl
        locationDetails.value=post.location
        addressVal.value=post.location.address
    }

    fun getPostTitle():MutableLiveData<String>{
        return postTitle
    }

    fun getPostBody():MutableLiveData<String>{
        return postBody
    }

    fun getAddress():MutableLiveData<String>{
        return addressVal
    }

    fun getLocationDetails():MutableLiveData<LocationDetails>{
        return locationDetails
    }
}