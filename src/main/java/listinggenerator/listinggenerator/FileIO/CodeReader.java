package listinggenerator.listinggenerator.FileIO;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class CodeReader {
    static String readCodeFromFile(File file) {
        StringBuilder content = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            for (String line : lines) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + file.getName());
            return null;
        }
        return content.toString();
    }
}