package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Post01 extends JsonPlaceHolderBaseUrl {
    /*
    Given
           1) https://jsonplaceholder.typicode.com/todos
           2)  {
                 "userId": 55,
                 "title": "Tidy your room",
                 "completed": false
              }
        When
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */

    @Test
    public void post01() {

        // Set the URL
        spec.pathParam("first", "todos");

        // Set the expected data  (create payload explicitly)
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", 55);
        expectedData.put("title", "Tidy your room");
        expectedData.put("completed", false);

        System.out.println(expectedData);

        // Send the request and get the response
        // body() here is just serving as a container to carry the data
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        // Do assertion
        // We need expected data and the actual data to do the assertion

        //  response.as(HashMap.class) using as() method, response will be converted into Hashmap again

        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("Actual data" + actualData);

        assertEquals(201, response.statusCode());
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("userId"), actualData.get("userId"));


// Json is not a programming language. It's a common language used for storing data
    }

    @Test
    public void post01b() {

        // Set the URL
        spec.pathParam("first", "todos");

        // Set the expected data
        // We are creating a separate class JsonPlaceHolderTestData... so we can use that method again and again
        // Create object to use that class.. we can not use inheritance here coz this class is already extended to another class
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        Map<String, Object> expectedData = obj.expectedDataJPH(55, "Tidy your room", false);
        System.out.println(expectedData);

        // Send the request and get the response
        // body() here is just serving as a container to carry the data
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        // Do assertion
        // We need expected data and the actual data to do the assertion

        //  response.as(HashMap.class) using as() method, response will be converted into Hashmap again

        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("Actual data" + actualData);

        assertEquals(201, response.statusCode());
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("userId"), actualData.get("userId"));


    }
}
