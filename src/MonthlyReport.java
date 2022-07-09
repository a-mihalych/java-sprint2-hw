import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {

    public final int YEAR;
    // Integer: месяц (1 - 12)
    // ArrayList: состоит из массивов на 4 строки (String[4]) - это одна запись (строка) из файла
    // [0] название товара, [1] true/false (трата/доход), [2] количество товара, [3] стоимость единицы товара
    private HashMap<Integer, ArrayList<String[]>> monthlyReport = new HashMap<>();

    public MonthlyReport(int year) {
        this.YEAR = year;
    }

    // проверка наличия ключа в HashMap
    public boolean isKey(int key) {
        return monthlyReport.containsKey(key);
    }

    // добавляет в HashMap данные из месячного файла
    public void setReportMonth(int month, String fileContents) {
        String[] reportAll = fileContents.split("\n");
        String[] reportLine;
        ArrayList<String[]> reportLines = new ArrayList<>();
        for (int i = 1; i < reportAll.length; i++) {
            reportLine = reportAll[i].split(",");
            reportLines.add(reportLine);
        }
        monthlyReport.put(month, reportLines);
    }

    // суммирует траты (isExpense - "true") или прибыль (isExpense - "false") за месяц (month)
    public int sum(int month, String isExpense) {
        int result = 0;
        ArrayList<String[]> reportLines = monthlyReport.get(month);
        for (String[] reportLine : reportLines) {
            if (reportLine[1].equals(isExpense)) {
                result += (Integer.parseInt(reportLine[2]) * Integer.parseInt(reportLine[3]));
            }
        }
        return result;
    }

    // месячный отчёт (для меню 4)
    public String monthlyReports() {
        String result = "";
        ArrayList<String[]> reportLines;
        String itemNameExpense = "";
        String itemNameProfit = "";
        int expense;
        int profit;
        int sum;
        for (Integer month : monthlyReport.keySet()) {
            reportLines = monthlyReport.get(month);
            expense = 0;
            profit = 0;
            for (String[] reportLine : reportLines) {
                sum = Integer.parseInt(reportLine[2]) * Integer.parseInt(reportLine[3]);
                if (reportLine[1].equals("TRUE")) {
                    if (sum > expense) {
                        expense = sum;
                        itemNameExpense = reportLine[0];
                    }
                } else {
                    if (sum > profit) {
                        profit = sum;
                        itemNameProfit = reportLine[0];
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
