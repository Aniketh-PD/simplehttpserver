package org.example.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

public class Json {
    private static ObjectMapper objectMapper = defaultObjectMapper();

    private static ObjectMapper defaultObjectMapper (){
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        return om;
    }
    public static JsonNode parse(String jsonStr) throws JsonProcessingException {
        return objectMapper.readTree(jsonStr);
    }
    public static <A> A fromJson(JsonNode node ,Class<A> cls) throws JsonProcessingException {
        return objectMapper.treeToValue(node,cls);
    }

    public static JsonNode toJson(Object obj){
        return objectMapper.valueToTree(obj);
    }

    public static String stringify(Object o) throws JsonProcessingException {
        return generateJson(o, false);
    }

    public static String stringifyPretty(Object o) throws JsonProcessingException {
        return generateJson(o, true);
    }

    private static String generateJson(Object o, boolean pretty) throws JsonProcessingException {
        ObjectWriter objectWriter = objectMapper.writer();
        if(pretty){
            objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);
        }
        return objectWriter.writeValueAsString(o);
    }
}
