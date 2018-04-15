package javase07.task2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedPropertyReader {

    private Map<String, String> propertyMap = new HashMap<>();
    private Lock lock = new ReentrantLock();

    public Map getPropertiesMap(){
        return propertyMap;
    }

    public void readPropertyFile(String path, String key){
        lock.lock();
        try (InputStream inputStream = new FileInputStream(path)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            String property = properties.getProperty(key);
            propertyMap.put(key, property);
        } catch (IOException e) {
            throw new RuntimeException("File or property could not be found", e);
        } finally {
            lock.unlock();
        }
    }
}
