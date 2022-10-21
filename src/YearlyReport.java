import java.util.*;

/**
 * Класс, предоставляющий годовой отчёт, содержащий ровно по 2 записи на каждый из 12 месяцев — общий доход и расход за этот месяц.
 */
public class YearlyReport {

    private final List<String> months = List.of("Январь","Февраль","Март");
    List<Integer> monthProfit = new ArrayList<>();
    List<Integer> monthExpense = new ArrayList<>();
    private final String path = "resources/y.2021.csv";

    public YearlyReport() {
        try {
            parseLinesFromFile(Auxiliary.util(path));
        } catch (NullPointerException exception) {
            System.out.println("Невозможно прочитать файл с годовым отчётом. Возможно, файл не находится в нужной директории.");
        }
    }

    /**
     * метод, получающий значения из строк годового отчёта
     */
    private void parseLinesFromFile(List<String> file) {
        for (String line : file.subList(1, file.size())) {
            String[] lines = line.split(",");
            label:
            for (int i = 0; i < lines.length; i++) {
                switch (lines[2]) {
                    case "false":
                        monthProfit.add(Integer.parseInt(lines[1]));
                        break label;
                    case "true":
                        monthExpense.add(Integer.parseInt(lines[1]));
                        break label;
                }
            }
        }
    }

    /**
     * метод для печати информации о годовом отчёте
     */
    public void printYearlyReport() {
        System.out.println("Прибыль за " + path.replaceAll("\\D+","") + " год по месяцам \n");
        for (int i = 0; i < months.size(); i++) {
            System.out.println(months.get(i) + ": " + (monthProfit.get(i) - monthExpense.get(i)));
        }
        System.out.println();
    }
}