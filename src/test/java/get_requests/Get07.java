package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import com.google.common.io.LittleEndianDataInputStream;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Get07 extends JsonPlaceHolderBaseUrl {

    /*
        Given
	   	    https://jsonplaceholder.typicode.com/todos
		When
			 I send GET Request to the URL
		Then
			 1)Status code is 200
			 2)Print all ids greater than 190 on the console
			   Assert that there are 10 ids greater than 190
			 3)Print all userIds whose ids are less than 5 on the console
			   Assert that the number of userIds whose ids are less than 5 is 4
			 4)Print all titles whose ids are less than 5
			   Assert that "delectus aut autem" is one of the titles whose id is less than 5

			   [ {key:value}, {}, {}, {}, .....] Json list

     */

    @Test
    public void get07(){

        // Set the URL
        spec.pathParam("first", "todos");

        // Set the expected data


        // Send the request and get the response
       Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        // Do assertion
        // 1)Status code is 200
        assertEquals(200, response.statusCode());

        // 2)Print all ids greater than 190 on the console
        //	 Assert that there are 10 ids greater than 190

       JsonPath jsonPath = response.jsonPath();  // we use jsonPath object to extract data out of response body (JSON format) so we can manipulate
      List<Integer> ids = jsonPath.getList("id");  // once we get the ids, we assign it to a normal int list named 'ids' here
       // System.out.println("ids: " + ids);

        List<Integer> idsGreaterThan190 = new ArrayList<>();
        for(int w: ids){
            if(w>190){
                // System.out.println(w);
                idsGreaterThan190.add(w);
            }
        }
        System.out.println("ids greater than 190: " + idsGreaterThan190);
        //Now assert them
        assertEquals(10, idsGreaterThan190.size());


        // 2nd way: using Groovy Language --  Recommended
       List<Integer> idsGreaterThan190Groovy = jsonPath.getList("findAll{it.id>190}.id");    // 'it' is like 't->' in lambda and 'this' in this.class in Java
        // it means "from the Json Data which we are working in"
        System.out.println("idsGreaterThan190Groovy: " + idsGreaterThan190Groovy);
        assertEquals(10, idsGreaterThan190Groovy.size());




        /*
        You can also use lambda
        List greaterThan190=ids.stream().filter(t-> t>190).collect(Collectors.toList());
        System.out.println(greaterThan190);
         */


        // 3) Print all userIds whose ids are less than 5 on the console
        //	Assert that the number of userIds whose ids are less than 5 is 4

        List<Integer> userIds = jsonPath.getList("findAll{it. id<5}.userId");
        System.out.println("userIds: " + userIds);
        Collections.sort(userIds); // elements are sorted in ascending order
        //assertEquals((Integer)4, userIds.get(userIds.size()-1));
        // OR
        assertEquals(4, userIds.size());

        // 4)  Print all titles whose ids are less than 5
        //	   Assert that "delectus aut autem" is one of the titles whose id is less than 5

        List<Integer> title = jsonPath.getList("findAll{it.id<5}.title");
        System.out.println("titles: " + title);
        //1st way:
        assertTrue("\"delectus aut autem\" doesn't exist" ,title.contains("delectus aut autem"));


        //2nd way: lambda
        // assertTrue("\"delectus aut autem\" doesn't exist" ,title.stream().anyMatch(t-> t.equals("delectus aut autem")));


        // These are all function tests
    }




}
