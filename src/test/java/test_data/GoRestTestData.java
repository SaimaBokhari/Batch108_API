package test_data;

import java.util.HashMap;
import java.util.Map;

public class GoRestTestData {

    // inner map
    public Map<String,String> goRestDataMapSetUp(String name, String email, String gender, String status){
        Map<String,String> goRestDataMapSetUp = new HashMap<>();
        goRestDataMapSetUp.put("name", name);
        goRestDataMapSetUp.put("email", email);
        goRestDataMapSetUp.put("gender", gender);
        goRestDataMapSetUp.put("status", status);

        return goRestDataMapSetUp;

    }


    // outer map

    public Map<String,Object> expectedDataMapSetUp (Object meta, Map<String, String> data ){
        Map<String,Object> expectedDataMapSetUp  = new HashMap<>();
        expectedDataMapSetUp .put("meta", meta);
        expectedDataMapSetUp .put("data", data);

        return expectedDataMapSetUp ;
    }
}

