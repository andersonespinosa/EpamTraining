package javase04.task4;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Serializer {
    private static final String pathToSerializedFile = "./src/main/java/javase04/task4/serializedFile.serial";

    public void serialize(Map<String, List<String>> filmsToActors) {
        try (FileOutputStream outputStream = new FileOutputStream(pathToSerializedFile);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(filmsToActors);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Map deserialize() {
        try (FileInputStream inputStream = new FileInputStream(pathToSerializedFile);
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            return (HashMap) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
