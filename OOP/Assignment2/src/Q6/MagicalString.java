package Q6;

import java.util.Scanner;

/**
 * Build magical string magic by repeating the string str infinitely many times.
 * For example, if str = ’xxyyy’,then magic = ’xxyyyxxyyyxxyyy...’.
 * @version 22.2.26
 * @author SaGaR VaRiyA
 */
public class MagicalString {

    /**
     * using magicalStr to store original string.
     */
    private final String magicalStr;

    /**
     * Construct MagicalString class with the initialization of original string.
     * @param magicalStr original string.
     */
    public MagicalString(String magicalStr) {
        this.magicalStr = magicalStr;
    }

    /**
     * A function to get the character count in magical string
     * in specific sub string of magical string.
     * @param l Starting index
     * @param r Ending index
     * @param ch Character to be found
     * @return Appearance count of given character in magical string
     */
    public int checkClaim(int l, int r, char ch) {

        int left = l - 1, right = r - 1, countChar = 0;
        int iterations = right / magicalStr.length();
        StringBuilder tempMagicalStr = new StringBuilder(magicalStr);

        while (iterations-- > 0) {
            tempMagicalStr.append(magicalStr);
        }

        while ((left = tempMagicalStr.toString().indexOf(ch, left)) != -1) {
            if (left++ > right) break;
            countChar++;
        }
        return countChar;
    }

}

class Q6 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Magical String: ");
        MagicalString yashwant = new MagicalString(sc.nextLine());

        for (int i = 0; i < 6; i++) {

            System.out.println("\nQuery #" + (i + 1));

            System.out.print("l: ");
            int l = sc.nextInt();

            System.out.print("r: ");
            int r = sc.nextInt();

            System.out.print("ch: ");
            char ch = sc.next().trim().toLowerCase().charAt(0);

            System.out.println("Count [" + ch + "]: " + yashwant.checkClaim(l, r, ch));

        }
    }
}
