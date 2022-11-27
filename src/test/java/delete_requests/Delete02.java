package delete_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import utils.AuthenticationHerOkuApp;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static utils.AuthenticationHerOkuApp.generateToken;

public class Delete02 extends HerOkuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/{bookingId}
        When
	 		I send DELETE Request to the Url
	 	Then
		 	Status code is 200
		 	And Response body is "Created"
     */

    /*
        How to automate Delete Request in API Testing?
        1. Create a new data by using Post request
        2. Use "Delete request" to delete new data
        3. Do Not delete existing data, always create a data to delete.

         */

    @Test
    public void delete02(){

        /* bearer token  or basic authority is used to delete any existing data
         basic authority => username: admin, password: password123 for herokuapp
         bearer token is in swagger documentation

         **READ the documentation before Delete request**

         To create bearer token, we need swagger documentation  ==>  https://restful-booker.herokuapp.com/apidoc/index.html#api-Auth-CreateToken
         1) Post request to Postman  2) copy/paste endpoint in postman  3) copy/paste Json body   .. will have bearer token for 24 hours
         like {
            "token": "70fa14222e47208"
        }
        Bearer Token 1619a6d1e962497 will be used to give you authority to delete the data according to the documentation
        READ the documentation before Delete request ...
         */

        // Set the URL
        spec.pathParams("first", "booking", "second", 176);

        // Set the expected date
        String expectedData = "Created";

        // Send the request and get the response

        Response response = given().
                spec(spec).
                headers("Cookie", "token=" + generateToken()).
                contentType(ContentType.JSON).when().delete("/{first}/{second}");

        response.prettyPrint();  // "created" on the console means that booking is deleted

        // Do assertion

        assertEquals(201, response.statusCode());
        assertEquals(expectedData, response.asString());


    }
}
