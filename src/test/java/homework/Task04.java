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
            https://restful-booker.herokuapp.com/booking?firstname=Carlos&lastname=Sevilla
            {
    "firstname": "Carlos",
    "lastname": "Sevilla",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "Breakfast"
}

        When
            User sends get request to the URL
        Then
            Status code is 200
        And
            Among the data there should be someone whose firstname is "Carlos" and lastname is "Sevilla"

 */


    @Test
    public void task04(){
        // Set the URL

        spec.pathParam("first", "booking").queryParams("firstname","Carlos", "lastname","Sevilla");


        // Set the expected data

        // Send the request and get the response
       Response response = given().spec(spec).when().get("/{first}");
       response.prettyPrint();

       // Do assertion

        assertEquals(200, response.statusCode());
        assertTrue(response.asString().contains("bookingid"));


    }




}
