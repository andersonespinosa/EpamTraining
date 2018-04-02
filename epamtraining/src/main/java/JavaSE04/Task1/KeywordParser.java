package javase04.Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class KeywordParser {

    public List<String> parse(String codeFilePath, String keywordsFilePath) {
        List<String> codeFile = readFromFile(codeFilePath);
        List<String> keywordsFile = readFromFile(keywordsFilePath);
        List<String> foundKeywords = new ArrayList<>();

        for (String codeFileWord : codeFile) {
            for (String keywordsFileWord : keywordsFile) {
                if (codeFileWord.equals(keywordsFileWord)) {
                    foundKeywords.add(keywordsFileWord);
                }
            }
        }
        return foundKeywords;
    }

    private List<String> readFromFile(String filePath) {
        List<String> fileContent = new ArrayList<>();
        Path path = Paths.get(filePath);
        try (InputStream in = Files.newInputStream(path);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;
    }


}
