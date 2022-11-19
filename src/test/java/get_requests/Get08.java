package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Get08 extends JsonPlaceHolderBaseUrl {
    /*
         Given
            https://jsonplaceholder.typicode.com/todos/2
        When
            I send GET Request to the URL
        Then
            Status code is 200
            And "completed" is false
            And "userId" is 1
            And "title" is "quis ut nam facilis et officia qui"
            And header "Via" is "1.1 vegur"
            And header "Server" is "cloudflare"
            {
                "userId": 1,
                "id": 2,
                "title": "quis ut nam facilis et officia qui",
                "completed": false
            }
     */

    @Test
    public void get08(){

        // Set the URL
        spec.pathParams("first", "todos", "second", 2);

       //  Set the expected data
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        Map<String, Object> expectedData = obj.expectedDataJPH(1, "quis ut nam facilis et officia qui", false);
        System.out.println(expectedData);

        // Send the request and get the response
       Response response = given().spec(spec).when().get("/{first}/{second}"); // we are reading the data only with get()
        response.prettyPrint();

        // Do assertion
        // to compare the data, both actual and real data should be in the same format
        // it's called deserialisation
        // for that we change response (JSON format) into HashMap

        Map<String, Object> actualData = response.as(HashMap.class);   // as() method comes from GSon library from google
        System.out.println("Actual data: " + actualData);

        // assert the body
        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));

        //  And header "Via" is "1.1 vegur"
        assertEquals("1.1 vegur", response.getHeader("Via"));

        // And header "Server" is "cloudflare"
        assertEquals("cloudflare", response.getHeader("Server"));


    }



}
