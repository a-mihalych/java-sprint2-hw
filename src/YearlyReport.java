public class YearlyReport {

    public final int YEAR;
    public static final String[] MONTHS = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
                                          "Июль", "Август", "Сентябрь", "Октябрь", "Нояюрь", "Декабрь"};
    private int[] yearlyReport = new int[24];   // на каждый месяц две суммы, первая - расход, вторая - доход

    public YearlyReport(int year) {
        this.YEAR = year;
    }

    // добавляет в маасив yearlyReport траты и прибыли
    public void setReportMonth(String fileContents) {
        // рекомендованный в техническои задании System.lineSeparator(),
        // в windows, строку на части не разбивает, в split использую "\n"
        String[] reportAll = fileContents.split("\n");
        // два массива для для одного месяца? траты и прибыль
        String[] reportLine1;
        String[] reportLine2;
        int expense;
        int profit;
        int index;  // для массива yearlyReport (месяц - 1)
        for (int i = 1; i < reportAll.length; i += 2) {
            reportLine1 = reportAll[i].split(",");
            reportLine2 = reportAll[i + 1].split(",");
            index = Integer.parseInt(reportLine1[0]) - 1;
            if (Boolean.parseBoolean(reportLine1[2])) {
                expense = Integer.parseInt(reportLine1[1]);
                profit = Integer.parseInt(reportLine2[1]);
            } else {
                profit = Integer.parseInt(reportLine1[1]);
                expense = Integer.parseInt(reportLine2[1]);
            }
            yearlyReport[index * 2] = expense;
            yearlyReport[index * 2 + 1] = profit;
        }
    }

    // сверка данных (для меню 3)
    public String dataReconciliation(MonthlyReport monthlyReport) {
        String result = "\n";
        int month = 1;
        int index;
        int expense;
        int profit;
        while (monthlyReport.isKey(month)) {
            index = (month - 1) * 2;
            expense = monthlyReport.sum(month, "TRUE");
            profit = monthlyReport.sum(month, "FALSE");
            if ((yearlyReport[index] == expense) && (yearlyReport[index + 1] == profit)) {
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
        String result = "\n- Отчёт за " + YEAR + " год -\n\n";
        int numberOfMonths = 0;
        int profitPerMonth;
        while ((numberOfMonths < 12) &&
               ((yearlyReport[numberOfMonths * 2] != 0) || (yearlyReport[numberOfMonths * 2 + 1] != 0))) {
            profitPerMonth = yearlyReport[numberOfMonths * 2 + 1] - yearlyReport[numberOfMonths * 2];
            result += ("Прибыль за " + MONTHS[numberOfMonths] + ": " + profitPerMonth + "\n");
            numberOfMonths++;
        }
        int profit = 0;
        int expense = 0;
        for (int i = 0; i < numberOfMonths; i++) {
            expense += yearlyReport[i * 2];
            profit +=  yearlyReport[i * 2 + 1];
        }
        result += ("Средний расход составил: " + (expense / numberOfMonths) + "\n");
        result += ("Средний доход составил: " + (profit / numberOfMonths) + "\n");
        return result;
    }
}
