import java.util.ArrayList;

/**
 * Класс, предоставляющий годовой отчёт, содержащий ровно по 2 записи на каждый из 12 месяцев — общий доход и расход за этот месяц.
 */
public class YearlyReport {
    private int month; // месяц (целое число по ТЗ)
    private  double amount;

    public YearlyReport(int month, double amount, boolean isExpense) {
        this.month = month;
        this.amount = amount;
        this.isExpense = isExpense;
    }

    private boolean isExpense; // является ли запись тратой (TRUE) или доходом (FALSE);

    public void annualReport(int year) {
        System.out.println("Запрошен отчёт за " + year + " год");
        System.out.println("Прибыль по каждому месяцу. Прибыль — это разность доходов и расходов");
        System.out.println("Средний расход за все месяцы в году");
        System.out.println("Средний доход за все месяцы в году");
    }


}