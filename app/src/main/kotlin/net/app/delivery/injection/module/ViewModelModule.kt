package net.app.delivery.injection.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import net.app.delivery.injection.ViewModelFactory
import net.app.delivery.ui.post.PostListViewModel
import net.app.delivery.ui.post.PostViewModel

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PostViewModel::class)
    protected abstract fun movieListViewModel(postViewModel: PostViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(PostListViewModel::class)
    protected abstract fun movieDetailViewModel(postListViewModel: PostListViewModel): ViewModel

}