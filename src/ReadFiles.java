import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReadFiles {

    private final String PATH = "resources\\";

    public void readFilesYear(YearlyReport yearlyReport) {
        String fileName = "y." + yearlyReport.YEAR + ".csv";
        String fileContents = readFile(fileName);
        if (fileContents != null) {
            yearlyReport.setReportMonth(fileContents);
        }
    }

    public void readFilesMonth(MonthlyReport monthlyReport) {
        String fileName = "m." + monthlyReport.YEAR;
        String fileContents = "";
        int month = 1;
        while ((fileContents != null) && (month > 0) && (month < 13)) {
            fileContents = readFile(fileName + (month < 10 ? "0" : "") + month + ".csv");
            if (fileContents != null) {
                monthlyReport.setReportMonth(month, fileContents);
                month++;
            }
        }
    }

    private String readFile(String file) {
        try {
            return Files.readString(Path.of(PATH + file));
        } catch (IOException e) {
            System.out.println("Ошибка! Невозможно прочитать файл: " + file +
                               ". Возможно, файл не находится в нужной директории.");
            return null;
        }
    }
}
