package JavaSE03.Task1;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class CrazyLogger {
    private StringBuilder logger = new StringBuilder();
    private LocalDateTime localDateTimeTime = LocalDateTime.now();
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MMM-YYYY : hh-mm - ", Locale.ENGLISH);

    private void log(String str) {
        logger.append(localDateTimeTime.format(dateTimeFormatter)).append(str).append(System.getProperty("line.separator"));
    }

    private void printLog() {
        try (FileOutputStream outputStream = new FileOutputStream("epamtraining/src/main/java/JavaSE03/Task1/log.txt", true);
             OutputStreamWriter writer = new OutputStreamWriter(outputStream)) {
            writer.write(logger.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String findInfoInLog(String searchedString) {
        try (Scanner scanner = new Scanner(new File("epamtraining/src/main/java/JavaSE03/Task1/log.txt"))) {
            while (scanner.hasNextLine()) {
                String logString = scanner.nextLine();
                if (logString.toLowerCase().contains(searchedString.toLowerCase())) {
                    return logString;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "No such info in log file";
    }

    private String findInfoInLog(LocalDateTime dateTime) {
        try (Scanner scanner = new Scanner(new File("epamtraining/src/main/java/JavaSE03/Task1/log.txt"))) {
            String formattedDate = dateTime.format(dateTimeFormatter);
            while (scanner.hasNextLine()) {
                String logString = scanner.nextLine();
                if (logString.toLowerCase().contains(formattedDate.toLowerCase())) {
                    return logString;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "No such date in log file";
    }
}
