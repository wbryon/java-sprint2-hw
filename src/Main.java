import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        MonthlyReport report = new MonthlyReport();
//        for (String line : report.monthlyReport.keySet()) {
//            System.out.println(line + ": " + report.monthlyReport.get(line));
//        }
        int command;
//        Scanner scanner = new Scanner(System.in);
//        while (true) {
//            printMenu();
//            command = scanner.nextInt();
//            if (command == 1) {
//
//            } else if (command == 2) {
//
//            } else if (command == 3) {
//
//            } else if (command == 4) {
//
//            } else if (command == 5) {
//
//            } else if (command == 0) {
//                break;
//            } else {
//                System.out.println("Такой команды в меню нет");
//            };
//        }
    }

    static void printMenu() {
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выйти из программы");
    }
}