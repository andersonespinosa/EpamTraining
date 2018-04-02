package javase04.Task3;

import java.io.*;

public class EncodingChanger {

    public void changeEncoding(String initialFilePath, String finalFilePath) {

        try (FileReader reader = new FileReader(initialFilePath);
             FileOutputStream outputStream = new FileOutputStream(finalFilePath);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-16");
             BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter)) {
            int value;
            while ((value = reader.read()) != -1) {
                bufferedWriter.write(value);
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
