package bzh.zomzog.prez.unitEvolution.controller;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Utility class for testing REST controllers.
 */
class MockMvcUtils {

    /**
     * Convert an object to JSON byte array.
     *
     * @param object
     * the object to convert
     * @return the JSON byte array
     * @throws IOException ioException
     */
    static String convertObjectToJson(final Object object) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        return mapper.writeValueAsString(object);
    }
}