import java.io.IOException;
import java.nio.file.*;
import java.util.*;

/**
 * Класс, предоставляющий месячный отчёт, содержащий данные о доходах и расходах в рамках одного календарного месяца
 */
public class MonthlyReport {
    ReadData readData = new ReadData();
    List<String> itemName = new ArrayList<>();
    List<Boolean> isExpense = new ArrayList<>();
    List<Integer> quantity = new ArrayList<>();
    List<Integer> sumOfOne = new ArrayList<>();
    List<List<String>> monthData = readFileContentsOrNull(getPathToMonthlyReports());
    Map<Integer, MonthlyReport> monthlyReport = new HashMap<>();
    public MonthlyReport() {

        for (int i = 0; i < monthData.size(); i++) {
            for (String line : monthData.get(i).subList(1, monthData.get(i).size())) {
                String[] lines = line.split(",");
                itemName.add(lines[0]);
                isExpense.add(Boolean.parseBoolean(lines[1]));
                quantity.add(Integer.parseInt(lines[2]));
                sumOfOne.add(Integer.parseInt(lines[3]));
            }
        }
    }
    public void getMostProfitableProduct(HashMap<String, List<String>> monthlyReport) {}
    public void getBiggestWaste() {}

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

    class MonthData {
        List<List<String>> itemName = new ArrayList<>();
        List<List<Boolean>> isExpense = new ArrayList<>();
        List<List<Integer>> quantity = new ArrayList<>();
        List<List<Integer>> sumOfOne = new ArrayList<>();
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