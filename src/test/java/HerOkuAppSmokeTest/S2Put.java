package HerOkuAppSmokeTest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import utils.JsonUtils;

import static HerOkuAppSmokeTest.S1Post.bookingId;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static utils.AuthenticationHerOkuApp.generateToken;


public class S2Put extends HerOkuAppBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/:id
    And
        {
    "firstname": "Sadaf",
    "lastname": "Ahmed",
    "totalprice": 159,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2022-10-16",
        "checkout": "2022-10-24"
    },
    "additionalneeds": "Breakfast"
}
    When
        User sends Put request
    Then
        Status code is 200
    And
        Response body is like {
    "firstname": "Sadaf",
    "lastname": "Ahmed",
    "totalprice": 159,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2022-10-16",
        "checkout": "2022-10-24"
    },
    "additionalneeds": "Breakfast"
}
     */
    @Test
    public void put01(){
        //Set the url
        spec.pathParams("first","booking","second",bookingId);

        //Set the expected data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2022-10-16","2022-10-24");
        BookingPojo expectedData = new BookingPojo("Sadaf","Ahmed",159,true,bookingDatesPojo,"Breakfast");
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given().
                spec(spec).
                contentType(ContentType.JSON).
                headers("Cookie","token="+generateToken()).
                body(expectedData).
                when().
                put("/{first}/{second}");

        response.prettyPrint();

        // Do assertion

        BookingPojo actualData = JsonUtils.convertJsonToJavaObject(response.asString(),BookingPojo.class);
        System.out.println("actualData = " + actualData);
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());

        assertEquals(bookingDatesPojo.getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(),actualData.getBookingdates().getCheckout());


    }
}