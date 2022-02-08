package Q4;

public class Locker {

    private final boolean[] lockers = new boolean[100];

    Locker() {
        for (int i = 0; i < 100; i++) {
            lockers[i] = true;
        }
    }

    // same as toggleMultiplesOf(2) but its requirement
    public void toggleEvens() {
        for (int i = 1; i <= 100; i++) {
            if(i % 2 == 0) lockers[i - 1] = !lockers[i - 1];
        }
    }

    public void toggleMultiplesOf(int x) {
        for (int i = 1; i <= 100; i++) {
            if(i % x == 0) lockers[i - 1] = !lockers[i - 1];
        }
    }

    public void DisplayOpenLockers() {
        int totalOpenlockers = 0;
        for (int i = 0; i < 100; i++) {
            if(lockers[i]) {
                System.out.println(i+1);
                totalOpenlockers++;
            }
        }

        System.out.println("Total Open Lockers: " + totalOpenlockers );
    }
}

class Q3 {
    public static void main(String[] args) {

        Locker lockers = new Locker();

        lockers.toggleEvens();

        for (int i = 3; i <= 100; i++) {
            lockers.toggleMultiplesOf(i);
        }

        lockers.DisplayOpenLockers();
    }
}
