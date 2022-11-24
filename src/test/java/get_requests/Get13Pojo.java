package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GoRestDataPojo;
import pojos.GoRestPojo;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Get13Pojo extends GoRestBaseUrl {
    /*
        Given
            https://gorest.co.in/public/v1/users/131
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
                        {
                            "meta": null,
                            "data": {
                                "id": 131,
                                "name": "Prem Pilla",
                                "email": "pilla_prem@mueller.info",
                                "gender": "female",
                                "status": "active"
                            }
                        }
    */


    @Test
    public void get13(){
        // Set the URL
        spec.pathParams("first", "users", "second", 131);

        // Set the expected data
        // for inner pojo

        GoRestDataPojo goRestDataPojo = new GoRestDataPojo("Prem Pilla", "pilla_prem@mueller.info", "female", "active");
        System.out.println("goRestDataPojo = " + goRestDataPojo);

        // outer pojo
        GoRestPojo expectedData = new GoRestPojo(null, goRestDataPojo);
        System.out.println("expectedData = " + expectedData);

        // Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // Do assertion
        GoRestPojo actualData = response.as(GoRestPojo.class);
        System.out.println("actualData = " + actualData);


        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getMeta(), actualData.getMeta());
        assertEquals(goRestDataPojo.getName(), actualData.getData().getName());
        assertEquals(goRestDataPojo.getEmail(), actualData.getData().getEmail());
        assertEquals(goRestDataPojo.getGender(), actualData.getData().getGender());
        assertEquals(goRestDataPojo.getStatus(), actualData.getData().getStatus());


    }
}
