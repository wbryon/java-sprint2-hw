import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class ReadData {
    public ReadData(String path) {
        readFileContentsOrNull(getPathToMonthlyReports(Path.of(path)));
    }
    private List<List<String>> readFileContentsOrNull(ArrayList<String> source) { // Метод, считывающий содержимое месячного отчёта
        List<List<String>> monthReports = new ArrayList<>();
        try {
            for (String path : source) {
                monthReports.add(Arrays.asList(Files.readString(Path.of(path)).split("\r?\n")));
            }
            return monthReports;
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }
    private ArrayList<String> getPathToMonthlyReports(Path pathToFile) {
        ArrayList<String> pathToMonthlyReports = new ArrayList<>();
        Path path = Path.of(String.valueOf(pathToFile));
        try (DirectoryStream<Path> files = Files.newDirectoryStream(path)) {
            for (Path fileName : files) {
                if (fileName.toString().startsWith("resources/m")) {
                    pathToMonthlyReports.add(fileName.toString());
                }
            }
            Collections.sort(pathToMonthlyReports);
            return pathToMonthlyReports;
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }
}
