package get_requests;

import base_urls.GoRestBaseUrl;
import org.junit.Test;

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
            "id": 13,
            "name": "Dandak Adiga",
            "email": "adiga_dandak@christiansen-schimmel.biz",
            "gender": "female",
            "status": "active"
                 }
            }
     */

    @Test
    public void get10(){
        // Set the URL
        spec.pathParams("first", "users", "second", 13);


    }







}
