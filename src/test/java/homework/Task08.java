package homework;

import base_urls.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.PetStoreTestData;

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

        assertEquals(200, response.statusCode());

        /*
         I got the status code 200 on swagger and on intelliJ as well.. but I don't get to see the body of the data
         in Json format when I pretty print it. This is the response body I see on the console. I don't know the reason.

        {
    "code": 200,
    "type": "unknown",
    "message": "134"
}

         */






    }


}
