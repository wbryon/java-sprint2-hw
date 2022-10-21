import java.util.*;

/**
 * Класс, предоставляющий месячный отчёт, содержащий данные о доходах и расходах в рамках одного календарного месяца
 */
public class MonthlyReport {
    private final YearlyReport yearlyReport = new YearlyReport();
    private final List<String> months = List.of("Январь", "Февраль", "Март");
    private final Map<Integer, ReadMonthData> monthlyReport = new HashMap<>();
    public MonthlyReport() {
        ReadMonthData januaryReport = new ReadMonthData("resources/m.202101.csv");
        monthlyReport.put(0, januaryReport);
        ReadMonthData februaryReport = new ReadMonthData("resources/m.202102.csv");
        monthlyReport.put(1, februaryReport);
        ReadMonthData marchReport = new ReadMonthData("resources/m.202103.csv");
        monthlyReport.put(2, marchReport);
    }

    /**
     * метод проверяющий, что информация по месяцу в годовом отчёте не противоречит информации в месячном отчёте
     */
    public void reconciliationOfReports() {
        int errorCount = 0;
        for (Integer key : monthlyReport.keySet()) {
            int monthProfit = 0;
            int monthExpense = 0;
            for (int i = 0; i < monthlyReport.get(key).itemName.size(); i++) {
                if (monthlyReport.get(key).isExpense.get(i).equals(false)) {
                    monthProfit += monthlyReport.get(key).quantity.get(i) * monthlyReport.get(key).sumOfOne.get(i);
                } else if (monthlyReport.get(key).isExpense.get(i).equals(true)) {
                    monthExpense += monthlyReport.get(key).quantity.get(i) * monthlyReport.get(key).sumOfOne.get(i);
                }
            }
            if (monthProfit != yearlyReport.monthProfit.get(key)) {
                System.out.println(months.get(key) + ": обнаружено несоответствие в доходах!");
                errorCount++;
            } else if (monthExpense != yearlyReport.monthExpense.get(key)) {
                System.out.println(months.get(key) + ": обнаружено несоответствие в расходах!");
                errorCount++;
            }
        }
        if (errorCount == 0) {
            System.out.println("Сверка отчётов успешно завершена\n");
        }
    }

    /**
     * метод для печати информации о месяце
     */
    public void printMonthlyReport() {
        for (Integer key : monthlyReport.keySet()) {
            System.out.println(months.get(key) + "\n" + getMostProfitableProduct(monthlyReport.get(key)));
            System.out.println(getBiggestExpense(monthlyReport.get(key)) + "\n");
        }
    }

    /**
     * метод, выводящий информацию о самом прибыльном товаре
     */
    private String getMostProfitableProduct(ReadMonthData data) {
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

    /**
     * метод, выводящий самую большую трату за месяц
     */
    private String getBiggestExpense(ReadMonthData data) {
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

    /**
     * Статический вложенный класс, хранящий всю информацию за месяц
     */
    private static class ReadMonthData {
        public ReadMonthData(String source) {
            try {
                parseLinesFromFile(Auxiliary.util(source));
            } catch (NullPointerException exception) {
                System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            }
        }
        private final List<String> itemName = new ArrayList<>();
        private final List<Boolean> isExpense = new ArrayList<>();
        private final List<Integer> quantity = new ArrayList<>();
        private final List<Integer> sumOfOne = new ArrayList<>();

        /**
         * метод, получающий значения из строк месячного отчёта
         */
        private void parseLinesFromFile(List<String> file) {

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