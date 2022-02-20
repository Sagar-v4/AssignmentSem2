/**
 * @author Sagar Variya | 202112114
 */

package Q2;

import java.util.Scanner;

/**
 *  Calendar class contains 1 data members (without getters & setters) that describes the year.
 *  it has member function that will give the boolean value about year is leap or not.
 */
public class Calendar {

    /**
     * private data member
     */
    private final long year;

    /**
     * constructor of the calendar class to set the year
     * @param year year
     */
    public Calendar(long year) {
        this.year = year;
    }

    /**
     * this function give the boolean value if the year is leap then true else false
     * @return the boolean value about leap year
     */
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

            Calendar calendar1 = new Calendar(year);
            System.out.println(year + ((calendar1.isLeapYear()) ? " is " : " is Not a ") + "Leap year.\n");
        }
    }
}
