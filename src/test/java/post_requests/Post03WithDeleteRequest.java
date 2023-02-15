package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Post03WithDeleteRequest extends JsonPlaceHolderBaseUrl {
    /*
         Given
            1) https://jsonplaceholder.typicode.com/todos
            2)  {
                "userId": 55,
                "title": "Tidy your room",
                "completed": false
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }

                                    baseurl + parameter = endpoint

        Pojo class is most common, secure and fast
        Plain Old Java Object
     */

    @Test
    public void Post03WithDeleteRequest(){

        // set the URL
        spec.pathParam("first", "todos");

        // Set the expected data
        // create an object from pojo class

        JsonPlaceHolderPojo expectedData = new JsonPlaceHolderPojo(55, "Tidy your room", false);
        System.out.println(expectedData);

        // Send the request and get the response
        // serialisation (by specifying the content type to be posted
        Response response1 = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response1.prettyPrint();

        // Get the id of newly created data
        JsonPath jsonPath = response1.jsonPath();
        Integer id  = jsonPath.getInt("id");

        // Set the url for Delete request using that id
        spec.pathParams("first", "todos", "second", id);

        // Send the request and get the response
        Response response2 = given().spec(spec).when().delete("/{first}/{second}");
        response2.prettyPrint();

        // Do assertion
        // After deleting the data, response should be empty json. We create an empty map
        // so that we can do assertion
        Map<String,Object> actualData = response2.as(HashMap.class);
        assertTrue(actualData.size()==0);

        /*
        We can run this test method 1000 times, no problem, cos we are deleting it after every run
         */

    }

}
