package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Get12Pojo extends HerOkuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/12
        When
 		    I send GET Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
 		    "firstname": "Fabio",
    "lastname": "Dominguez",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "Breakfast"
    }
}

     */
    @Test
    public void get12(){

        // Set the URL
        spec.pathParams("first", "booking", "second", 12);

        // Set the expected data
        // inner pojo
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01","2019-01-01");

        // outer pojo
        BookingPojo expectedData = new BookingPojo("Fabio", "Dominguez", 111, true, bookingDatesPojo, "Breakfast");
        System.out.println("expectedData: " + expectedData);

        // Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();


        // Do assertion
        // de-serialisation
       BookingPojo actualData = response.as(BookingPojo.class);
       System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals("First names don't match",expectedData.getFirstname(),actualData.getFirstname());
        assertEquals("Last names don't match", expectedData.getLastname(),actualData.getLastname());
        assertEquals("Total price doesn't match", expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals("Deposit paid doesn't match", expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals("Additional needs don't match", expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());

        // 1st way:
        assertEquals("Check in dates don't match", expectedData.getBookingdates().getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals("Check out dates don't match",expectedData.getBookingdates().getCheckout(), actualData.getBookingdates().getCheckout());


        //2nd way: recommended coz shorter
        assertEquals("Check in dates don't match",bookingDatesPojo.getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals("Check out dates don't match", bookingDatesPojo.getCheckout(), actualData.getBookingdates().getCheckout());



    }




}
