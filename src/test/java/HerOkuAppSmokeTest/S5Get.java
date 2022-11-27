package HerOkuAppSmokeTest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import static HerOkuAppSmokeTest.S1Post.bookingId;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;


public class S5Get extends HerOkuAppBaseUrl {
    /*
    Negative test: Testing with negative values


    Given
         https://restful-booker.herokuapp.com/booking/:id
    When
         User sends Get request
    Then
         Status code is 404
    And
         Response body is "Not Found"
     */


    @Test
    public void get02() {
        // Set the URL
        spec.pathParams("first", "booking", "second", bookingId);

        //Set the expected data
        String expectedData = "Not Found";

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // Do assertion
        assertEquals(404, response.statusCode());
        assertEquals(expectedData, response.asString());



    }
}