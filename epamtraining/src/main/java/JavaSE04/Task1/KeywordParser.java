package JavaSE04.Task1;

import com.sun.org.apache.xerces.internal.xs.StringList;

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
//    private Path codeFile = Paths.get("D:\\Java\\JavaCode.txt");
//    private Path keywordsFile = Paths.get("D:\\Java\\JavaKeywords.txt");
//    private List<String> codeFileWords = new ArrayList<>();
//    private List<String> keywordsFileWords = new ArrayList<>();
//    private List<String> foundKeywords = new ArrayList<>();

    public List<String> parseFile(String filePath) {
        List<String> codeFileWords = new ArrayList<>();
        Path codeFilePath = Paths.get(filePath);
        try (InputStream in = Files.newInputStream(codeFilePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                codeFileWords.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return codeFileWords;
    }

    public List<String> searchForKeywords(List<String> parsedFileStrings) {
        List<String> keywordsFileWords = new ArrayList<>();//add file JavaKeywords
        List<String> foundKeywords = new ArrayList<>();

        for (String codeFileWord : parsedFileStrings) {
            for (String keywordsFileWord : keywordsFileWords) {
                if (codeFileWord.equals(keywordsFileWord)) {
                    foundKeywords.add(keywordsFileWord);
                }
            }
        }
        return foundKeywords;
    }


}
