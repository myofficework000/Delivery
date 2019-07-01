package net.app.delivery.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DeliveryDao {
    @get:Query("SELECT * FROM post")
    val all: List<Post>

    @Insert
    fun insertAll(vararg users: Post)
}