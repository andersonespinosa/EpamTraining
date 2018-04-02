package javase04.Task2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KeywordParser {

    public void parse(String codeFilePath, String keywordsFilePath) {
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
    }

    private List readFromFile(String pathToFile) {
        List<String> fileContent = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(pathToFile))) {
            scanner.useDelimiter(" ");
            while (scanner.hasNextLine()) {
                String value = scanner.nextLine();
                fileContent.add(value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;
    }


}
