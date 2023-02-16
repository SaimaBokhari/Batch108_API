package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GoRestPojo;
import test_data.GoRestTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Get10 extends GoRestBaseUrl {
    /*
        Given
            https://gorest.co.in/public/v1/users/13
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
           {
    "meta": null,
    "data": {
        "id": 1614,
        "name": "Sushil Desai",
        "email": "desai_sushil@dickens.biz",
        "gender": "male",
        "status": "inactive"
    }
}
     */

    @Test
    public void get10(){
        // Set the URL
        spec.pathParams("first", "users", "second", 1614);

        // Set the expected data
       GoRestTestData obj = new GoRestTestData();
       Map<String, String> goRestDataMap = obj.goRestDataMapSetUp("Sushil Desai", "desai_sushil@dickens.biz", "male", "inactive");

       Map<String, Object> expectedData = obj.expectedDataMapSetUp(null,goRestDataMap);
        System.out.println(expectedData);

        // Send the request and get the response
       Response response = given().spec(spec).when().get("/{first}/{second}");
       response.prettyPrint();

       // Do assertion
        // first de-serialisation .. actual and expected data should have same data type

        Map<String, Object> actualData  = response.as(HashMap.class);
        System.out.println("Actual data: " + actualData);

        // now assertion
        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("meta"), actualData.get("meta"));
        assertEquals(goRestDataMap.get("name"),(((Map)(actualData.get("data"))).get("name")));
        assertEquals(goRestDataMap.get("email"),(((Map)(actualData.get("data"))).get("email")));
        assertEquals(goRestDataMap.get("gender"),(((Map)(actualData.get("data"))).get("gender")));
        assertEquals(goRestDataMap.get("status"),(((Map)(actualData.get("data"))).get("status")));


        // With Pojo Class

        GoRestPojo actualDataWithPojo = response.as(GoRestPojo.class);
        System.out.println(actualDataWithPojo);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("meta"), actualDataWithPojo.getMeta());
        assertEquals(goRestDataMap.get("name"),actualDataWithPojo.getData().getName());
        assertEquals(goRestDataMap.get("email"),actualDataWithPojo.getData().getEmail());
        assertEquals(goRestDataMap.get("gender"),actualDataWithPojo.getData().getGender());
        assertEquals(goRestDataMap.get("status"), actualDataWithPojo.getData().getStatus());


    }



}
