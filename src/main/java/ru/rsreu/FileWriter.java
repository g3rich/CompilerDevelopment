package ru.rsreu;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileWriter {

    public void writeFile(String fileName, String content) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName))) {
            writer.write(content);
            writer.newLine();
        }
    }

    public static void writeStringToFile(String fileName, String outputString) throws IOException {
        try {
            Path path = new File(fileName).toPath();
            // Запись строк в файл с новой строки для каждой строки массива
            Files.write(path, outputString.getBytes());
        } catch (IOException ex) {
            throw new IOException("Файл " + fileName + " не может быть открыт или записан.");
        }
    }
}
