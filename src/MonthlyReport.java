import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Класс, предоставляющий месячный отчёт, содержащий данные о доходах и расходах в рамках одного календарного месяца
 */
public class MonthlyReport {

HashMap<String, ArrayList<String>> itemName = new HashMap<>();
HashMap<String, ArrayList<Boolean>> isExpense = new HashMap<>();
HashMap<String, ArrayList<Integer>> quantity = new HashMap<>();
HashMap<String, ArrayList<Integer>> sumOfOne = new HashMap<>();
    public MonthlyReport() {
        itemName.put("item_name", new ArrayList<>());
        isExpense.put("is_expense", new ArrayList<>());
        quantity.put("quantity", new ArrayList<>());
        sumOfOne.put("sum_of_one", new ArrayList<>());
    }
}