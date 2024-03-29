package get_requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get01 {
    /*

    API stands for “application programming interface.”

More simply, it is a collection of definitions, instructions, and protocols for building and connecting software.
An API is the connection between two programs that exposes the select business or operational value of one to the other.

- APIs establish a connection between systems and enables data access
- Allows monitoring of users and their activity
- Well-designed APIs Increase security
- Hides back-end complexity from developers



    What is a POM? A Project Object Model or POM is the fundamental unit of work in Maven.
    It is an XML file that contains information about the project and configuration details
    used by Maven to build the project. It contains default values for most projects.

    We need to import following libraries onto pom xml page:
    - Rest_Assured Library
    - Junit
    These two will be most used ones
    - Json
    - Testng
    - Google/gson
    - Codehaus-jackson

     */

    /*
    1) Postman is used for manual API testing.
    2) We use Rest_Assured Library for Automation API Testing.
    3) To type automation script, we need to follow these steps:
        a) understand the requirement (from Swagger Document)
        b) type the test cases

        To type cases, we use 'Gherkin Language'
        The keywords are
                 i) Given: it is used for preconditions
                ii) When: it is used for actions
               iii) Then: it is used for outputs
                iv) And: it is used for multiple given, when and then

Given: Used to describe the scenario scene. It is mandatory. It declares a Pre-requisite
When: It is used to describe an event or action.
Then: It is used to define the expected result/output. It is mandatory.
And: You can use the keyword “Given” one after the other when writing the given ones,
     or you can add the keyword “And” instead, after the first definition of “Given”.
     You can also use (*) instead of “And”.
But: Used with negative situations.

What's a protocol?
A network protocol is an established set of rules that determine how data is transmitted
between different devices in the same network. Essentially, it allows connected devices
to communicate with each other, regardless of any differences in their internal
processes, structure or design.
HTTP, REST and SOAP are API protocols.
HTTP -> Hyper Text Transfer Protocol


        c) Four steps to type Automation Script
                 i) Set the URL
                ii) Set the expected data (Post, Put, Patch requests)
               iii) Send the request and get the response
                iv) Do Assertion (assert the data using junit) means verify it.


     */

    // we use test method instead of main method by using the annotation @Test

    /*
    Task 1:

   Given
       https://restful-booker.herokuapp.com/booking/101
   When
       User sends a GET Request to the url
   Then
       HTTP Status Code should be 200
   And
       Content Type should be JSON
   And
       Status Line should be HTTP/1.1 200 OK
*/

    @Test
    public void get01(){

        // i) Set the URL
        String url = "https://restful-booker.herokuapp.com/booking/3";  // primitive way of setting but we'll learn better way later

        // ii) Set the expected data


        // iii) Send the request and get the response

        Response response = given().when().get(url);   // Response is interface and datatype
        response.prettyPrint();   // this will print out JSON data format

        // iv) Do Assertion
        response.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");

        /*
        If you have multiple errors on assertions, execution will be stopped in the first error.
        Next codes will not be executed. You will not get the error message about the 2nd, 3rd or 4th error.
        This is called HARD ASSERTION.

        If all codes are executed, and you get the report for all assertions, it is called
        SOFT ASSERTION (also called Verification)
         */

        // response has 'header' inside it so contains the data about the data (metadata)


        // How to print status code, content type and status line on console (like we used to do before in Java)
        System.out.println("Status code: " + response.statusCode());
        System.out.println("Content type: " + response.contentType());
        System.out.println("Status Line: " + response.statusLine());

        System.out.println("================");

        // How to print  specific "Header" on the console?
        System.out.println("Server: " + response.getHeader("Server"));
        System.out.println("Connection: " + response.header("Connection"));

        // How to print  all "Header" on the console?
        System.out.println("Headers: " + response.getHeaders());

        // How to print "time" on console?
        System.out.println("Time: " + response.getTime());


    }

}
