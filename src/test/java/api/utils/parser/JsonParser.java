package api.utils.parser;

import api.utils.logging.Log;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class JsonParser  {

    private static JsonParser instance;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Map<String,String> cache = new HashMap<>();

    // Private constructor to prevent instantiation
    private JsonParser() {}

    public static JsonParser getInstance() {
        if(instance == null){
            instance = new JsonParser();
        }
        return instance;
    }

    public void loadJsonData(String filePath){
        try (InputStream inputStream = new FileInputStream(filePath)) {
            Map<String, String> result = objectMapper.readValue(inputStream, new TypeReference<Map<String, String>>() {});
            cache.putAll(result);
        } catch (FileNotFoundException e) {
            Log.error(e,"File with such name or path not found: "+filePath);
            throw new RuntimeException("File with such name or path not found: " + filePath, e);
        } catch (IOException e) {
            Log.error(e,"Failed to parse JSON from file:"+ filePath);
            throw new RuntimeException("Failed to parse JSON from file: " + filePath, e);
        }
    }

    public String getValue(String key) {
        return cache.get(key);
    }
}
