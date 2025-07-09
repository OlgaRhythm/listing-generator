package listinggenerator.listinggenerator.services;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocGenerator {
    // Список форматов по умолчанию (если пользователь ничего не выбрал)
    private static final LinkedList<String> defaultFileFormats = new LinkedList<String>(){{
        add(".java");
        add(".cpp");
        add(".c");
        add(".hs");
        add(".js");
        add(".py");
        add(".php");
        add(".html");
        add(".css");
        add(".sql");
        //add(".xml");
        add(".json");
        add(".go");
        add(".swift");
        add(".csharp");
        add(".cs");
        add(".rb");
        add(".ts");
        add(".kt");
        add(".php");
        //add(".md");
    }};
    public static String generate(String directoryValue,
                                  String directoryToSaveValue,
                                  String docNameValue,
                                  Set<String> formats,
                                  String titleFont,
                                  String codeFont) {

        File directory = new File(directoryValue);
        if (directory.exists() && directory.isDirectory()) {
            if (checkDocName(docNameValue)) {
                // Если пользователь не выбрал ни одного формата, используем форматы по умолчанию
                Set<String> formatsToUse = formats.isEmpty() ?
                        new HashSet<>(defaultFileFormats) :
                        formats;
                processFilesInDirectory(
                        directory,
                        directoryToSaveValue,
                        docNameValue,
                        formatsToUse,
                        titleFont,
                        codeFont
                );
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

    private static void processFilesInDirectory(File directory,
                                                String directoryToSaveValue,
                                                String docNameValue,
                                                Set<String> formats,
                                                String titleFont,
                                                String codeFont) {
        // Создание нового документа
        XWPFDocument document = new XWPFDocument();

        processFilesRecursive(directory, document, formats, titleFont, codeFont);

        // Сохранение документа в файл
        TextWriter.writeToDocument(document, directoryToSaveValue, docNameValue);
    }

    private static void processFilesRecursive(File directory,
                                              XWPFDocument document,
                                              Set<String> formats,
                                              String titleFont,
                                              String codeFont) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    // Если текущий файл является директорией, рекурсивно обходим её содержимое
                    processFilesRecursive(file, document, formats, titleFont, codeFont);
                } else if (file.isFile() && checkFileFormat(file, formats)) {
                    // получение содержимого файла
                    String content = CodeReader.readCodeFromFile(file);
                    if (content != null) {
                        TextWriter.addContentToDocument(
                                document,
                                file.getName(),
                                content,
                                titleFont,
                                codeFont);
                    }
                }
            }
        }
    }

    private static boolean checkFileFormat(File file, Set<String> formats) {
        String fileName = file.getName().toLowerCase();
        for (String format : formats) {
            if (file.getName().endsWith(format)) {
                return true;
            }
        }
        return false;
    }

}
