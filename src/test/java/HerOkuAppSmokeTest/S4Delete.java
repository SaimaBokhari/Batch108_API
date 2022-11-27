package HerOkuAppSmokeTest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import static HerOkuAppSmokeTest.S1Post.bookingId;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static utils.AuthenticationHerOkuApp.generateToken;

public class S4Delete extends HerOkuAppBaseUrl {
    /*
    Given
         https://restful-booker.herokuapp.com/booking/:id
    When
         User sends Delete request
    Then
         Status code is 201
    And
         Response body is "Created"
     */
    @Test
    public void delete01() {
        // Set the URL
        spec.pathParams("first", "booking", "second", bookingId);

        //Set the expected data
        String expectedData = "Created";

        //Send the request and get the response
        Response response = given().
                spec(spec).
                headers("Cookie", "token="+ generateToken()).
                contentType(ContentType.JSON).
                when().delete("/{first}/{second}");

        response.prettyPrint();


        // Do assertion
        assertEquals(201, response.statusCode());
        assertEquals(expectedData, response.asString());

    }
}
