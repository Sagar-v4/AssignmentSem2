/**
 * @author Sagar Variya | 202112114
 */

package Q4;

/**
 * this locker class is about the open and closed ones
 * it has default 100 lockers all are open
 * there are 2 functions to toggle the locker opening and closing
 */
public class Locker {

    /**
     * initializing and declaring private data member
     * lockers array to hold 100 locker value in boolean
     * openLockers to count how many lockers are open
     */
    private final boolean[] lockers = new boolean[100];
    private int openLockers;

    /**
     * default constructor
     * it will set all the lockers open and total openLockers to 100
     */
    public Locker() {
        for (int i = 0; i < 100; i++) {
            this.lockers[i] = true;
        }
        this.openLockers = 100;
    }

    /**
     * this function will toggle all the even number of lockers
     */
    // same as toggleMultiplesOf(2) but its requirement
    public void toggleEvens() {
        for (int i = 1; i < 100; i += 2) {

            if (!this.lockers[i]) this.openLockers++;
            this.lockers[i] = !this.lockers[i];
            if (!this.lockers[i]) this.openLockers--;
        }

        System.out.println("after toggling all even number open lockers open lockers: " + this.openLockers);
    }

    /**
     * this function take integer number and toggle it's all multiply number of lockers
     * @param x integer type | multiply of x
     */
    public void toggleMultiplesOf(int x) {
        for (int i = x - 1; i < 100; i += x) {

            if (!this.lockers[i]) this.openLockers++;
            this.lockers[i] = !this.lockers[i];
            if (!this.lockers[i]) this.openLockers--;
        }

        System.out.println("after toggling all multiple of " + x + " number open lockers: " + this.openLockers);
    }

    /**
     * this function displays all the open lockers and total count of open lockers
     */
    public void DisplayOpenLockers() {
        for (int i = 0; i < 100; i++) {
            if (this.lockers[i]) {
                System.out.println(i + 1);
            }
        }

        System.out.println("Total Open Lockers: " + this.openLockers);
    }
}

class Q4 {
    public static void main(String[] args) {

        Locker lockers = new Locker();

        lockers.toggleEvens();

        //lockers.DisplayOpenLockers();

        for (int i = 3; i <= 100; i++) {
            lockers.toggleMultiplesOf(i);
        }

        lockers.DisplayOpenLockers();

    }
}
