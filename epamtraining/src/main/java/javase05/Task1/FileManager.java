package javase05.Task1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {
    private String directoryPath = System.getProperty("user.home");

    public void changeDirectory(String path) {
        if (path != null) {
            Path pathToDirectory = Paths.get(path);
            if (Files.exists(pathToDirectory)) {
                directoryPath = path;
                System.out.println(directoryPath);
            } else {
                throw new IllegalArgumentException("No such directory");
            }
        } else {
            throw new IllegalArgumentException("Wrong path");
        }
    }

    public void showAllFilesAndDirectoriesInCurrentDirectory() {
        File[] filesInDirectory = new File(directoryPath).listFiles();
        if (filesInDirectory != null) {
            for (File aFilesInDirectory : filesInDirectory) {
                if (aFilesInDirectory.isFile()) {
                    System.out.println("File " + aFilesInDirectory.getName());
                } else if (aFilesInDirectory.isDirectory()) {
                    System.out.println("DIR " + aFilesInDirectory.getName());
                }
            }
        }
    }

    public void createTextFileInCurrentDirectory(String fileName) {
        String fullPathToFile = directoryPath + File.separator + fileName + ".txt";
        File newFile = new File(fullPathToFile);
        try {
            newFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("File can't be created", e);
        }
    }

    public void deleteFile(String fileName) throws FileNotFoundException {
        String fulPathToFile = directoryPath + File.separator + fileName + ".txt";
        File fileToDelete = new File(fulPathToFile);
        if (fileToDelete.exists()) {
            fileToDelete.delete();
        } else {
            throw new FileNotFoundException();
        }
    }

    public void editFile(String fileName, String stringToWrite) {
        String fulPathToFile = directoryPath + File.separator + fileName + ".txt";
        try (FileOutputStream outputStream = new FileOutputStream(fulPathToFile, true);
             OutputStreamWriter writer = new OutputStreamWriter(outputStream)) {
            writer.write(stringToWrite);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
