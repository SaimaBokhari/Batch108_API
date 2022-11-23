package homework;

import base_urls.ReqresInBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.ReqresInTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class Task07 extends ReqresInBaseUrl {
    /*
        Given
            1) https://reqres.in/api/users
            2) {
                "name": "morpheus",
                "job": "leader"
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "leader",
                                                "id": "496",
                                                "createdAt": "2022-11-22T06:40:22.293Z"
                                              }
     */

    @Test
    public void task07(){
        // Set the URL
        spec.pathParams("first","users");

        // Set the expected data
        ReqresInTestData obj = new ReqresInTestData();
        Map<String,String> expectedData = obj.expectedDataReqres("morpheus", "leader");
        System.out.println(expectedData);

        // Send the request and get the response

        Response response = given().spec(spec).contentType(ContentType.JSON). body(expectedData). when().post("/{first}");
        response.prettyPrint();

        // Do assertion
        // We need expected data and actual data to do assertion

        Map<String, String> actualData = response.as(HashMap.class);
        System.out.println("actualData" + actualData);

        // soft assertion

        response.
                then().
                assertThat().
                statusCode(201).
                body("name",equalTo("morpheus"),
                        "job", equalTo("leader"));

        // hard assertion

        assertEquals(201, response.statusCode());
        assertEquals(actualData.get("name"), expectedData.get("name"));
        assertEquals(actualData.get("job"), expectedData.get("job"));


    }

}
