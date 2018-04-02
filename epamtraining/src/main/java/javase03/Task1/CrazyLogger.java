package javase03.Task1;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class CrazyLogger {
    private StringBuilder logger = new StringBuilder();
    private LocalDateTime localDateTimeTime = LocalDateTime.now();
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MMM-YYYY : hh-mm - ", Locale.ENGLISH);
    private String filePath = "epamtraining/src/main/java/javase03/Task1/log.txt";
    public void log(String str) {
        logger.append(localDateTimeTime.format(dateTimeFormatter)).append(str).append(System.getProperty("line.separator"));
    }

    public void printLog() {
        try (FileOutputStream outputStream = new FileOutputStream(filePath, true);
             OutputStreamWriter writer = new OutputStreamWriter(outputStream)) {
            writer.write(logger.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String findInfoInLog(String searchedString) {
        try (Scanner scanner = new Scanner(new File(filePath))) {
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

    public String findInfoInLog(LocalDateTime dateTime) {
        try (Scanner scanner = new Scanner(new File(filePath))) {
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
