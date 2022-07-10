import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

public class ReadFiles {

    private final String PATH = "resources\\";

    public void readFilesYear(YearlyReport yearlyReport) {
        int year = getYear();
        String fileName = "y." + year + ".csv";
        String fileContents = readFile(fileName);
        if (fileContents != null) {
            yearlyReport.setReportMonth(fileContents, year);
        }
    }

    public void readFilesMonth(MonthlyReport monthlyReport) {
        int year = getYear();
        String fileName = "m." + year;
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

    // считываю год из имени файла
    private int getYear() {
        File file = new File(PATH);
        String[] nameFiles = file.list();
        int year = 0;
        String[] pars;
        for (String nameFile : nameFiles) {
            if (nameFile.length() == 10) {
                pars = nameFile.split(Pattern.quote("."));
                year = Integer.parseInt(pars[1]);
                return year;
            }
        }
        return year;
    }
}
