package Q4;

public class Locker {

    private final boolean[] lockers = new boolean[100];
    private int openLockers;

    public Locker() {
        for (int i = 0; i < 100; i++) {
            this.lockers[i] = true;
        }
        this.openLockers = 100;
    }

    // same as toggleMultiplesOf(2) but its requirement
    public void toggleEvens() {
        for (int i = 1; i < 100; i += 2) {

            if (!this.lockers[i]) this.openLockers++;
            this.lockers[i] = !this.lockers[i];
            if (!this.lockers[i]) this.openLockers--;
        }

        System.out.println("toggle all even number open lockers: " + this.openLockers);
    }

    public void toggleMultiplesOf(int x) {
        for (int i = x - 1; i < 100; i += x) {

            if (!this.lockers[i]) this.openLockers++;
            this.lockers[i] = !this.lockers[i];
            if (!this.lockers[i]) this.openLockers--;
        }

        System.out.println("toggle all multiple of " + x + " number open lockers: " + this.openLockers);
    }

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
