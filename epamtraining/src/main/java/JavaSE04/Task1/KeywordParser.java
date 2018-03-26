package JavaSE04.Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KeywordParser {
    private Path codeFile = Paths.get("D:\\Java\\JavaCode.txt");
    private Path keywordsFile = Paths.get("D:\\Java\\JavaKeywords.txt");
    private List<String> codeFileWords = new ArrayList<>();
    private List<String> keywordsFileWords = new ArrayList<>();
    private List<String> foundKeywords = new ArrayList<>();

    public static void main(String[] args) {
        KeywordParser parser = new KeywordParser();
        parser.parseFile(parser.codeFile, parser.codeFileWords);
        parser.parseFile(parser.keywordsFile, parser.keywordsFileWords);
        parser.searchForKeywords();
        for(String keyword : parser.foundKeywords){
            System.out.println(keyword);
        }

    }

    private void parseFile(Path pathToFile, List parsedStrings) {
        try (InputStream in = Files.newInputStream(pathToFile);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Collections.addAll(parsedStrings, line.split(" "));
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
