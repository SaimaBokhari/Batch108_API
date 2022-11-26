package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class DummyApiBaseUrl {

    protected RequestSpecification spec;

    @Before // if we use this annotation at the top of any method, it means this method will
    // be run before any other method
    public void setUp(){
        spec = new RequestSpecBuilder().setBaseUri("https://dummy.restapiexample.com/api/v1").build();
    }


}
