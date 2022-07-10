import java.util.HashMap;

public class YearlyReport {

    private int year;
    public static final String[] MONTHS = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
                                          "Июль", "Август", "Сентябрь", "Октябрь", "Нояюрь", "Декабрь"};
    // Integer: месяц (1 - 12)
    // ResultOfMonth: объект с полями: expense и profit
    private HashMap<Integer, ResultOfMonth> yearlyReport = new HashMap<>();

    // пустой ли HashMap
    public boolean isEmptyHashMap() {
        return yearlyReport.isEmpty();
    }

    // добавляет год, а в HashMap yearlyReport траты и прибыли
    public void setReportMonth(String fileContents, int year) {
        this.year = year;
        // рекомендованный в техническои задании System.lineSeparator(),
        // в windows, строку на части не разбивает, в split использую "\n"
        String[] reportAll = fileContents.split("\n");
        // два массива для одного месяца, траты и прибыль
        String[] reportLine1;
        String[] reportLine2;
        int expense;
        int profit;
        int month;
        for (int i = 1; i < reportAll.length; i += 2) {
            reportLine1 = reportAll[i].split(",");
            reportLine2 = reportAll[i + 1].split(",");
            month = Integer.parseInt(reportLine1[0]);
            if (Boolean.parseBoolean(reportLine1[2])) {
                expense = Integer.parseInt(reportLine1[1]);
                profit = Integer.parseInt(reportLine2[1]);
            } else {
                profit = Integer.parseInt(reportLine1[1]);
                expense = Integer.parseInt(reportLine2[1]);
            }
            yearlyReport.put(month, new ResultOfMonth(expense, profit));
        }
    }

    // сверка данных (для меню 3)
    public String dataReconciliation(MonthlyReport monthlyReport) {
        String result = "\n";
        int month = 1;
        int expense;
        int profit;
        while (monthlyReport.isKey(month) && yearlyReport.containsKey(month)) {
            expense = monthlyReport.sum(month, true);
            profit = monthlyReport.sum(month, false);
            if ((yearlyReport.get(month).getExpense() == expense) &&
                (yearlyReport.get(month).getProfit() == profit)) {
                result += ("Сверка за " + MONTHS[month - 1] + " завершилась успешно.\n");
            } else {
                result += ("Ошибка! Месяц " + MONTHS[month - 1] + ".\n");
            }
            month++;
        }
        return result;
    }

    // годовой отчёт (для меню 5)
    public String yearlyReport() {
        String result = "\n- Отчёт за " + year + " год -\n\n";
        int profitPerMonth;
        ResultOfMonth resultOfMonth;
        int profit = 0;
        int expense = 0;
        for (Integer key : yearlyReport.keySet()) {
            resultOfMonth = yearlyReport.get(key);
            expense += resultOfMonth.getExpense();
            profit +=  resultOfMonth.getProfit();
            profitPerMonth = resultOfMonth.getProfit() - resultOfMonth.getExpense();
            result += ("Прибыль за " + MONTHS[key - 1] + ": " + profitPerMonth + "\n");
        }
        result += ("Средний расход составил: " + (expense / yearlyReport.size()) + "\n");
        result += ("Средний доход составил: " + (profit / yearlyReport.size()) + "\n");
        return result;
    }
}
