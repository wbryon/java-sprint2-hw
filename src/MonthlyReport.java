import java.io.IOException;
import java.nio.file.*;
import java.util.*;

/**
 * Класс, предоставляющий месячный отчёт, содержащий данные о доходах и расходах в рамках одного календарного месяца
 */
public class MonthlyReport {
    ReadMonthData januaryReport = new ReadMonthData("resources/m.202101.csv");
    ReadMonthData februaryReport = new ReadMonthData("resources/m.202102.csv");
    ReadMonthData marchReport = new ReadMonthData("resources/m.202103.csv");
    Map<String, ReadMonthData> monthlyReport = new HashMap<>();
    public MonthlyReport() {
        monthlyReport.put("Январь", januaryReport);
        monthlyReport.put("Февраль", februaryReport);
        monthlyReport.put("Март", marchReport);
    }

    public void printMonthReports() {
        for (String key : monthlyReport.keySet()) {
            System.out.println(key + "\n" + getMostProfitableProduct(monthlyReport.get(key)) + "\n");
        }
    }
    public String getMostProfitableProduct(ReadMonthData data) {
        int mostProfitableProduct = 0;
        String curItemName = "";
        String bestSeller = "";
        for (int i = 0; i < data.itemName.size(); i++) {
            int curProfitableProduct = data.quantity.get(i) * data.sumOfOne.get(i);
            if (data.isExpense.get(i).equals(false) && curProfitableProduct > mostProfitableProduct) {
                mostProfitableProduct = curProfitableProduct;
                curItemName = data.itemName.get(i);
            }
        }
        bestSeller = curItemName + " на сумму " + String.valueOf(mostProfitableProduct) + " рублей";
        return bestSeller;
    }
    public void getBiggestWaste() {}

    private class ReadMonthData {
        public ReadMonthData(String source) {
            parseLinesFromFile(readFileContentsOrNull(source));
        }
        private List<String> itemName = new ArrayList<>();
        private List<Boolean> isExpense = new ArrayList<>();
        private List<Integer> quantity = new ArrayList<>();
        private List<Integer> sumOfOne = new ArrayList<>();
        private void parseLinesFromFile(List<String> file) {

            for (String line : file.subList(1, file.size())) {
                String[] lines = line.split(",");
                itemName.add(lines[0]);
                isExpense.add(Boolean.parseBoolean(lines[1]));
                quantity.add(Integer.parseInt(lines[2]));
                sumOfOne.add(Integer.parseInt(lines[3]));
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

//        private ArrayList<String> getPathToMonthlyReports() {
//            ArrayList<String> pathToMonthlyReports = new ArrayList<>();
//            Path path = Path.of("resources");
//            try (DirectoryStream<Path> files = Files.newDirectoryStream(path)) {
//                for (Path fileName : files) {
//                    if (fileName.toString().startsWith("resources/m")) {
//                        pathToMonthlyReports.add(fileName.toString());
//                    }
//                }
//                Collections.sort(pathToMonthlyReports);
//                return pathToMonthlyReports;
//            } catch (IOException e) {
//                System.out.println("Что-то пошло не так");
//                return null;
//            }
//        }

    }
}