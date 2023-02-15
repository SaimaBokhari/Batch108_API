package delete_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Delete01 extends JsonPlaceHolderBaseUrl {
    /*
        Given
            https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send DELETE Request to the Url
	 	Then
		 	Status code is 200
		 	And Response body is { }
     */

    @Test
    public void delete01(){
        // Set the URL
        spec.pathParams("first", "todos", "second", 198);

        // Set the expected data
        Map<String, Object> expectedData = new HashMap<>();
        System.out.println("expectedData = " + expectedData);

        // Send the request and get the response
        Response response = given().spec(spec).when().delete("/{first}/{second}");
        response.prettyPrint();

        // Do assertion
        // de-serialisation first using Gson
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);


        assertEquals(200, response.statusCode());
        assertEquals(expectedData, actualData);
        // or
        assertEquals(expectedData.size(), actualData.size());
        // or
        assertTrue(actualData.isEmpty());
        // or
        response.then().assertThat(). body("isEmpty()", Matchers.is(true));
        // or
        response.asString().replaceAll("[^A-Za-z0-9]", "").length();


        /*
        How to automate Delete Request in API Testing?
        1. Create a new data by using Post request
        2. Use "Delete request" to delete new data
        3. Do Not delete existing data, always create a data to delete.
         */
    }




}
