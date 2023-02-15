package patch_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class Patch01 extends JsonPlaceHolderBaseUrl {
    /*
      Given
          1) https://jsonplaceholder.typicode.com/todos/198
          2) {
               "title": "Read the books"
             }
      When
           I send PATCH Request to the Url
      Then
            Status code is 200
            And response body is like   {
                                      "userId": 10,
                                      "title": "Read the books",
                                      "completed": true,
                                      "id": 198
                                     }
   */
    @Test
    public void patch01(){

        // Set the URL
        spec.pathParams("first", "todos", "second", 198);

        //  Set the expected data
//        Map<String, Object> expectedData = new HashMap<>();
//        expectedData.put("title", "Read the books");
//        System.out.println("expectedData = " + expectedData);

        // recommended
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        Map<String, Object> expectedData = obj.expectedDataJPH(null, "Wash the dishes", null );

        // Send the request and get the response
       Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().patch("/{first}/{second}");
       response.prettyPrint();

       // Do assertion

        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());

        assertEquals(10, actualData.get("userId"));
        assertEquals(true, actualData.get("completed"));
        assertEquals(expectedData.get("title"), actualData.get("title"));

        // or
        response.
                then().
                assertThat().
                body("userId", equalTo(10),
                "title", equalTo("Wash the dishes"),
                        "completed", equalTo(true));


/*
When you create a new test method in your classes, run all the previous test methods as well
just to make sure that you didn't break any previous codes/methods (even though you're sure
that the new test method is completely independent)
 */





    }
}
