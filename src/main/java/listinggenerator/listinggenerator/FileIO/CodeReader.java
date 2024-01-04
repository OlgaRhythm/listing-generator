package listinggenerator.listinggenerator.FileIO;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class CodeReader {
    static String readCodeFromFile(File file) {
        try {
            List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            return String.join("\n", lines);
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + file.getName());
            return null;
        }
    }
}