package net.app.delivery.injection.component

import dagger.BindsInstance
import dagger.Component
import net.app.delivery.base.MyApplication
import net.app.delivery.injection.module.DbModule
import net.app.delivery.injection.module.NetworkModule
import net.app.delivery.ui.post.PostListViewModel
import net.app.delivery.ui.post.PostViewModel
import javax.inject.Singleton


@Singleton
@Component(modules = [(NetworkModule::class) , ( DbModule::class)])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        fun build(): ApplicationComponent
        fun networkModule(networkModule: NetworkModule): Builder
        fun dbModule(dbModule: DbModule): Builder
    }

    fun inject(application: MyApplication)
    fun inject(postListViewModel: PostListViewModel)
    fun inject(postViewModel: PostViewModel)
}