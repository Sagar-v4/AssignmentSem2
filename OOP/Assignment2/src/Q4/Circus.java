package Q4;

import java.util.Scanner;

/**
 * Circus game of ring and rods.
 * Rings can be either red, green, or blue.
 * There are a total of ten rods that are present on the stage.
 * The rod that has all three colored ring is called fully colored rode.
 * This class has default constructor.
 * @version 22.2.22
 * @author SaGaR VaRiyA
 */
public class Circus {

    /**
     * using rodes array to store rings position in rods.
     */
    boolean[][] rods;
    /**
     * using fullyColoredRods to count the rod that has all three colored rings.
     */
    int fullyColoredRods;

    /**
     * using rods to store ring positions in all rods.
     * using fullyColoredRods to count Rods that has all three colored rings.
     * Set the rods with different colored rings and count fully colored rode.
     * @param s Thrown ring in rods.
     */
    private void setRods(String s) {

        fullyColoredRods = 0;
        rods = new boolean[10][3];

        for (int i = 0; i < s.length(); i += 2) {

            int colorNum = -1;
            char color = s.charAt(i);
            int rod = Integer.parseInt(String.valueOf(s.charAt(i + 1)));

            if (color == 'R') colorNum = 0;
            if (color == 'G') colorNum = 1;
            if (color == 'B') colorNum = 2;

            rods[rod][colorNum] = true;

            if (rods[rod][0] && rods[rod][1] && rods[rod][2]) fullyColoredRods++;
        }
    }

    /**
     * see {@link #setRods(String)}
     * Set rods if given rod string is valid.
     * It will set rods via setRods method.
     * @see #setRods(String)
     * @param str Thrown rings in rods string
     */
    public void setRodsString(String str) {

        str = str.toUpperCase();
        if (str.length() % 2 == 0) setRods(str);
    }

    /**
     * Get the fully colored rode count
     * @return fully colored rodes.
     */
    public int getFullyColoredRods() {
        return fullyColoredRods;
    }

}

class Q4 {

    public static void main(String[] args) {

        Circus shepherd = new Circus();
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 5; i++) {

            System.out.print("\nEnter Message: ");
            shepherd.setRodsString(sc.nextLine()); //str = "R3G2B1G3B3";

            System.out.println("Total Fully Colored Rods: " + shepherd.getFullyColoredRods());
        }
    }
}
