package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Post03Pojo extends JsonPlaceHolderBaseUrl {
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
    public void post03(){

        // set the URL
        spec.pathParam("first", "todos");

        // Set the expected data
        // create an object from pojo class

        JsonPlaceHolderPojo expectedData = new JsonPlaceHolderPojo(55, "Tidy your room", false);
        System.out.println(expectedData);

        // Send the request and get the response
        // serialisation (by specifying the content type to be posted
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        // Do assertion
        JsonPlaceHolderPojo actualData = response.as(JsonPlaceHolderPojo.class); // de-serialisation
        System.out.println(actualData);

/*
@JsonIgnoreProperties(ignoreUnknown = true)
We have to include this annotation on top of pojo class in order to handle the exception
 This will help ignore any value which is not present in response body.

 */
        assertEquals(expectedData.getUserId(), actualData.getUserId());
        assertEquals(expectedData.getTitle(), actualData.getTitle());
        assertEquals(expectedData.getCompleted(), actualData.getCompleted());



    }

}
