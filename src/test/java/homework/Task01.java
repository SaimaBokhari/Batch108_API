package homework;

import base_urls.ReqresIn;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;

public class Task01 extends ReqresIn {
    /*
        Given
            https://reqres.in/api/users/3
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
    public void task01(){

        // Set the URL
        spec.pathParams("first", "users", "second", 3);

        // Set the expected data


        // Send the request and get the response
       Response response = given().spec(spec).when().get("/{first}/{second}");
       response.prettyPrint();

       // Do assertions
//        assertEquals(200, response.statusCode());
//        assertEquals("application/json", response.contentType());
//        assertEquals("HTTP/1.1 200 OK", response.statusLine());

        response.then().assertThat().statusCode(200).statusLine("HTTP/1.1 200 OK").contentType(ContentType.JSON);







    }


}
