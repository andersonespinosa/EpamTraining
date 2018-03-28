package JavaSE03.Task1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class CrazyLogger {
    private StringBuilder logger = new StringBuilder();
    private LocalDateTime localDateTimeTime = LocalDateTime.now();
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-mm-YYYY : hh-mm - ");

    private void log(String str) {
        logger.append(localDateTimeTime.format(dateTimeFormatter)).append(str).append(System.getProperty("line.separator"));
    }

    private void printLog() {
        try (FileOutputStream outputStream = new FileOutputStream("epamtraining/src/main/java/JavaSE03/Task1/log.txt");
             OutputStreamWriter writer = new OutputStreamWriter(outputStream)) {
            writer.write(logger.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CrazyLogger crazyLogger = new CrazyLogger();
        crazyLogger.log("Log this info");
        crazyLogger.log("Log this info too");
        crazyLogger.printLog();
    }
}
