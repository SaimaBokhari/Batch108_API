package get_requests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.testng.AssertJUnit.*;

public class Review {

    /*
    Task 1:

   Given
       https://restful-booker.herokuapp.com/booking/101
   When
       User sends a GET Request to the url
   Then
       HTTP Status Code should be 200
   And
       Content Type should be JSON
   And
       Status Line should be HTTP/1.1 200 OK
*/


    @Test
    public void get01(){
//        String rul = "https://restful-booker.herokuapp.com/booking/101";
//
//      Response response = given().when().get(rul);
//      response.prettyPrint();
//
//      response.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");
//
//        System.out.println(response.getHeaders());
//        System.out.println(response.getTime());


        /*
   Given
       https://restful-booker.herokuapp.com/booking/1
   When
       User send a GET Request to the url
   Then
       HTTP Status code should be 404
   And
       Status Line should be HTTP/1.1 404 Not Found
   And
       Response body contains "Not Found"
   And
       Response body does not contain "TechProEd"
   And
       Server is "Cowboy"
*/
        String url = "https://restful-booker.herokuapp.com/booking/1";

        Response response = given().when().get(url);
        response.prettyPrint();

        response.then().assertThat().statusCode(404).statusLine("HTTP/1.1 404 Not Found");

        assertTrue(response.asString().contains("Not Found"));

        assertFalse(response.asString().contains("TechPro"));

        assertEquals("Cowboy", response.getHeader("Server"));









    }
}
