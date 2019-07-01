package net.app.delivery.base

import androidx.lifecycle.ViewModel
import net.app.delivery.injection.component.DaggerApplicationComponent
import net.app.delivery.injection.component.ApplicationComponent
import net.app.delivery.injection.module.NetworkModule
import net.app.delivery.ui.post.PostListViewModel
import net.app.delivery.ui.post.PostViewModel

abstract class BaseViewModel:ViewModel(){
    private val injector: ApplicationComponent = DaggerApplicationComponent
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is PostListViewModel -> injector.inject(this)
            is PostViewModel -> injector.inject(this)
        }
    }
}