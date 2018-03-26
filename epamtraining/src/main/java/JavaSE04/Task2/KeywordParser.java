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
    private String codeFile = "D:\\Java\\JavaCode.txt";
    private String keywordsFile = "D:\\Java\\JavaKeywords.txt";
    private List<String> codeFileWords = new ArrayList<>();
    private List<String> keywordsFileWords = new ArrayList<>();
    private List<String> foundKeywords = new ArrayList<>();

    public static void main(String[] args) {
        KeywordParser parser = new KeywordParser();
        parser.parseFile(parser.codeFile, parser.codeFileWords);
        parser.parseFile(parser.keywordsFile, parser.keywordsFileWords);
        parser.searchForKeywords();
        for (String st : parser.foundKeywords) {
            System.out.println(st);
        }
    }

    private void parseFile(String pathToFile, List parsedStrings) {
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

    private void searchForKeywords() {
        for (String codeFileWord : codeFileWords) {
            for (String keywordsFileWord : keywordsFileWords) {
                if (codeFileWord.equals(keywordsFileWord)) {
                    foundKeywords.add(keywordsFileWord);
                }
            }
        }
    }


}
