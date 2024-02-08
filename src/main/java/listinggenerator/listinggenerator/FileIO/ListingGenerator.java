package listinggenerator.listinggenerator.FileIO;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListingGenerator {
    // список доступных расширений файлов
    private static LinkedList<String> fileFormat = new LinkedList<String>(){{
        add(".java");
        add(".cpp");
        add(".hs");
    }};
    public static String generate(String directoryValue, String docNameValue) {
        File directory = new File(directoryValue);
        if (directory.exists() && directory.isDirectory()) {
            if (checkDocName(docNameValue)) {
                processFilesInDirectory(directory, docNameValue);
                return "Успешно! ^_^";
            } else {
                return "Имя файла может содержать только латиницу, цифры, точки, подчеркивания и дефисы";
            }
        } else {
            return "Директория не найдена или не является директорией";
        }
    }

    private static boolean checkDocName(String docNameValue) {
        Pattern pattern = Pattern.compile("^[-_.A-Za-z0-9]+");
        Matcher matcher = pattern.matcher(docNameValue);
        return matcher.find();
    }

    private static void processFilesInDirectory(File directory, String docNameValue) {
        // Создание нового документа
        XWPFDocument document = new XWPFDocument();

        processFilesRecursive(directory, document);

        // Сохранение документа в файл
        TextWriter.writeToDocument(document, docNameValue);
    }

    private static void processFilesRecursive(File directory, XWPFDocument document) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    // Если текущий файл является директорией, рекурсивно обходим её содержимое
                    processFilesRecursive(file, document);
                } else if (file.isFile() && checkFileFormat(file)) {
                    // получение содержимого файла
                    String content = CodeReader.readCodeFromFile(file);
                    if (content != null) {
                        // System.out.print(content);
                        TextWriter.addContentToDocument(document, file.getName(), content);
                    }
                }
            }
        }
    }

    private static boolean checkFileFormat(File file) {
        for (String format : fileFormat) {
            if (file.getName().endsWith(format)) {
                return true;
            }
        }
        return false;
    }

}
