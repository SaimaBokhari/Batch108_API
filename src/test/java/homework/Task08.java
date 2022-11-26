package homework;

import base_urls.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.PetStoreTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Task08 extends PetStoreBaseUrl {
     /*
    Type automation code to create a 'user' by using "https://petstore.swagger.io/"" documantation.

    https://petstore.swagger.io/user/createWithList
[
  {
    "id": 134,
    "username": "kana yari",
    "firstName": "Affaf",
    "lastName": "Ahmed",
    "email": "kanayari_rocks@gmail.com",
    "password": "cokestudio2022",
    "phone": "0123456789",
    "userStatus": 82
  }
]

Then
            Status code is 200
            And response body should be like {
                                                "code": 200,
                                                "type": "unknown",
                                                "message": "6874988058"
                                             }
    */

    @Test
    public void task8(){

        // Set the URL
        spec.pathParams("first", "v2", "second","user");

        // Set the expected data

        PetStoreTestData user = new PetStoreTestData(134, "kana yari", "Affaf","Ahmed", "kanayari_rocks@gmail.com", "cokestudio2022", "0123456789", 82);
        Map<String, Object> expectedDataUser = user.expectedDataUserSetUp(134, "kana yari", "Affaf","Ahmed", "kanayari_rocks@gmail.com", "cokestudio2022", "0123456789", 82);

        // Send the request and get the response
        Response response = given(). spec(spec).contentType(ContentType.JSON).body(expectedDataUser).when().post("/{first}/{second}");
        response.prettyPrint();

        // Do assertion

        Map<String ,Object> actualData =response.as(HashMap.class);
        assertEquals(200,response.statusCode());
        assertEquals(200,actualData.get("code"));
        assertEquals("unknown",actualData.get("type"));

        /*

@Test
    public void post01() {
        spec.pathParam("first", "user");

        //You can create payload by using pojo class as well.

        Map<String, Object> expectedData = new HashMap<>();//You can create payload by using pojo class as well.
        expectedData.put("username", "JohnDoe");
        expectedData.put("firstName", "John");
        expectedData.put("lastName", "Doe");
        expectedData.put("email", "john@doe.com");
        expectedData.put("password", "1234");
        expectedData.put("phone", "1234");
        expectedData.put("userStatus", 123);

        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        Map<String ,Object> actualData =response.as(HashMap.class);
        assertEquals(200,response.statusCode());
        assertEquals(200,actualData.get("code"));
        assertEquals("unknown",actualData.get("type"));
    }
         */






    }


}
