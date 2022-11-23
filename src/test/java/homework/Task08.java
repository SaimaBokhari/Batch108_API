package homework;

import base_urls.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.PetStoreTestData;

import java.util.Map;

import static io.restassured.RestAssured.given;

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
    */

    @Test
    public void task8(){

        // Set the URL
        spec.pathParams("first", "v2", "second","user");

        // Set the expected data

        PetStoreTestData user1 = new PetStoreTestData(134, "kana yari", "Affaf","Ahmed", "kanayari_rocks@gmail.com", "cokestudio2022", "0123456789", 82);

        // Send the request and get the response
        Response response = given(). spec(spec).contentType(ContentType.JSON).body(user1).when().post("/{first}/{second}");
        response.prettyPrint();






    }


}
