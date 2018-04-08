package javase03.task3;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class PictureParser {

    public void parseImages() {
        Pattern pattern = Pattern.compile("(?ui)^.+(?= )\\((рис(.|унок) [0-9](-[а-я]+)+?)+\\).*$");

        try (Scanner scanner = new Scanner(new File("Article.html"))) {
            while (scanner.hasNextLine()) {
                String str = scanner.findInLine(pattern);
                System.out.println(str);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
