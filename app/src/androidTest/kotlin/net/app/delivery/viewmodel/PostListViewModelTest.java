package net.app.delivery.viewmodel;

import android.app.Application;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import androidx.room.Database;
import androidx.room.Room;
import androidx.test.InstrumentationRegistry;

import net.app.delivery.model.Post;
import net.app.delivery.model.database.AppDatabase;
import net.app.delivery.network.Resource;
import net.app.delivery.ui.post.PostListViewModel;
import net.app.delivery.util.MockTestUtil;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

public class PostListViewModelTest {

    private PostListViewModel postListViewModel;

    @Mock
    Observer<Resource<List<Post>>> observer;


    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void init() {
        Application app =
                (Application) InstrumentationRegistry
                        .getTargetContext()
                        .getApplicationContext();
     //   postListViewModel = new PostListViewModel(app);
    }

    @Test
    public void fetchMovies() {
       // postListViewModel.getLoadingVisibility().observeForever(observer);
        postListViewModel.getPostApi();
        assert(postListViewModel.getLoadingVisibility().equals("True"));
        assert(postListViewModel.getPostApi().getPosts(1,20) == MockTestUtil.mockListDelivery());
    }

}
