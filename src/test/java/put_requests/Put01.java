package put_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class Put01 extends JsonPlaceHolderBaseUrl {
    /*
    Given
	        1) https://jsonplaceholder.typicode.com/todos/198
	        2) {
                 "userId": 21,
                 "title": "Wash the dishes",
                 "completed": false
               }
        When
	 		I send PUT Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 21,
									    "title": "Wash the dishes",
									    "completed": false
									   }
     */

    @Test
    public void put01(){
        // Set the URL
        spec.pathParams("first", "todos", "second", 198);

        // Set the expected data (payload)
       // new JsonPlaceHolderTestData()        // short way to create object from the constructor

        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        Map<String, Object> expectedData = obj.expectedDataJPH(21,"Wash the dishes", false);
        System.out.println("expectedData = " + expectedData);

        // Send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().put("/{first}/{second}");
        response.prettyPrint();

        // Do assertion
        //1st way
        response.
                then().
                assertThat().
                statusCode(200).
                body("userId", equalTo(expectedData.get("userId")),
                "title", equalTo(expectedData.get("title")),
                "completed", equalTo(expectedData.get("completed")));

        // Hamcrest Matchers can be used for assertions but de-serialisation is the recommended one

        // 2nd way
        // de-serialisation (converting Json data to Java Object
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("userId"), actualData.get("userId"));

        // How to use GSON in serialisation: Java Object ==> Json Data
        Map<String, Integer> ages = new HashMap<>(); // {}
        ages.put("Ali Can", 13);
        ages.put("Veli Han", 15);
        ages.put("Ayse Kan", 21);
        ages.put("Mary Star", 65);
        System.out.println(ages);  // HashMap prints randomly

        // Convert ages map to Json data
        // Step 1: Create Gson object
        Gson gson = new Gson();
        // Step 2: By using "gson" object select method to convert Java Object to Json Data
        String jsonFromMap = gson.toJson(ages);
        System.out.println(jsonFromMap);

    }

}
