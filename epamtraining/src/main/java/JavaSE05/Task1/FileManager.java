package JavaSE05.Task1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {
    private String directoryPath = System.getProperty("user.home");

    /**
     * Sets the path to desirable directory
     *
     * @param path A pathname string
     */
    private boolean cd(String path) {
        Boolean result = false;
        Path pathToDirectory = Paths.get(path);
        if (Files.exists(pathToDirectory)) {
            directoryPath = path;
            result = true;
            System.out.println(directoryPath);
        } else
            System.out.println("System could not find path specified");
        return result;
    }

    /**
     * Lists all files in current directory
     */
    private void ls() {
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

    private boolean createTextFile(String fileName) {
        Boolean result = false;
        String fullPathToFile = directoryPath + "\\" + fileName + ".txt";
        File newFile = new File(fullPathToFile);
        try {
            newFile.createNewFile();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
