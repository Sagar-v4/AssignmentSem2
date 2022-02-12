package Q2;

import java.util.Scanner;

public class Calendar {

    private final long year;

    public Calendar(long year) {
        this.year = year;
    }

    public boolean isLeapYear() {
        return (this.year % 4 == 0 && (this.year % 100 != 0 || this.year % 400 == 0));
    }
}

class Q2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 3; i++) {

            System.out.print("Enter year: ");
            long year = sc.nextInt();

            Calendar calender = new Calendar(year);

            System.out.println(year + ((calender.isLeapYear()) ? " is " : " is Not a ") + "Leap year.\n");
        }
    }
}
