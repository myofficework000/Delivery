package net.app.delivery.base

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import net.app.delivery.injection.component.ApplicationComponent
import net.app.delivery.injection.module.NetworkModule
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import net.app.delivery.injection.module.DbModule
import javax.inject.Inject

class MyApplication : Application()
{

    /* @Inject
     lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

     override fun activityInjector(): DispatchingAndroidInjector<Activity>? {
         return dispatchingAndroidInjector
     }*/

    override fun onCreate() {
        super.onCreate()

        /*  val injector: ApplicationComponent = DaggerApplicationComponent
                 .builder()
                 .networkModule(NetworkModule)
                 .build()*/
        /*DaggerApplicationComponent.builder()
                .application(this)
                .networkModule(NetworkModule)
                .dbModule(DbModule())
                .build()
                .inject(this)*/
    }
}