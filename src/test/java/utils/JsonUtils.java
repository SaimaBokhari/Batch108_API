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

    // 1st: De-serialisation Method
    // This is Generic method coz it works with every kind of data type. This will accept two parameters and convert 1st parameter
    // to 2nd parameter data type by using ObjectMapper Class
    // <T> T is a return type which returns any data type

    public static <T> T convertJsonToJavaObject(String json, Class<T> cls){  // Generic method
        T javaResult = null;  // javaResult is the container which will carry the Java object that we are creating
        try {
            javaResult = mapper.readValue(json, cls);
        } catch (IOException e) {
            System.out.println("Json could not be converted to Java Object" + e.getMessage());
        }
        return javaResult;
    }

    // Most probably this type of util classes will be given to you by your company


    // 2nd: Serialisation Method
    public static String convertJavaToJson(Object obj){
        String jsonResult = null;
        try {
            jsonResult = mapper.writeValueAsString(obj);
        } catch (IOException e) {
            System.out.println("Java object could not be converted to Json " + e.getMessage());
        }
        return jsonResult;
    }


}
