package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Review3 extends JsonPlaceHolderBaseUrl {

     /*
       Given
           https://jsonplaceholder.typicode.com/todos/23
       When
           User send GET Request to the URL
       Then
           HTTP Status Code should be 200
   And
       Response format should be "application/json"
   And
       "title" is "et itaque necessitatibus maxime molestiae qui quas velit",
   And
       "completed" is false
   And
       "userId" is 2
    */

    // Do the manual testing first through Postman

    @Test
    public void get3(){
        // Set the URL
        spec.pathParams("first", "todos", "second", 23);
        //set the expected data

        // send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // Do assertion Hard assertion

        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit")).
                body("userId", equalTo(2)).
                body("completed", equalTo(false));


        // Soft assertion
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                        "userId", equalTo(2), "completed", equalTo(false));









    }


}
