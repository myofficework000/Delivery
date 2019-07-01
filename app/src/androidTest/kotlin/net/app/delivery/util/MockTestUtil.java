package net.app.delivery.util;

import net.app.delivery.model.LocationDetails;
import net.app.delivery.model.Post;

import java.util.ArrayList;
import java.util.List;

public class MockTestUtil {

    public static List<Post> PostDeliveryList(int id , String title , String imgUrl , LocationDetails locationDetails) {
        List<Post> postEntities = new ArrayList<>();

        Post postEntity1 = new Post(id , title , imgUrl , locationDetails);
        postEntities.add(postEntity1);

        Post postEntity2 = new Post(id , title , imgUrl , locationDetails);
        postEntities.add(postEntity2);

        return postEntities;
    }


    public static Post mockDelivery() {
        LocationDetails locationDetails= new LocationDetails(22.319181 ,114.170008 ,"Mong Kok");
        Post postEntity = new Post(1,"Deliver wine to Kenneth","https://s3-ap-southeast-1.amazonaws.com/lalamove-mock-api/images/pet-5.jpeg",locationDetails );
        return postEntity;
    }

    public static List<Post> mockListDelivery() {
        List<Post> postEntities = new ArrayList<>();
        LocationDetails locationDetails= new LocationDetails(22.319181 ,114.170008 ,"Mong Kok");
        Post postEntity = new Post(1,"Deliver wine to Kenneth","https://s3-ap-southeast-1.amazonaws.com/lalamove-mock-api/images/pet-5.jpeg",locationDetails );
        postEntities.add(postEntity);
        return postEntities;
    }
}