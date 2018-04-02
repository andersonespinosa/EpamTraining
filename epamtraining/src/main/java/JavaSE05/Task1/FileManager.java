package JavaSE05.Task1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {
    private String directoryPath = System.getProperty("user.home");

    public void changeDirectory(String path) {
        Boolean result = false;
        Path pathToDirectory = Paths.get(path);
        if (Files.exists(pathToDirectory)) {
            directoryPath = path;
            result = true;
            System.out.println(directoryPath);
        } else
            System.out.println("System could not find path specified");
    }


    public void showAllFilesAndDirectories() {
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

    public void createTextFile(String fileName) {
        Boolean result = false;
        String fullPathToFile = directoryPath + "\\" + fileName + ".txt";
        File newFile = new File(fullPathToFile);
        try {
            newFile.createNewFile();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteFile(String fileName) {
        Boolean result = false;
        String fulPathToFile = directoryPath + "\\" + fileName + ".txt";
        File fileToDelete = new File(fulPathToFile);
        if (fileToDelete.exists()) {
            fileToDelete.delete();
            result = true;
        } else {
            throw new FileNotFoundException();
        }
    }

    public void editFile(String fileName, String stringToWrite) {
        String fulPathToFile = directoryPath + "\\" + fileName + ".txt";
        try (FileOutputStream outputStream = new FileOutputStream(fulPathToFile, true);
             OutputStreamWriter writer = new OutputStreamWriter(outputStream)) {
            writer.write(stringToWrite);
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
