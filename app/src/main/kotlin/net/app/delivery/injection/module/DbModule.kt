package net.app.delivery.injection.module

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import net.app.delivery.model.DeliveryDao
import net.app.delivery.model.database.AppDatabase
import javax.inject.Singleton

@Suppress("unused")
@Module
object  DbModule {

    /*
     * The method returns the Database object
     * */
    @Provides
    @Singleton
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
                application, AppDatabase::class.java, "Delivery.db")
                .allowMainThreadQueries().build()
    }


    /*
     * We need the MovieDao module.
     * For this, We need the AppDatabase object
     * So we will define the providers for this here in this module.
     * */
    @Provides
    @Singleton
    fun provideDeliveryDao(appDatabase: AppDatabase): DeliveryDao {
        return appDatabase.postDao()
    }
}