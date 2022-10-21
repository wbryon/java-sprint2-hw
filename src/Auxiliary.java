import java.io.IOException;
import java.nio.file.*;
import java.util.*;

/**
 * Класс, предоставляющий вспомогательные методы для считывания файлов
 */
public class Auxiliary {
    public Auxiliary(String path) {
        util(path);
    }

    /**
     * Метод, считывающий содержимое отчёта
     */
    public static List<String> util(String source) {
        List<String> currentReport = new ArrayList<>();
        try {
            String[] lines = Files.readString(Path.of(source)).split("\r?\n");
            currentReport.addAll(Arrays.asList(lines));
            return currentReport;
        } catch (IOException e) {
            return null;
        }
    }
}