package bukalapak.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class JsonUtil {
    public static Map<String, Object> jsonToMap(Path pathToJsonFile) throws IOException {
        String jsonBody = new String(Files.readAllBytes(pathToJsonFile), StandardCharsets.UTF_8);

        return jsonToMap(jsonBody);
    }

    public static Map<String, Object> jsonToMap(String jsonString) throws IOException {
        return (new ObjectMapper()).readValue(jsonString, new TypeReference<Map<String, Object>>() {});
    }
}
