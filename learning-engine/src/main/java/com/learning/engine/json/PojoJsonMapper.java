package com.learning.engine.json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.type.TypeReference;

import com.learning.engine.BusinessException;

/**
 * Convenience class to convert POJO to JSON and vice versa.
 *
 * @author Yosua
 */
public class PojoJsonMapper {
    private final static ObjectMapper mapper = new ObjectMapper();

    static {
//        mapper.getSerializationConfig().setDateFormat(new SimpleDateFormat("dd MM yyyy HH:mm:ss"));
//        mapper.getDeserializationConfig().setDateFormat(new SimpleDateFormat("dd MM yyyy HH:mm:ss"));
    	mapper.setDateFormat(new SimpleDateFormat("dd MM yyyy HH:mm:ss"));
        mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationConfig(mapper.getSerializationConfig().withSerializationInclusion(Inclusion.NON_NULL));
    }

    /**
     * Convert POJO to its JSON string.
     * @param pojo POJO to be converted
     * @param <T> type parameter
     * @return JSON string
     */
    public static <T> String toJson(T pojo) {
        String result;
        try {
            result = mapper.writeValueAsString(pojo);
        } catch (IOException e) {
            e.printStackTrace();
            //-- PJMTJ = POJO JSON MAPPER TO JSON
            throw new BusinessException("LS-0500", "PJMTJ");
        }

        return result;
    }

    /**
     * Convert JSON string to specified POJO class.
     * @param json JSON string
     * @param pojoClass POJO class
     * @param <T> type parameter
     * @return POJO object
     */
    public static <T> T fromJson(String json, Class<T> pojoClass) {
        T object;
        try {
            object = mapper.readValue(json, pojoClass);
        } catch (IOException e) {
            e.printStackTrace();
            //-- PJMFJ = POJO JSON MAPPER FROM JSON
            throw new BusinessException("LS-0500", "PJMFJ");
        }

        return object;
    }

    /**
     * Convert JSON string to specified POJO class.
     * @param json JSON string
     * @param valueTypeRef value of type reference
     * @return Object
     */
    public static <T> T fromJson(String json, TypeReference<T> valueTypeRef) {
        T object;
        try {
            object = mapper.<T>readValue(json, valueTypeRef);
        } catch (IOException e) {
            e.printStackTrace();
            //-- PJMFJ = POJO JSON MAPPER FROM JSON
            throw new BusinessException("LS-0500", "PJMFJ");
        }

        return object;
    }
    
}
