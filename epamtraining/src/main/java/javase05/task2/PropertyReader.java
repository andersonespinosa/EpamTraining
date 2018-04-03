package javase05.task2;

import java.nio.file.Path;
import java.util.ResourceBundle;

public class PropertyReader {

    public void getProperty(Path path, String key) {
        try {
            String stringPath = path.toString();
            ResourceBundle resourceBundle = ResourceBundle.getBundle(stringPath);
            resourceBundle.getString(key);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Wrong file path or property key", e);
        }
    }
}
