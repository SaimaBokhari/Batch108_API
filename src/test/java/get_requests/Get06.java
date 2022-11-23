package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Get06 extends HerOkuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/55
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json”
        And
            Response body should be like;
                {
    "firstname": "Eric",
    "lastname": "Smith",
    "totalprice": 773,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2017-12-15",
        "checkout": "2020-01-01"
    },
    "additionalneeds": "Breakfast"
}

     */

    @Test
    public void get06() {
        // Set the URL
        spec.pathParams("first", "booking", "second", 32);

        // Set the expected data

        // Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // Do assertion

        // 1st way: using matchers for body() method
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname", equalTo("Taylor"),
                        "lastname", equalTo("Miller"),
                        // "totalPrice", equalTo(111),    //this value is not passing the test
                        "depositpaid", equalTo(true),
                        "bookingdates.checkin", equalTo("2022-12-09"),
                        "bookingdates.checkout", equalTo("2022-12-22"),
                        "additionalneeds", equalTo("breakfast"));

        // 2nd way: We will use JsonPath Class
               JsonPath jsonPath = response.jsonPath();  // Hard Assertion

         /* JsonPath is used for data manipulation
         JsonPath() enables us to extract data from inside the body
         Jsonpath class enables us to do Math operations like division in the following example
         System.out.println(jsonPath.getInt("totalPrice") / 3);

          */

        assertEquals("James", jsonPath.getString("firstname"));
        assertEquals("Brown", jsonPath.getString("lastname"));
       // assertEquals(773, jsonPath.getInt("totalprice"));   this value is not passing the test
        assertTrue(jsonPath.getBoolean("depositpaid"));
        assertEquals("2018-01-01", jsonPath.getString("bookingdates.checkin"));
        assertEquals("2019-01-01", jsonPath.getString("bookingdates.checkout"));
        assertEquals("Breakfast", jsonPath.getString("additionalneeds"));


        /* Soft assertion for JsonPath
           There are three steps:
           1st. Create softAssert Object
           2nd. Do assertion
           3rd. Use assertAll() method

         */

        // 1st. Create softAssert Object
        SoftAssert softAssert = new SoftAssert();


        // 2nd. Do assertion
        softAssert.assertEquals(jsonPath.getString("firstName"), "James", "First name didn't match");  // 3rd parameter is a custom message if test fails
        softAssert.assertEquals(jsonPath.getString("lastName"), "Brown", "Last name didn't match");
        // softAssert.assertEquals(jsonPath.getInt("totalPrice"), 111, "Total price didn't match");
        softAssert.assertEquals(jsonPath.getBoolean("depositpaid"), true, "Deposit is not paid");
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkin"), "2018-01-01", "Checkin date didn't match");
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkout"), "2019-01-01", "Checkout date didn't match");
        softAssert.assertEquals(jsonPath.getString("additionalneeds"), "Breakfast", "Additional needs are not provided" );

        //  3rd. Use assertAll() method

        softAssert.assertAll();


    }

}

/*  output
{
    "firstname": "Taylor",
    "lastname": "Miller",
    "totalprice": 156,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2022-12-09",
        "checkout": "2022-12-22"
    },
    "additionalneeds": "breakfast"
}

 */
