package get_requests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.*;

public class Get02 {
    /*
   Given
       https://restful-booker.herokuapp.com/booking/101
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

   @Test
   public void get02(){

       // i) Set the URL
       String url = " https://restful-booker.herokuapp.com/booking/1001";

       // ii) Set the expected data
       // we will do this step later


       // iii) Send the request and get the response
       Response response = given().when().get(url);
       response.prettyPrint();
       // "Not Found" printed on the console belongs to response datatype ... it is not a string data type

       // iv) Do assertion for status code, status line
       response.then().assertThat().statusCode(404).statusLine("HTTP/1.1 404 Not Found");

       // Do assertion for body "Not Found"
       // assertTrue(x) method passes if x is true
       assertTrue(response.asString().contains("Not Found"));
       // we need to convert "Not Found" from response datatype to String datatype to be able to use String methods

       // Do assertion for body doesn't contain "TechPro"
       // assertFalse(x) method passes if x is false
       assertFalse(response.asString().contains("TechPro"));

       // Do assertion for Server is "Cowboy"
       // assertEquals(x,y) method passes if x is equal to y.
       assertEquals("Cowboy",response.getHeader("Server"));
       // Expected data comes from test case, Actual data comes form API

       // assertFalse() is good for negative tests



   }


}
