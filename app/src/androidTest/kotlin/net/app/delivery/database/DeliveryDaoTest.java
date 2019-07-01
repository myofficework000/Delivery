package net.app.delivery.database;

import androidx.test.runner.AndroidJUnit4;

import net.app.delivery.model.Post;
import net.app.delivery.util.MockTestUtil;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;


@RunWith(AndroidJUnit4.class)
public class DeliveryDaoTest extends DbTest {

    @Test
    public void insertAndReadMovieTest() {
        List<Post> postEntities = new ArrayList<>();
        postEntities.add(MockTestUtil.mockDelivery());
        db.postDao().insertAll(MockTestUtil.mockDelivery());
        List<Post> storedDeliveryEntities = db.postDao().getAll();
        Assert.assertEquals("Deliver wine to Kenneth", storedDeliveryEntities.get(0).getDescription());
        Assert.assertEquals("longitude",114.170008 ,storedDeliveryEntities.get(0).getLocation().getLng(),0);
    }
}

