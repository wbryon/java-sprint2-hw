import java.util.Scanner;

public class Main {
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

    static void printMenu() {   // метод для ввода команд из консоли
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выйти из программы");
    }
}