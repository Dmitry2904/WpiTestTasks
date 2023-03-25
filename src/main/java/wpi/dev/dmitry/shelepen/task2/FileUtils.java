package wpi.dev.dmitry.shelepen.task2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileUtils {

    public List<String> readInputDataFromFile(String fileName) {
        Path filePath = Paths.get(fileName);
        List<String> lines = null;
        try {
            lines = Files.readAllLines(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

    public void writeDataToTargetFile(String fileName, String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
