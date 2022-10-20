import java.io.IOException;
import java.nio.file.*;
import java.util.*;

/**
 * Класс, предоставляющий вспомогательные методы для считывания файлов
 */
public class Auxiliary {
    public Auxiliary(String path) {
        readFileContentsOrNull(path);
    }

    public List<String> readFileContentsOrNull(String source) { // Метод, считывающий содержимое отчёта
        List<String> currentReport = new ArrayList<>();
        try {
            String[] lines = Files.readString(Path.of(source)).split("\r?\n");
            currentReport.addAll(Arrays.asList(lines));
            return currentReport;
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }
}
