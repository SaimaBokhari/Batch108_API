package post_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Post04Pojo extends HerOkuAppBaseUrl {
    /*
         Given
            https://restful-booker.herokuapp.com/booking
            {
                "firstname": "Ali",
                "lastname": "Can",
                "totalprice": 999,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2021-09-21",
                    "checkout": "2021-12-21"
                     },
                 "additionalneeds": "Breakfast"
             }
        When
 		    I send POST Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
 		                            "bookingid": 257249,
 		                            "booking" :{
                                        "firstname": "Ali",
                                        "lastname": "Can",
                                        "totalprice": 999,
                                        "depositpaid": true,
                                        "bookingdates": {
                                            "checkin": "2021-09-21",
                                            "checkout": "2021-12-21"
                                        }
                                        "additionalneeds": "Breakfast"
                                     }
                                  }
     */
    @Test
    public void post04(){
        // set the URL
        spec.pathParam("first", "booking");

        // Set the expected data
        // Pojo class is more common for this

        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2021-09-21", "2021-12-21");
        BookingPojo expectedData = new BookingPojo("Ali", "Can", 999, true, bookingDatesPojo, "Breakfast");
        System.out.println("expectedData = " + expectedData);

        // Send the request the get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        // Do assertion
        BookingResponsePojo actualData = response.as(BookingResponsePojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getFirstname(),actualData.getBooking().getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getBooking().getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getBooking().getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getBooking().getDepositpaid());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getBooking().getAdditionalneeds());

        // 1st way:
        assertEquals(expectedData.getBookingdates().getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(expectedData.getBookingdates().getCheckout(),actualData.getBooking().getBookingdates().getCheckout());

        // 2nd way: recommended
        assertEquals(bookingDatesPojo.getCheckin(), actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(), actualData.getBooking().getBookingdates().getCheckout());
    }

    @Test
    public void post04b(){
        // set the URL
        spec.pathParam("first", "booking");

        // Set the expected data
        // Pojo class is more common for this

        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2021-09-21", "2021-12-21");
        BookingPojo expectedData = new BookingPojo("Ali", "Can", 999, true, bookingDatesPojo, "Breakfast");
        System.out.println("expectedData = " + expectedData);

        // Send the request the get the response
        Response response1 = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response1.prettyPrint();

        // No need to create responsePojo class
        // Use Jsonpath
        JsonPath jsonPath = response1.jsonPath();

        Integer bookingId = jsonPath.getInt("bookingid");  //bookingId from Post response body
        System.out.println(bookingId);

        // Set the url for Get Request
        spec.pathParams("first", "booking", "second", bookingId);  // bookingId will be different everytime we run the Post request

        Response response2 = given().spec(spec).contentType(ContentType.JSON).when().get("/{first}/{second}");
        response2.prettyPrint();

        // Do the assertion
        BookingPojo actualData = response2.as(BookingPojo.class);

        assertEquals(200, response2.getStatusCode());
        assertEquals(expectedData.getFirstname(), actualData.getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());
        assertEquals(expectedData.getBookingdates().getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals(expectedData.getBookingdates().getCheckout(), actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(), actualData.getAdditionalneeds());

        /*
        If you're sure that the payload/requestbody that you sent in Post Request is exactly the same
        as the Get Request on same bookingid (created by API in response body), then instead of creating
        BookingResponsePojo, we have another way of asserting: we can create Get Request in the same test
        and use response2 (response from the Get Request) for assertions.

        NOTE: REMEMBER TO DELETE THE DATA THAT YOU CREATE IN POST REQUESTS AND CLOSE THE CONNECTION WITH DATABASE
              AFTER EVERY TEST RUN.
              Everytime we connect with DataBase, we are charged for using cloud services.

              For that you can create @After method in the base url classes just like we
              created @Before annotation to create a connection.
         */


    }

}
