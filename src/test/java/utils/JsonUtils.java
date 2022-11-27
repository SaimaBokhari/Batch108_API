package utils;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JsonUtils {

    // This is recommended way to avoid throws exception every time we use readValue()
    protected static ObjectMapper mapper;  // variable


    // static block works before every process and this object will be created once and used in every class.
    static {
        mapper = new ObjectMapper();
    }



    // This is Generic method. This will accept two parameters and convert 1st parameter
    // to 2nd parameter data tye by using ObjectMapper Class
    // <T> T is a return type which returns any data type
    public static <T> T convertJsonToJavaObject(String json, Class<T> cls){  // Generic method
        T javaResult = null;
        try {
            javaResult = mapper.readValue(json, cls);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return javaResult;
    }


}
