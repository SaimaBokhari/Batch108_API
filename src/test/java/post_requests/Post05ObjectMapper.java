package post_requests;

import base_urls.DummyApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import pojos.DummyApiDataPojo;
import pojos.DummyApiResponsePojo;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Post05ObjectMapper extends DummyApiBaseUrl {
    /*
       URL: https://dummy.restapiexample.com/api/v1/create
       HTTP Request Method: POST Request
       Request body: {
                        "employee_name": "Ali Can",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                     }
       Test Case: Type by using Gherkin Language
       Assert:
                i) Status code is 200
                ii) Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Ali Can",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image",
                            "id": 6344
                        },
                        "message": "Successfully! Record has been added."
                    }
     */

    /*
    Test case (in Gherkin Language):

    Given
          https://dummy.restapiexample.com/api/v1/create
    And
       {
            "employee_name": "Ali Can",
            "employee_salary": 111111,
            "employee_age": 23,
            "profile_image": "Perfect image"
          }

      When
          User sends Post Request
      Then
          Status code is 200
      And
          Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Ali Can",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image",
                            "id": 6344
                        },
                        "message": "Successfully! Record has been added."
                    }



     */

    @Test
    public void post05() throws IOException {

        // Set the URL
        spec.pathParam("first", "create");

        // Set the expected data
        // for inner pojo to do payload / to perform the post request

        DummyApiDataPojo expectedData = new DummyApiDataPojo("Ali Can", 111111, 23,"Perfect imag");
        System.out.println("expectedData = " + expectedData);

        // Send the request and get the response
       Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
       response.prettyPrint();

        // Do assertion
        // de-serialisation
        // today we are using  ObjectMapper from codehaus
        // readValue() will show error and ask to throw an exception
        DummyApiResponsePojo actualData = new ObjectMapper().readValue(response.asString(), DummyApiResponsePojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals("success", actualData.getStatus());
        assertEquals(expectedData.getEmployee_name(), actualData.getData().getEmployee_name());
        assertEquals(expectedData.getEmployee_salary(), actualData.getData().getEmployee_salary());
        assertEquals(expectedData.getEmployee_age(), actualData.getData().getEmployee_age());
        assertEquals(expectedData.getProfile_image(),actualData.getData().getProfile_image());

        assertEquals("Successfully! Record has been added.", actualData.getMessage());

    }


}
