package homework;

import base_urls.HerOkuAppBaseUrl;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Task04 extends HerOkuAppBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking?firstname=Brandon&lastname=Wilson
        When
            User sends get request to the URL
        Then
            Status code is 200
        And
            Among the data there should be someone whose firstname is "Brandon" and lastname is "Wilson"

 */


    @Test
    public void task04(){
        // Set the URL

        spec.pathParam("first", "booking").queryParams("firstname","Brandon", "lastname","Wilson");


        // Set the expected data

        // Send the request and get the response
       Response response = given().spec(spec).when().get("/{first}");
       response.prettyPrint();

       // Do assertion

        assertEquals(200, response.statusCode());
        assertTrue(response.asString().contains("bookingid"));


    }




}
