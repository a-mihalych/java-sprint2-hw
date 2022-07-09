import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean[] menuFlag = {false, false}; // для меню 1 и 2 (false - не загружено)
        Scanner scanner = new Scanner(System.in);
        int userInput;
        MonthlyReport monthlyReport = new MonthlyReport(2021);
        YearlyReport yearlyReport = new YearlyReport(2021);
        ReadFiles readFiles = new ReadFiles();
        do {
            printMenu(menuFlag);
            userInput = getNumber(scanner);
            switch (userInput) {
                case 1:
                    System.out.println("Считать все месячные отчёты]");
                    menuFlag[0] = true;
                    readFiles.readFilesMonth(monthlyReport);
                    break;
                case 2:
                    System.out.println("Считать годовой отчёт]");
                    menuFlag[1] = true;
                    readFiles.readFilesYear(yearlyReport);
                    break;
                case 3:
                    System.out.println("Сверить отчёты]");
                    if(menuFlag[0] && menuFlag[1]) {
                        System.out.println(yearlyReport.dataReconciliation(monthlyReport));
                    } else {
                        System.out.println("Ошибка!");
                        if (!menuFlag[0]) {
                            System.out.println("Нужно считать месячные отчёты.");
                        }
                        if (!menuFlag[1]) {
                            System.out.println("Нужно считать годовой отчёт.");
                        }
                    }
                    break;
                case 4:
                    System.out.println("Вывести информацию о всех месячных отчётах]");
                    if(menuFlag[0]) {
                        System.out.println(monthlyReport.monthlyReports());
                    } else {
                        System.out.println("Ошибка!\nНужно считать месячные отчёты.");
                    }
                    break;
                case 5:
                    System.out.println("Вывести информацию о годовом отчёте]");
                    if(menuFlag[1]) {
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
    private static void printMenu(boolean[] menuFlag) {
        System.out.println("\n\n" + "-= Автоматизация бухгалтерии =-" + "\n");
        System.out.println("1 [Считать все месячные отчёты]");
        System.out.println("2 [Считать годовой отчёт]");
        System.out.println("3 " + ((menuFlag[0] && menuFlag[1]) ? "" : "Недоступно! ") + "[Сверить отчёты]");
        System.out.println("4 " + (menuFlag[0] ? "" : "Недоступно! ") + "[Вывести информацию о всех месячных отчётах]");
        System.out.println("5 " + (menuFlag[1] ? "" : "Недоступно! ") + "[Вывести информацию о годовом отчёте]");
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
