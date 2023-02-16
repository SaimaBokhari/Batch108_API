package post_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;
import utils.JsonUtils;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class PostGetWithObjectMapperPojo extends HerOkuAppBaseUrl {
    /*
    Given
            1) https://restful-booker.herokuapp.com/booking/55
            2) {
                 "firstname": "Josh",
                 "lastname": "Allen",
                 "totalprice": 111,
                 "depositpaid": true,
                 "bookingdates": {
                                  "checkin": "2018-01-01",
                                  "checkout": "2019-01-01"
                                 },
                  "additionalneeds": "midnight snack"
              }
        When
            Send POST Request to the Url
        And
            Send Get Request to the Url
        Then
            Status code is 200
        And
            Get response body should be like {
                                               "firstname": "Josh",
                                               "lastname": "Allen",
                                               "totalprice": 111,
                                               "depositpaid": true,
                                               "bookingdates": {
                                                                "checkin": "2018-01-01",
                                                                "checkout": "2019-01-01"
                                                              },
                                               "additionalneeds": "midnight snack"
                                             }
     */

    @Test
    public void PostGetWithObjectMapperPojo(){

        // Set the URL for Post Request body
        spec.pathParam("first", "booking");

        // Set the request body
        BookingDatesPojo bookingDates = new BookingDatesPojo("2018-01-01","2019-01-01" );
        BookingPojo expectedData = new BookingPojo("Josh", "Allen", 111, true, bookingDates,"midnight snack" );
        System.out.println("expectedData = " + expectedData);

        // Send the request and get the response
        Response response1 = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response1.prettyPrint();

        // De-serialisation
        BookingResponsePojo postResponseBodyInPojo = JsonUtils.convertJsonToJavaObject(response1.asString(), BookingResponsePojo.class);
        System.out.println("postResponseBodyInPojo = " + postResponseBodyInPojo);


        // Send Get Request to the Url
        Integer bookingId = postResponseBodyInPojo.getBookingid();  // get the booking id from pojo class

        // Set the URL for Get Request
        spec.pathParams("first", "booking", "second", bookingId);

        // Set the expected Data
        // same as above .. coz the data sent is Post request is the same for Get Request as well

        // send the request and get the response
        Response response2 = given().spec(spec).when().get("/{first}/{second}");
        response2.prettyPrint();

        // De-serialisation
        BookingPojo getResponseBodyInPojo = JsonUtils.convertJsonToJavaObject(response2.asString(), BookingPojo.class);

        // Do assertion
        assertEquals(200, response2.statusCode());
        assertEquals(expectedData.getFirstname(), getResponseBodyInPojo.getFirstname());
        assertEquals(expectedData.getLastname(), getResponseBodyInPojo.getLastname());
        assertEquals(expectedData.getTotalprice(), getResponseBodyInPojo.getTotalprice());
        assertEquals(expectedData.getDepositpaid(), getResponseBodyInPojo.getDepositpaid());
        assertEquals(expectedData.getBookingdates().getCheckin(), getResponseBodyInPojo.getBookingdates().getCheckin());
        assertEquals(expectedData.getBookingdates().getCheckout(), getResponseBodyInPojo.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(), getResponseBodyInPojo.getAdditionalneeds());

         /*
          SUMMARY:

        To send Post Request we did following 3 steps:
        1. Set the URL
        2. Set the Post Request body
        3. Send the Post Request and get the response

        Then we did de-serialisation by converting Response body to Java Object  (Using Object Mapper)
        From this converted response body(postResponseBodyInPojo), we got the value of bookingId by using Getter of bookingId

        By using this bookingId, I sent Get Request and did the 3 steps again
        1. Set the URL
        2. Set the Get Request body (same as above)
        3. Send the Post Request and get the response

        Then we did de-serialisation again by converting Response body (of Get Request) to Java Object  (Using Object Mapper)
        In the end, I did assertions by using expectedData and getResponseBodyInPojo.

         */




    }
}
