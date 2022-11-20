package get_requests;

import base_urls.HerOkuAppBaseUrl;
import org.junit.Test;

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
    public void get09(){
        // Set the URL
        spec.pathParams("first", "booking", "second",246);

        // Set the expected data





    }

}
