import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class ReadData {
    public List<String> itemName = new ArrayList<>();
    public List<Boolean> isExpense = new ArrayList<>();
    public List<Integer> quantity = new ArrayList<>();
    public List<Integer> sumOfOne = new ArrayList<>();
    public void parseLinesFromFile(List<List<String>> file) {

        for (int i = 0; i < file.size(); i++) {
            for (String line : file.get(i).subList(1, file.get(i).size())) {
                String[] lines = line.split(",");
                itemName.add(lines[0]);
                isExpense.add(Boolean.parseBoolean(lines[1]));
                quantity.add(Integer.parseInt(lines[2]));
                sumOfOne.add(Integer.parseInt(lines[3]));
            }
        }
    }

    private List<String> readFileContentsOrNull(String source) { // Метод, считывающий содержимое месячного отчёта
        List<String> currentMonthReport = new ArrayList<>();
        try {
            String[] lines = Files.readString(Path.of(source)).split("\r?\n");
            for (String line : lines) {
                currentMonthReport.add(line);
            }
            return currentMonthReport;
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }

    private ArrayList<String> getPathToMonthlyReports() {
        ArrayList<String> pathToMonthlyReports = new ArrayList<>();
        Path path = Path.of("resources");
        try (DirectoryStream<Path> files = Files.newDirectoryStream(path)) {
            for (Path fileName : files) {
                if (fileName.toString().startsWith("resources/m")) {
                    pathToMonthlyReports.add(fileName.toString());
                }
            }
            Collections.sort(pathToMonthlyReports);
            return pathToMonthlyReports;
        } catch (IOException e) {
            System.out.println("Что-то пошло не так");
            return null;
        }
    }
}
