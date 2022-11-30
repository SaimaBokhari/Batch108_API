package utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthenticationGMIBank {

//    public static void main(String[] args) {
//        System.out.println(generateToken());
//    }

    public static String generateToken(){
        Map<String, Object> mapGMI = new HashMap<>();
        mapGMI.put("password","John.123");
        mapGMI.put("rememberMe",true);
        mapGMI.put("username","john_doe");

        Response response = given().
                contentType(ContentType.JSON).
                body(mapGMI).when().
                post("https://www.gmibank.com//api/authenticate");

        return response.jsonPath().getString("id_token");
    }
}
