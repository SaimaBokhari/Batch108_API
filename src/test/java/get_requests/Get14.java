package get_requests;

import base_urls.ReqresBaseUrl;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;

public class Get14 extends ReqresBaseUrl {
    /*
    HW Task02

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
        // or

        assertEquals(404, response.statusCode());
        assertEquals("HTTP/1.1 404 Not Found", response.statusLine());
        assertEquals("cloudflare", response.header("Server"));


        // response body should be empty

        System.out.println(response.asString().length());  // 8
        // Yasin's way to check if body is empty
        assertEquals(0, response.as(HashMap.class).size());

        // Emina's way to check if body is empty
        assertFalse(response.asString().contains("Not Found"));

        // Teacher's way
        assertEquals(0, response.asString().replaceAll("[^A-Za-z0-9]", "").length());





    }

}
