package net.app.delivery.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import net.app.delivery.model.Post
import net.app.delivery.model.DeliveryDao

@Database(entities = [Post::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): DeliveryDao
}