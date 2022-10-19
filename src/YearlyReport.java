import java.util.*;

/**
 * Класс, предоставляющий годовой отчёт, содержащий ровно по 2 записи на каждый из 12 месяцев — общий доход и расход за этот месяц.
 */

public class YearlyReport {

    List<String> months = List.of("Январь","Февраль","Март");
    List<Integer> monthProfit = new ArrayList<>();
    List<Integer> monthExpense = new ArrayList<>();
    private final String path = "resources/y.2021.csv";
    Auxiliary getPath = new Auxiliary(path);

    public YearlyReport() {
        parseLinesFromFile(getPath.readFileContentsOrNull(path));
    }
    private void parseLinesFromFile(List<String> file) {    // метод, обрабатывающий значения из годового отчёта
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
    public void printYearlyReport() {   // метод для печати информации о годе
        System.out.println("Прибыль за " + path.replaceAll("\\D+","") + " год по месяцам \n");
        for (int i = 0; i < months.size(); i++) {
            System.out.println(months.get(i) + ": " + (monthProfit.get(i) - monthExpense.get(i)));
        }
        System.out.println();
    }
}