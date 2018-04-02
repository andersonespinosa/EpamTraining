package javase04.Task4;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Serializer {
    private final static String pathToSerializedFile = "./src/main/java/javase04/Task4/serializedFile.serial";

    public void serialize(Map<String, List<String>> filmsToActors) {
        try (FileOutputStream outputStream = new FileOutputStream(pathToSerializedFile);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(filmsToActors);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map deserialize(String fileName) {
        try (FileInputStream inputStream = new FileInputStream(pathToSerializedFile);
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            return (HashMap) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new HashMap();
    }


}
