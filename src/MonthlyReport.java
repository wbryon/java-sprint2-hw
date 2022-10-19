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

    public void printMonthlyReport() {   // метод для печати информации о месяце
        for (String key : monthlyReport.keySet()) {
            System.out.println(key + "\n" + getMostProfitableProduct(monthlyReport.get(key)));
            System.out.println(getBiggestExpense(monthlyReport.get(key)) + "\n");
        }
    }
    private String getMostProfitableProduct(ReadMonthData data) {    // метод, выводящий информацию о самом прибыльном товаре
        int mostProfitableProduct = 0;
        String itemName = "";
        for (int i = 0; i < data.itemName.size(); i++) {
            int curProfitableProduct = data.quantity.get(i) * data.sumOfOne.get(i);
            if (data.isExpense.get(i).equals(false) && curProfitableProduct > mostProfitableProduct) {
                mostProfitableProduct = curProfitableProduct;
                itemName = data.itemName.get(i);
            }
        }
        return itemName + " на сумму " + mostProfitableProduct + " рублей";
    }
    private String getBiggestExpense(ReadMonthData data) {   // метод, выводящий самую большую трату за месяц
        int biggestExpense = 0;
        String itemName = "";
        for (int i = 0; i < data.itemName.size(); i++) {
            int curExpensiveProduct = data.quantity.get(i) * data.sumOfOne.get(i);
            if (data.isExpense.get(i).equals(true) && curExpensiveProduct > biggestExpense) {
                biggestExpense = curExpensiveProduct;
                itemName = data.itemName.get(i);
            }
        }
        return itemName + " на сумму " + biggestExpense + " рублей";
    }

    private static class ReadMonthData {    // внутренний класс, хранящий всю информацию за месяц
        public ReadMonthData(String source) {
            Auxiliary getPath = new Auxiliary(source);
            parseLinesFromFile(getPath.readFileContentsOrNull(source));
        }
        private final List<String> itemName = new ArrayList<>();
        private final List<Boolean> isExpense = new ArrayList<>();
        private final List<Integer> quantity = new ArrayList<>();
        private final List<Integer> sumOfOne = new ArrayList<>();
        private void parseLinesFromFile(List<String> file) {    // метод, обрабатывающий значения из месячного отчёта

            for (String line : file.subList(1, file.size())) {
                String[] lines = line.split(",");
                itemName.add(lines[0]);
                isExpense.add(Boolean.parseBoolean(lines[1]));
                quantity.add(Integer.parseInt(lines[2]));
                sumOfOne.add(Integer.parseInt(lines[3]));
            }
        }
    }
}