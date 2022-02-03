package Q2;

import java.util.Scanner;

public class Calendar {

    private final long year;

    Calendar(long year) { this.year = year; }

    public boolean isLeapYear() {

        if(this.year % 4 == 0)
        {
            if(this.year % 100 == 0) return this.year % 400 == 0;
            else return true;
        }
        else return false;
    }
}

class Q2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 3; i++) {

            System.out.print("Enter year: ");
            long year = sc.nextInt();

            Calendar calender = new Calendar(year);

            System.out.println(year + ((calender.isLeapYear()) ? " is " : " is Not a ") + "Leap year.\n" );
        }
    }
}
