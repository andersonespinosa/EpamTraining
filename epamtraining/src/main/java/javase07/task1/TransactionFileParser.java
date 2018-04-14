package javase07.task1;

import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class TransactionFileParser {

    public List<String> readTransactionFile(String filePath) {
        List<String> transactions = new ArrayList<>();
        Path path = Paths.get(filePath);
        try (InputStream in = Files.newInputStream(path);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {

            String line;
            while ((line = reader.readLine()) != null) {
                transactions.add(line);
            }
        } catch (IOException e) {
            log.error("Cannot read transaction file", e);
            throw new RuntimeException(e);
        }
        return transactions;
    }
}
