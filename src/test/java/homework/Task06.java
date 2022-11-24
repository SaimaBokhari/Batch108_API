package homework;

import base_urls.ReqresBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Task06 extends ReqresBaseUrl {
     /*
       Given
              https://reqres.in/api/unknown/
       When
            I send GET Request to the URL
       Then

            1)Status code is 200
            2)Print all pantone_values
            3)Print all ids greater than 3 on the console
              Assert that there are 3 ids greater than 3
            4)Print all names whose ids are less than 3 on the console
              Assert that the number of names whose ids are less than 3 is 2
    */

    @Test
    public void task06(){
        // Set the URL
        spec.pathParam("first", "unknown");

        // Set the expected data

        // Send the request and get the response
        Response response= given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        // Do assertion

        // 1)Status code is 200
        assertEquals(200, response.statusCode());

        // 2)Print all pantone_values
        JsonPath jsonPath = response.jsonPath();
        List<String> pantoneValueList = jsonPath.getList("data.pantone_value");
        System.out.println("pantoneValueList = " + pantoneValueList);


        //  3)Print all ids greater than 3 on the console
        //    Assert that there are 3 ids greater than 3

        //a. Using Groovy
        List<Integer> idsGreaterThan3 = jsonPath.getList("data.findAll{it. id>3}.id");
        System.out.println("idsGreaterThan3 = " + idsGreaterThan3);

        assertEquals(3, idsGreaterThan3.size());

        //b. using Lambda
        List<Integer> ids=jsonPath.getList("data.id");
        List<Integer> idsGraterThan3Lambda = ids.stream().filter(t->t>3).collect(Collectors.toList());
        System.out.println(ids.size());
        System.out.println(idsGraterThan3Lambda.size());

        assertEquals(3,idsGraterThan3Lambda.size());


        // 4)Print all names whose ids are less than 3 on the console
        //   Assert that the number of names whose ids are less than 3 is 2

        // using groovy
        List<String> names = jsonPath.getList("data.findAll{it.id<3}.name");
        System.out.println("names = " + names);

        assertEquals(2, names.size());

        // using lambda
        List<String> namesListLambda = jsonPath.getList("data.findAll{it.id<3}.name");
        System.out.println("namesListLambda = " + namesListLambda);
        namesListLambda.stream().forEach(System.out::println);

        assertEquals(2,namesListLambda.size());

    }



}

/*
{
    "page": 1,
    "per_page": 6,
    "total": 12,
    "total_pages": 2,
    "data": [
        {
            "id": 1,
            "name": "cerulean",
            "year": 2000,
            "color": "#98B2D1",
            "pantone_value": "15-4020"
        },
        {
            "id": 2,
            "name": "fuchsia rose",
            "year": 2001,
            "color": "#C74375",
            "pantone_value": "17-2031"
        },
        {
            "id": 3,
            "name": "true red",
            "year": 2002,
            "color": "#BF1932",
            "pantone_value": "19-1664"
        },
        {
            "id": 4,
            "name": "aqua sky",
            "year": 2003,
            "color": "#7BC4C4",
            "pantone_value": "14-4811"
        },
        {
            "id": 5,
            "name": "tigerlily",
            "year": 2004,
            "color": "#E2583E",
            "pantone_value": "17-1456"
        },
        {
            "id": 6,
            "name": "blue turquoise",
            "year": 2005,
            "color": "#53B0AE",
            "pantone_value": "15-5217"
        }
    ],
    "support": {
        "url": "https://reqres.in/#support-heading",
        "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
    }
}
 */