import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Класс, предоставляющий месячный отчёт, содержащий данные о доходах и расходах в рамках одного календарного месяца
 */
public class MonthlyReport {
    HashMap<String, List<String>> monthlyReport;
    public MonthlyReport() {
        monthlyReport = new HashMap<>();
        monthlyReport.put("Январь", readFileContentsOrNull(getPathToMonthlyReports()).get(0).subList(1,
                readFileContentsOrNull(getPathToMonthlyReports()).get(0).size()));
        monthlyReport.put("Февраль", readFileContentsOrNull(getPathToMonthlyReports()).get(1).subList(1,
                readFileContentsOrNull(getPathToMonthlyReports()).get(1).size()));
        monthlyReport.put("Март", readFileContentsOrNull(getPathToMonthlyReports()).get(2).subList(1,
                readFileContentsOrNull(getPathToMonthlyReports()).get(2).size()));
    }
    public void getMostProfitableProduct(HashMap<String, List<String>> monthlyReport) {

    }
    public void getBiggestWaste() {}
    private List<List<String>> readFileContentsOrNull(ArrayList<String> source) { // Метод, считывающий содержимое месячного отчёта
        ArrayList<String> fileReader = new ArrayList<>();
        List<List<String>> monthReports = new ArrayList<>();
        try {
            for (int i = 0; i < source.size(); i++) {
                fileReader.add(Files.readString(Path.of(source.get(i))));
            }
            for (int i = 0; i < fileReader.size(); i++) {
                String[] lines = fileReader.get(i).split(System.lineSeparator());
                monthReports.add(Arrays.asList(fileReader.get(i).split(System.lineSeparator())));
            }
            return monthReports;
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }
    class ReportPerMonth {
        HashMap<String, ArrayList<String>> itemName;
        HashMap<String, ArrayList<Boolean>> isExpense;
        HashMap<String, ArrayList<Integer>> quantity;
        HashMap<String, ArrayList<Integer>> sumOfOne;
        public ReportPerMonth() {
            itemName = new HashMap<>();
            isExpense =new HashMap<>();
            quantity = new HashMap<>();
            sumOfOne = new HashMap<>();

            itemName.put("item_name", new ArrayList<>());
            isExpense.put("is_expense", new ArrayList<>());
            quantity.put("quantity", new ArrayList<>());
            sumOfOne.put("sum_of_one", new ArrayList<>());
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