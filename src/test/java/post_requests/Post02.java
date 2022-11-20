package post_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Post02 extends HerOkuAppBaseUrl {
    /*
    Given
            1) https://restful-booker.herokuapp.com/booking
            2) {
                 "firstname": "John",
                 "lastname": "Doe",
                 "totalprice": 11111,
                 "depositpaid": true,
                 "bookingdates": {
                     "checkin": "2021-09-09",
                     "checkout": "2021-09-21"
                  },
                "additionalneeds": "Breakfast"
               }
        When
            I send POST Request to the Url
        Then
            Status code is 200
            And response body should be like {
                                                "bookingid": 36725,
                                                "booking": {
                                                    "firstname": "John",
                                                    "lastname": "Doe",
                                                    "totalprice": 11111,
                                                    "depositpaid": true,
                                                    "bookingdates": {
                                                        "checkin": "2021-09-09",
                                                        "checkout": "2021-09-21"
                                                    }
                                                "additionalneeds": "Breakfast"
                                                }
                                             }
     */

    @Test
    public void post02(){

        // Set the URL
        spec.pathParam("first", "booking");

        // Set the expected data  (payload)
        // for inner map
        HerOkuAppTestData obj = new HerOkuAppTestData();
        Map<String, String> bookingDatesMap = obj.bookingDatesMapSetUp("2021-09-09", "2021-09-21");

        // for outer map
        Map<String, Object> expectedData =  obj.expectedDataSetUp("John", "Doe", 11111, true, bookingDatesMap, "Breakfast" );
        System.out.println(expectedData);

        // Send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        // Do assertion
        // 1st step: De-serialisation
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("Actual data: " + actualData);

        //2nd step:  assertion
        assertEquals(200, response.statusCode());
        // we have to do typecasting for actualdata ... convert it to map .. so we can reach inner key/values of booking
        assertEquals(expectedData.get("firstname"), ((Map)(actualData.get("booking"))).get("firstname"));
        assertEquals(expectedData.get("lastname"), ((Map)(actualData.get("booking"))).get("lastname"));
        assertEquals(expectedData.get("totalprice"), ((Map)(actualData.get("booking"))).get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), ((Map)(actualData.get("booking"))).get("depositpaid"));
        assertEquals(expectedData.get("additionalneeds"), ((Map)(actualData.get("booking"))).get("additionalneeds"));

        assertEquals(bookingDatesMap.get("checkin"),((Map)(((Map)(actualData.get("booking"))).get("bookingdates"))).get("checkin"));
        assertEquals(bookingDatesMap.get("checkout"),((Map)(((Map)(actualData.get("booking"))).get("bookingdates"))).get("checkout"));



    }

}
