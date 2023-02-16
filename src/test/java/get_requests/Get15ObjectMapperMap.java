package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;
import utils.JsonUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Get15ObjectMapperMap extends JsonPlaceHolderBaseUrl {

    /*
        Given
	        https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send GET Request to the URL
	 	Then
	 		Status code is 200
	 		And response body is like {
									    "userId": 10,
									    "id": 198,
									    "title": "quis eius est sint explicabo",
									    "completed": true
									  }
     */

    @Test
    public void get15(){
        // Set the URL
        spec.pathParams("first","todos", "second", 198);

        // Set the expected data with ObjectMapper
        // following way of creating String variable is not recommended... so we need to create a method to handle it
//        String expectedDataInString = "{\n" +
//                "   \"userId\": 10,\n" +
//                "   \"id\": 198,\n" +
//                "   \"title\": \"quis eius est sint explicabo\",\n" +
//                "    \"completed\": true\n" +
//                "  }";

        // Recommended way:
       String expectedDataInString = new JsonPlaceHolderTestData().expectedDataInString(10, "quis eius est sint explicabo", true);


        // To convert our string into map, we call method from JsonUtil class like JsonUtils.convertJsonToJavaObject
        // JsonUtils.convertJsonToJavaObject method is the ObjectMapper, and it converts first parameter (which MUST be string) to second parameter (which can be any data type)... it's map in this example
        // we created this to handle the exception to readValue() .. see notes in Post05 class

        Map<String, Object> expectedData = JsonUtils.convertJsonToJavaObject(expectedDataInString, HashMap.class); // we can use pojo class here as well
        System.out.println("expectedData = " + expectedData);

        // Send the request and get the response
         Response response = given(). spec(spec). when().get("/{first}/{second}");
         response.prettyPrint();

         // Do assertion
        // first de-serialisation with ObjectMapper
        Map<String, Object> actualData = JsonUtils.convertJsonToJavaObject(response.asString(), HashMap.class);
        System.out.println("actualData = " + actualData);

        Assert.assertEquals(200, response.statusCode());
        Assert.assertEquals(expectedData.get("userId"), actualData.get("userId"));
        Assert.assertEquals(expectedData.get("title"), actualData.get("title"));
        Assert.assertEquals(expectedData.get("completed"), actualData.get("completed"));

    }


}
