package homework;

import base_urls.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class Task05 extends ReqresBaseUrl {

     /*
        Given
          https://reqres.in/api/unknown/3

        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json”
        And
            Response body should be like;(Soft Assertion)
        {
        "data": {
            "id": 3,
            "name": "true red",
            "year": 2002,
            "color": "#BF1932",
            "pantone_value": "19-1664"
        },
        "support": {
            "url": "https://reqres.in/#support-heading",
            "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
        }
}
      */

    @Test
    public void task05(){
        // Set the URL
        spec.pathParams("first", "unknown", "second", 3);

        // Set the expected data

        // Send the request and get the data

        Response response= given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // Do assertion (soft-assertion)
        // 1st. Create softAssert Object
        JsonPath jsonPath = response.jsonPath();
        SoftAssert softAssert = new SoftAssert();


        softAssert.assertEquals(jsonPath.getInt("data.id"), 3, "Id did not match");
        softAssert.assertEquals(jsonPath.getString("data.name"), "true red", "Name did not match");
        softAssert.assertEquals(jsonPath.getInt("data.year"), 2002, "Year did not match");
        softAssert.assertEquals(jsonPath.getString("data.color"), "#BF1932", "Color did not match");
        softAssert.assertEquals(jsonPath.getString("data.pantone_value"), "19-1664", "pantone_value did not match");

        softAssert.assertEquals(jsonPath.getString("support.url"), "https://reqres.in/#support-heading");
        softAssert.assertEquals(jsonPath.getString("support.text"), "To keep ReqRes free, contributions towards server costs are appreciated!");

        softAssert.assertAll();
    }

}
