package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class Get03 extends JsonPlaceHolderBaseUrl {

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
    public void get03(){
        // Set the URL
        // String is not the recommended way, so we'll learn something new
        // we create a separate class, create inheritance and use that method every time
        // pathParameters are used to make the search narrow, by narrowing down the search area

        spec.pathParams("first", "todos", "second", 23);

        // Set the expected data
        // not yet


        // Send the request and get the response
        Response response= given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // Do assertion
        // 1st Way:
//        response.
//                then().
//                assertThat().
//                statusCode(200).
//                contentType("application/json").
//                body("userId", equalTo(2)).     // equalTo() is a matcher method
//                body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit")).
//                body("completed", equalTo(false));
//
//        System.out.println("=====================");
        // if the first body() fails the test, it will not execute the next body() methods.
        // This is called "Hard Assertion". It is more common because it gives immediate result telling where the failure is


        // 2nd Way: Soft Assertion
        // For this we have to do assertions in a single body()
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("userId",equalTo(2),
                        "title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                        "completed", equalTo(false));

        // Soft Assertion happens ONLY inside the body() method... e.g. if test fails in statusCode, it will stop the execution

// We don't have to do soft assertion, depends on the tester and the task.

        /*
        Notes:
        1) When you execute your code, Java can stop execution after the first failure.
                (e.g. if the test fails at contentType, the assertions after that will not be executed.
                But the assertion before the failure will be executed.)
        2) If the execution stops after the first failure, it's called "Hard Assertion"
        3) If the execution doesn't stop after the first failure, it's called "Soft Assertion"
        4) "Hard assertion" has multiple body methods whereas "Soft Assertion" uses single
                 body method with multiple assertions inside the body method

         */


    }

}
