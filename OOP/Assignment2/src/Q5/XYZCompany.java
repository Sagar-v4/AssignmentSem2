package Q5;

import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;

/**
 * Company contains their employees' data in txt file.
 * you can calculate the number of employees who are below 50 year old.
 * @version 22.2.26
 * @author SaGaR VaRiyA
 */
public class XYZCompany {

    /**
     * using sc to open file.
     * using below50 to count dates below 50.
     * using file to store the file.
     * using currYear to store current year.
     * using currDay to store current day.
     * using currMonth to store current month.
     * using invalid to return invalid statement.
     */
    private Scanner sc;
    private int below50;
    private final File file;
    private final int currYear;
    private final byte currDay;
    private final byte currMonth;
    private final String invalid;

    /**
     * Construct company with storing the file.
     * Initialization of many variables like
     * currDay, currMonth, currYear, below50, invalid, file
     * with respect to their use.
     * @param fileName file containing dates
     */
    public XYZCompany(String fileName) {

        Calendar cal = Calendar.getInstance();
        currDay = (byte) cal.get(Calendar.DATE);
        currMonth = (byte) (cal.get(Calendar.MONTH) + 1);
        currYear = cal.get(Calendar.YEAR);
        below50 = 0;
        invalid = "INVALID DATE";
        file = new File(fileName);
    }

    /**
     * Get below 50 year count
     * @return count of less than 50 years
     */
    public int getBelow50() {
        return below50;
    }

    /**
     * Display file data.
     */
    public void displayFileData() {

        try {
            sc = new Scanner(file);
            sc.useDelimiter("\\Z");
            System.out.println(sc.next());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Count the year if it is below 50
     * @param day day of DOB
     * @param month month of DOB
     * @param year year of DOB
     */
    private void countIfBelow50(int day, int month, int year) {

        if (currYear - year > 0 && currYear - year < 50) {
            below50++;
        } else if (currYear - year == 50) {
            if (currMonth - month > 0 && currMonth - month < 12) {
                below50++;
            } else if (currMonth - month == 0) {
                if (currDay - day >= 0 && currDay - day < currDay) {
                    below50++;
                }
            }
        }
    }

    /**
     * give the year is leap or not
     * @param year year of DOB
     * @return true if year is leap else false
     */
    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
    }

    /**
     * A function to correct the date in int format
     * if its wrong then return invalid
     * @param day day of DOB
     * @return corrected day
     */
    private String dayCorrection(String day) {

        if (!day.matches("[0-9]+")) return invalid;
        byte d = Byte.parseByte(day);
        return (d < 10) ? "0" + d : String.valueOf(d);
    }

    /**
     * A function to correct the month in int format
     * if its wrong then return invalid
     * @param month month of DOB
     * @return corrected month
     */
    private String monthCorrection(String month) {

        byte m;
        if (month.matches("[0-9]+") && (month.length() == 1 || month.length() == 2) && Byte.parseByte(month) > 0 && Byte.parseByte(month) <= 12) {
            m = Byte.parseByte(month);
        } else {
            month = month.toLowerCase();
            switch (month) {
                case "jan":
                case "january":
                    m = 1;
                    break;
                case "feb":
                case "february":
                    m = 2;
                    break;
                case "mar":
                case "march":
                    m = 3;
                    break;
                case "apr":
                case "april":
                    m = 4;
                    break;
                case "may":
                    m = 5;
                    break;
                case "jun":
                case "june":
                    m = 6;
                    break;
                case "jul":
                case "july":
                    m = 7;
                    break;
                case "aug":
                case "august":
                    m = 8;
                    break;
                case "sep":
                case "sept":
                case "september":
                    m = 9;
                    break;
                case "oct":
                case "october":
                    m = 10;
                    break;
                case "nov":
                case "november":
                    m = 11;
                    break;
                case "dec":
                case "december":
                    m = 12;
                    break;
                default:
                    return invalid;
            }
        }

        return (m < 10) ? "0" + m : String.valueOf(m);
    }

    /**
     * A function to correct the year in int format
     * if its wrong then return invalid
     * @param year year of DOB
     * @return corrected year
     */
    private String yearCorrection(String year) {

        if (!year.matches("[0-9]+")) return invalid;
        return (Integer.parseInt(year) >= 0) ? year : invalid;
    }

    /**
     * see {@link #isLeapYear(int)}
     * see {@link #countIfBelow50(int, int, int)}
     * A function that will validate the date string into valid formatted date
     * @param day day of DOB
     * @param month month of DOB
     * @param year year of DOB
     * @return valid DOB in string format
     */
    private String dateValidation(String day, String month, String year) {

        if (month.equals("99")) return invalid;
        boolean leapYear = isLeapYear(Integer.parseInt(year));

        byte m = Byte.parseByte(month);
        byte d = Byte.parseByte(day);
        byte[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (d >= 1) {
            if (leapYear && m == 2) {
                if (29 < d) return invalid;
            } else if (days[m - 1] < d) return invalid;
        } else return invalid;

        countIfBelow50(Integer.parseInt(day), Integer.parseInt(month), Integer.parseInt(year));

        return day + "-" + month + "-" + year;
    }

    /**
     * see {@link #dayCorrection(String)}
     * see {@link #monthCorrection(String)}
     * see {@link #yearCorrection(String)}
     * see {@link #dateValidation(String, String, String)}
     * A function to convert the given date in string format to valid format.
     * @see #dateValidation(String, String, String)
     * @see #monthCorrection(String)
     * @see #yearCorrection(String)
     * @see #dateValidation(String, String, String)
     * @param str date in string format
     * @return converted in valid string date
     */
    private String convertToValidDate(String str) {

        if (str.contains(invalid)) return invalid;

        String day, month, year;
        int dashIndex = 0, dm = -1, my = -1;

        while ((dashIndex = str.indexOf("-", dashIndex)) != -1) {
            if (my != -1) return invalid;
            if (dm == -1) {
                dm = dashIndex;
            } else {
                my = dashIndex;
            }
            dashIndex++;
        }

        if (dm == -1 || my == -1) return invalid;

        day = dayCorrection(str.substring(0, dm));
        month = monthCorrection(str.substring(dm + 1, my));
        year = yearCorrection(str.substring(my + 1));

        if (month.equals(invalid) || year.equals(invalid)) return invalid;

        return dateValidation(day, month, year);
    }

    /**
     * Replace the line with new data in file.
     * @param lineNumber line number to replace
     * @param data new data to be place in given line number
     */
    private void replaceLine(int lineNumber, String data) {

        try {
            Path path = Paths.get(String.valueOf(file));
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            lines.set(lineNumber - 1, data);
            Files.write(path, lines, StandardCharsets.UTF_8);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    /**
     * see {@link #replaceLine(int, String)}
     * see {@link #convertToValidDate(String)}
     * A function that read the file data.
     * replace the date with the help convertToValidDate() and replaceLine methods
     * @see #replaceLine(int, String)
     * @see #convertToValidDate(String)
     */
    public void doCorrection() {

        try {

            int lineNumber = 0;
            sc = new Scanner(file);
            while (sc.hasNextLine()) {

                lineNumber++;
                String readDate = sc.nextLine();
                if (readDate.contains(invalid)) continue;
                String finalDate = convertToValidDate(readDate);

                if (!readDate.equals(finalDate)) {

                    if (finalDate.equals(invalid)) replaceLine(lineNumber, readDate + " | " + finalDate);
                    else replaceLine(lineNumber, finalDate);
                }
            }

            sc.close();
        } catch (Exception e) {
            System.out.println("ErrorR: " + e.getMessage());
        }
    }
}

class Q5 {

    public static void main(String[] args) {

        String fileName;
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter DOB file name: ");
        fileName = sc.nextLine();
        //fileName = "DOBs.txt";

        XYZCompany dataEngineer = new XYZCompany(fileName);

        System.out.println("\nCurrent Dates in file: " + fileName);
        dataEngineer.displayFileData();

        dataEngineer.doCorrection();

        System.out.println("\nUpdated Dates in file: " + fileName);
        dataEngineer.displayFileData();

        System.out.println("\nBelow 50: " + dataEngineer.getBelow50());
    }
}