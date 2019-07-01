package net.app.delivery;

import net.app.delivery.network.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;



@RunWith(JUnit4.class)
public class ApiResponseTest {

    @Test
    public void exception() {
        Exception exception = new Exception("test");
        Resource apiResponse = Resource.Companion.error(exception.getMessage(), exception);
        Assert.assertEquals("test", apiResponse.getMessage());
    }

    @Test
    public void success() {
        Resource resource = Resource.Companion.success("test");
        Assert.assertEquals("test", resource.getData());
    }
}
