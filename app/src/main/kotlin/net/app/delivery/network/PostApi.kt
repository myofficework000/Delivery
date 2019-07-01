package net.app.delivery.network

import io.reactivex.Observable
import net.app.delivery.model.Post
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * The interface which provides methods to get result of delivery webservices
 */
interface PostApi {
    /**
     * Get the list of the deliveries from the API
     */
    @GET("/deliveries")
    fun getPosts(@Query("offset") offset:Int,@Query("limit") limit:Int): Observable<List<Post>>
}