package javase03.task3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PictureParser {

    public void parseImages() {
        String stringPath = "Article.html";
        Path path = Paths.get(stringPath);
        Pattern pattern = Pattern.compile("(рис.)");

        try (InputStream in = Files.newInputStream(path);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in, "Cp1251"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if(matcher.find()){
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("IO Exception", e);
        }
    }
}
