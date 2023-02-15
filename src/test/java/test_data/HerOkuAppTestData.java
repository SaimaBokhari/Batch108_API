package test_data;

import java.util.HashMap;
import java.util.Map;

public class HerOkuAppTestData {

    public Map<String, String> bookingDatesMapSetUp (String checkin, String checkout){

        Map<String, String> bookingDatesMap = new HashMap<>();
        bookingDatesMap.put("checkin", checkin);
        bookingDatesMap.put("checkout",checkout);

        return bookingDatesMap; // inner json

    }

    public Map<String, Object> expectedDataSetUp(String firstname, String lastname, Integer totalprice, Boolean depositpaid, Map<String, String> bookingdates, String additionalneeds){

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", firstname);
        expectedData.put("lastname", lastname);
        expectedData.put("totalprice", totalprice);
        expectedData.put("depositpaid", depositpaid);
        expectedData.put("bookingdates", bookingdates);
        expectedData.put("additionalneeds", additionalneeds);

        return expectedData;
    }

    public String expectedDataInString(String firstname, String lastname, Integer totalprice ,
                                       Boolean depositpaid, String checkin, String checkout, String additionalneeds){
        String expectedData = "{\n" +
                "    \"firstname\": \""+firstname+"\",\n" +
                "    \"lastname\": \""+lastname+"\",\n" +
                "    \"totalprice\": "+totalprice+",\n" +
                "    \"depositpaid\": "+depositpaid+",\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \""+checkin+"\",\n" +
                "        \"checkout\": \""+checkout+"\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \""+additionalneeds+"\"\n" +
                "}";

        return expectedData;
    }

}
