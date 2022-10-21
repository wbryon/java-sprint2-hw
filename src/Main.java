import java.util.Scanner;

/**
 * Класс, исполняющий программу
 * @author  Хабибула Тамирбудаев
 */
public class Main {
    /**
     * точка входа в программу
     */
    public static void main(String[] args) {
        MonthlyReport monthlyReport = null;
        YearlyReport yearlyReport = null;
        int command;
        Scanner scanner = new Scanner(System.in);
        label:
        while (true) {
            printMenu();
            command = scanner.nextInt();
            switch (command) {
                case 1:
                    monthlyReport = new MonthlyReport();
                    break;
                case 2:
                    yearlyReport = new YearlyReport();
                    break;
                case 3:
                    try {
                        assert monthlyReport != null;
                        monthlyReport.reconciliationOfReports();
                    } catch (NullPointerException exception) {
                        System.out.println("Сначала считайте месячные отчёты!\n");
                    }
                    break;
                case 4:
                    try {
                        assert monthlyReport != null;
                        monthlyReport.printMonthlyReport();
                    } catch (NullPointerException exception) {
                        System.out.println("Сначала считайте месячные отчёты!\n");
                    }
                    break;
                case 5:
                    try {
                        assert yearlyReport != null;
                        yearlyReport.printYearlyReport();
                    } catch (NullPointerException exception) {
                        System.out.println("Сначала считайте годовой отчёт!\n");
                    }
                    break;
                case 0:
                    break label;
                default:
                    System.out.println("Такой команды в меню нет");
                    break;
            }
        }
    }

    /**
     * метод для ввода команд из консоли
     */
    static void printMenu() {
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выйти из программы");
    }
}