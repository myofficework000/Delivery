package net.app.delivery;

import net.app.delivery.model.Post;
import net.app.delivery.network.PostApi;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.util.List;

public class DeliveryApiTest extends ApiAbstract<PostApi> {

    private PostApi service;

    @Before
    public void initService() {
        this.service = createService(PostApi.class);
    }

    @Test
    public void fetchDeliveryDescriptionTest() throws IOException {
        enqueueResponse("test_delivery_data_list.json");
        List<Post> post = service.getPosts(1,5).blockingFirst();
        Assert.assertEquals("Deliver toys to Luqman", post.get(0).getDescription());
    }

    @Test
    public void fetchDeliveryIdTest() throws IOException {
        enqueueResponse("test_delivery_data_list.json");
        List<Post> post = service.getPosts(1,5).blockingFirst();
        Assert.assertEquals(4, post.get(3).getId());
    }

    @Test
    public void fetchDeliverySizeTest() throws IOException {
        enqueueResponse("test_delivery_data_list.json");
        List<Post> post = service.getPosts(1,5).blockingFirst();
        Assert.assertEquals(30, post.size());
    }

    @Test
    public void fetchLocationAddressTest() throws IOException {
        enqueueResponse("test_delivery_data_list.json");
        List<Post> post = service.getPosts(1,20).blockingFirst();
        Assert.assertEquals("lattitude",22.336093, post.get(1).getLocation().getLat(),0);
    }
}
