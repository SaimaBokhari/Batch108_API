package homework;

import base_urls.ReqresBaseUrl;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.*;

public class Task02 extends ReqresBaseUrl {
     /*
        Given
            https://reqres.in/api/users/23
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Server is "cloudflare"
        And
            Response body should be empty

     */

    @Test
    public void task02(){

        // Set the URL
        spec.pathParams("first", "users", "second", 23);

        // Set the expected data

        // Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // Do assertion

        response.
                then().
                assertThat().
                statusCode(404).
                statusLine("HTTP/1.1 404 Not Found").
                body("isEmpty()", Matchers.is(true));

        assertEquals("cloudflare", response.getHeader("server"));

        // Yasin's way to check if body is empty
        assertEquals(0, response.as(HashMap.class).size());

        // Emina's way to check if body is empty
        assertFalse(response.asString().contains("Not Found"));

        // Hard assertion
            assertEquals(404, response.statusCode());
            assertEquals("HTTP/1.1 404 Not Found",response.statusLine());
            assertEquals("cloudflare",response.getHeader("Server"));
            assertEquals(0, response.asString().replaceAll("[^A-Za-z0-9]", "").length());
            //or
            assertEquals(0,response.as(HashMap.class).size());






    }

}

