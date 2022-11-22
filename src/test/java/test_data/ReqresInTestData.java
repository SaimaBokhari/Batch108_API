package test_data;

import java.util.HashMap;
import java.util.Map;

public class ReqresInTestData {

    public Map<String, String> expectedDataReqres(String name, String job, String createdAt){
        Map<String, String> expectedData = new HashMap<>();

        expectedData.put("name", name);
        expectedData.put("job", job);
        expectedData.put("createdAt", createdAt);

        return expectedData;

    }

}
