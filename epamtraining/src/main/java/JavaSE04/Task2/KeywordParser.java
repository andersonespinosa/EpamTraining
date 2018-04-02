package JavaSE04.Task2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class KeywordParser {
    private String codeFile = "main/resources/JavaCode.txt";
    private String keywordsFile = "main/resources/JavaKeywords.txt";
    private List<String> codeFileWords = new ArrayList<>();
    private List<String> keywordsFileWords = new ArrayList<>();
    private List<String> foundKeywords = new ArrayList<>();

    public void parseFile(String pathToFile, List parsedStrings) {
        try (Scanner scanner = new Scanner(new File(pathToFile))) {
            scanner.useDelimiter(" ");
            while (scanner.hasNextLine()) {
                String value = scanner.nextLine();
                // parsedStrings.add(value);
                Collections.addAll(parsedStrings, value.split(" "));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchForKeywords() {
        for (String codeFileWord : codeFileWords) {
            for (String keywordsFileWord : keywordsFileWords) {
                if (codeFileWord.equals(keywordsFileWord)) {
                    foundKeywords.add(keywordsFileWord);
                }
            }
        }
    }
}
