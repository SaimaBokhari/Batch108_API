package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Get05 extends HerOkuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking
        When
            User sends get request to the URL
        Then
            Status code is 200
      And
         Among the data there should be someone whose firstname is "Agustin" and lastname is "Bonfanti"
     */

    @Test
    public void get05(){
        //Set the url
        spec.pathParam("first", "booking").
                queryParams("firstname","Mary","lastname","Ericsson");

        // queryParams() is used to select a specific data e.g. a book
        // we use ? after base url and then key:value structure & second key:value structure
        // pathParam() we use / after base url
        //Set the expected Data


        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();


        //Do Assertion

        //Status code is 200
        // response.then().assertThat().statusCode(200);
        assertEquals(200,response.statusCode());

        //Among the data there should be someone whose firstname is "Mary" and lastname is "Ericsson"
        assertTrue(response.asString().contains("bookingid"));


    }

}
