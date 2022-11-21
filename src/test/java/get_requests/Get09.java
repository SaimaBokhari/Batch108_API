package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Get09 extends HerOkuAppBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking/246
        When
            I send GET Request to the url
        Then
            Response body should be like that;
            {
                "firstname": "Alex",
                "lastname": "Dominguez",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                },
                "additionalneeds": "Breakfast"
            }
     */
    @Test
    public void get09() {
        // Set the URL
        spec.pathParams("first", "booking", "second", 246);

        // Set the expected data
        // we create inner map for booking dates and an outer map for all values

        //inner map
        Map<String, String> bookingDatesMap = new HashMap<>();
        bookingDatesMap.put("checkin", "2018-01-01");
        bookingDatesMap.put("checkout", "2019-01-01");
        System.out.println("bookingDatesMap: " + bookingDatesMap);

        //outer map
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "Alex");
        expectedData.put("lastname", "Dominguez");
        expectedData.put("totalprice", 111);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates", bookingDatesMap);
        expectedData.put("additionalneeds", "Breakfast");

        // Send the request and get the response

        Response response = given().spec(spec).get("/{first}/{second}");
        response.prettyPrint();

        // Do assertion
        // 1st step: de-serialisation .. convert from Json into Hashmap
        Map<String, Object> actualData = response.as(HashMap.class);
        // System.out.println(actualData);

        // 2ns step: assert
        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
        assertEquals(expectedData.get("additionalneeds"), actualData.get("additionalneeds"));

        // 1st way:
        // we have to use typecasting to get the inner json for bookingdates and convert it to Map data type so we
        // can get the checkin key and it's value
        assertEquals(((Map) expectedData.get("bookingdates")).get("checkin"), ((Map) actualData.get("bookingdates")).get("checkin"));
        assertEquals(((Map) expectedData.get("bookingdates")).get("checkout"), ((Map) actualData.get("bookingdates")).get("checkout"));

        // 2nd way: recommended way

        assertEquals(bookingDatesMap.get("checkin"), ((Map) actualData.get("bookingdates")).get("checkin"));
        assertEquals(bookingDatesMap.get("checkout"), ((Map) actualData.get("bookingdates")).get("checkout"));


    }

    @Test
    public void get09b() {
        // Set the URL
        spec.pathParams("first", "booking", "second", 246);

        // Set the expected data
        // we create object from HerOkuAppTestData class and call method from this class

        // call the method from HerOkuAppTestData for inner map
        HerOkuAppTestData obj = new HerOkuAppTestData();
        Map<String, String> bookingDatesMap = obj.bookingDatesMapSetUp("2018-01-01", "2019-01-01");

        //outer map
        Map<String, Object> expectedData = obj.expectedDataSetUp("Alex", "Dominguez", 111, true, bookingDatesMap, "Breakfast");

        // Send the request and get the response

        Response response = given().spec(spec).get("/{first}/{second}");
        response.prettyPrint();

        // Do assertion
        // 1st step: de-serialisation .. convert from Json into Hashmap
        Map<String, Object> actualData = response.as(HashMap.class);
        // System.out.println(actualData);

        // 2ns step: assert
        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
        assertEquals(expectedData.get("additionalneeds"), actualData.get("additionalneeds"));

        // 1st way:
        // we have to use typecasting to get the inner json for bookingdates and convert it to Map data type so we
        // can get the checkin key and it's value
        assertEquals(((Map) expectedData.get("bookingdates")).get("checkin"), ((Map) actualData.get("bookingdates")).get("checkin"));
        assertEquals(((Map) expectedData.get("bookingdates")).get("checkout"), ((Map) actualData.get("bookingdates")).get("checkout"));

        // 2nd way: recommended

        assertEquals(bookingDatesMap.get("checkin"), ((Map) actualData.get("bookingdates")).get("checkin"));
        assertEquals(bookingDatesMap.get("checkout"), ((Map) actualData.get("bookingdates")).get("checkout"));



    }


}
