package javase06.task2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyReader {
    private Map<String, String> propertyMap = new HashMap<>();

    public Map getPropertiesMap(){
        return propertyMap;
    }

    public void readPropertyFile(String path, String key){
        try (InputStream inputStream = new FileInputStream(path)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            String property = properties.getProperty(key);
            propertyMap.put(key, property);
        } catch (IOException e) {
            throw new RuntimeException("File or property could not be found", e);
        }
    }
}
