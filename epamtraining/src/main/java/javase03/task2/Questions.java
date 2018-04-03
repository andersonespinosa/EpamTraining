package javase03.task2;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.ResourceBundle;

public class Questions {
    private static ResourceBundle myResourceBundle;

    public void getAnswers() {
        Questions questions = new Questions();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Choose your locale\\Выберите язык: \nPress 1 for English\nНажмите 2 для русского");
            String tmpBr = bufferedReader.readLine();
            Integer localNum = Integer.parseInt(tmpBr);

            if (localNum == 1) {
                questions.engLocale(bufferedReader);
            } else if (localNum == 2) {
                questions.ruLocale(bufferedReader);
            } else {
                questions.engLocale(bufferedReader);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void engLocale(BufferedReader bufferedReader) throws IOException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("javase03/BundleResource", Locale.ENGLISH);
        System.out.println("Please input number of question you want to answer for you from 1-3:");
        System.out.println("Question 1 ?");
        System.out.println("Question 2 ?");
        System.out.println("Question 3 ?");
        System.out.println("exit for exit.");

        String key = bufferedReader.readLine();
        readIntFromUser(resourceBundle, key);
    }

    private void ruLocale(BufferedReader bufferedReader) throws IOException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("javase03/BundleResource", Locale.forLanguageTag("RU"));
        System.out.println("Пожалуйста введите номер вопроса, чтобы узнать ответ от 1-3:");
        System.out.println("Вопрос 1 ?");
        System.out.println("Вопрос 2 ?");
        System.out.println("Вопрос 3 ?");

        String key = bufferedReader.readLine();
        readIntFromUser(resourceBundle, key);
    }

    private static void readIntFromUser(ResourceBundle resourceBundle, String key) throws UnsupportedEncodingException {
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8), true);
        out.println(resourceBundle.getString(key));
        System.out.print(resourceBundle.getString(key));
    }
}