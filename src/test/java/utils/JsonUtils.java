package utils;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JsonUtils {
    protected static ObjectMapper mapper = new ObjectMapper();  // variable

    // <T> returns any data type
    public static <T> T convertJsonToJavaObject(String json, Class<T> cls){
        T javaResult = null;
        try {
            javaResult = mapper.readValue(json, cls);
        } catch (IOException e) {
            throw new RuntimeException(e.printStackTrace();
        }
        return javaResult;
    }



}
