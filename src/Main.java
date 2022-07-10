import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int userInput;
        MonthlyReport monthlyReport = new MonthlyReport();
        YearlyReport yearlyReport = new YearlyReport();
        ReadFiles readFiles = new ReadFiles();
        boolean isEmptyMonth;
        boolean isEmptyYear;
        do {
            isEmptyMonth = monthlyReport.isEmptyHashMap();
            isEmptyYear = yearlyReport.isEmptyHashMap();
            printMenu(isEmptyMonth, isEmptyYear);
            userInput = getNumber(scanner);
            switch (userInput) {
                case 1:
                    System.out.println("Считать все месячные отчёты]");
                    readFiles.readFilesMonth(monthlyReport);
                    break;
                case 2:
                    System.out.println("Считать годовой отчёт]");
                    readFiles.readFilesYear(yearlyReport);
                    break;
                case 3:
                    System.out.println("Сверить отчёты]");
                    if(!(isEmptyMonth || isEmptyYear)) {
                        System.out.println(yearlyReport.dataReconciliation(monthlyReport));
                    } else {
                        System.out.println("Ошибка!");
                        if (isEmptyMonth) {
                            System.out.println("Нужно считать месячные отчёты.");
                        }
                        if (isEmptyYear) {
                            System.out.println("Нужно считать годовой отчёт.");
                        }
                    }
                    break;
                case 4:
                    System.out.println("Вывести информацию о всех месячных отчётах]");
                    if(!isEmptyMonth) {
                        System.out.println(monthlyReport.monthlyReports());
                    } else {
                        System.out.println("Ошибка!\nНужно считать месячные отчёты.");
                    }
                    break;
                case 5:
                    System.out.println("Вывести информацию о годовом отчёте]");
                    if(!isEmptyYear) {
                        System.out.println(yearlyReport.yearlyReport());
                    } else {
                        System.out.println("Ошибка!\nНужно считать годовой отчёт.");
                    }
                    break;
                case 0:
                    System.out.println("Выйти из приложения]");
                    break;
                default: // число вне границ (0 - 5)
                    System.out.println("Неверный ввод! Нужно ввести число из меню]");
            }
        } while (userInput != 0);
        System.out.println("Программа завершена");
    }

    // печать меню
    private static void printMenu(boolean isEmptyMonth, boolean isEmptyYear) {
        System.out.println("\n\n" + "-= Автоматизация бухгалтерии =-" + "\n");
        System.out.println("1 [Считать все месячные отчёты]");
        System.out.println("2 [Считать годовой отчёт]");
        System.out.println("3 " + ((isEmptyMonth || isEmptyYear) ? "Недоступно! " : "") + "[Сверить отчёты]");
        System.out.println("4 " + (isEmptyMonth ? "Недоступно! " : "") + "[Вывести информацию о всех месячных отчётах]");
        System.out.println("5 " + (isEmptyYear ? "Недоступно! " : "") + "[Вывести информацию о годовом отчёте]");
        System.out.println("0 [Выход из приложения]");
        System.out.print("\nОжидание ввода: ");
    }

    // проверка типа, на целое число
    private static int getNumber(Scanner scanner) {
        int result;
        while (!scanner.hasNextInt()) {
            System.out.print("Нужно ввести целое число: ");
            scanner.next();
        }
        result = scanner.nextInt();
        System.out.print("\nВаш ввод: " + result + " [");
        return result;
    }
}
