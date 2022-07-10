import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {

    // Integer: месяц (1 - 12)
    // ArrayList: состоит из объектов типа OperationOfMonth
    private HashMap<Integer, ArrayList<OperationOfMonth>> monthlyReport = new HashMap<>();

    // пустой ли HashMap
    public boolean isEmptyHashMap() {
        return monthlyReport.isEmpty();
    }

    // проверка наличия ключа в HashMap
    public boolean isKey(int key) {
        return monthlyReport.containsKey(key);
    }

    // добавляет в HashMap monthlyReport данные из месячного файла
    public void setReportMonth(int month, String fileContents) {
        String[] reportAll = fileContents.split("\n");
        String[] reportLine;
        boolean isExpense;
        int quantity;
        int sumOfOne;
        ArrayList<OperationOfMonth> reportLines = new ArrayList<>();
        for (int i = 1; i < reportAll.length; i++) {
            reportLine = reportAll[i].split(",");
            isExpense = Boolean.parseBoolean(reportLine[1]);
            quantity = Integer.parseInt(reportLine[2]);
            sumOfOne = Integer.parseInt(reportLine[3]);
            reportLines.add(new OperationOfMonth(reportLine[0], isExpense, quantity, sumOfOne));
        }
        monthlyReport.put(month, reportLines);
    }

    // суммирует траты (isExpense - "true") или прибыль (isExpense - "false") за месяц (month)
    public int sum(int month, boolean isExpense) {
        int result = 0;
        ArrayList<OperationOfMonth> reportLines = monthlyReport.get(month);
        for (OperationOfMonth reportLine : reportLines) {
            if (reportLine.isExpense() == isExpense) {
                result += reportLine.getQuantity() * reportLine.getSumOfOne();
            }
        }
        return result;
    }

    // месячный отчёт (для меню 4)
    public String monthlyReports() {
        String result = "";
        ArrayList<OperationOfMonth> reportLines;
        String itemNameExpense = "";
        String itemNameProfit = "";
        int expense;
        int profit;
        int sumMax;
        for (Integer month : monthlyReport.keySet()) {
            reportLines = monthlyReport.get(month);
            expense = 0;
            profit = 0;
            for (OperationOfMonth reportLine : reportLines) {
                sumMax = reportLine.getQuantity() * reportLine.getSumOfOne();
                if (reportLine.isExpense()) {
                    if (sumMax > expense) {
                        expense = sumMax;
                        itemNameExpense = reportLine.getItemName();
                    }
                } else {
                    if (sumMax > profit) {
                        profit = sumMax;
                        itemNameProfit = reportLine.getItemName();
                    }
                }
            }
            result += ("\n- Отчёт за " + YearlyReport.MONTHS[month - 1] + " месяц -\n\n");
            result += ("Самый прибыльный товар - " + itemNameProfit + ", доход: " + profit + "\n");
            result += ("Самая большая трата - " + itemNameExpense + ", сумма: " + expense + "\n");
        }
        return result;
    }
}
