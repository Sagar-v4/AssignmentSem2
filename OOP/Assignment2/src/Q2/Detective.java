package Q2;

import java.util.Scanner;

/**
 * A mystery solver by decode a letter from string.
 * String is valid if all the character appears the same number of time.
 * for validation, it can remove just 1 character at one index in the string,
 * and the remaining characters will occur the same number of times.
 * @version 22.2.25
 * @author SaGaR VaRiyA
 */
public class Detective {

    /**
     * use arrLen to store the distinct character length.
     * use charCnt1 and charCnt2 to store 2 different character count in string.
     * use letters to store the original string of letters.
     * use charArr to store all unique letter.
     * use charCountArr to store all unique letter appear count.
     */
    private int arrLen;
    private int charCnt1;
    private int charCnt2;
    private final String letters;
    private final char[] charArr;
    private final int[] charCountArr;

    /**
     * Construct a Detective with the original string of letters.
     * Create array according to letters length.
     * @param str original string of letters.
     */
    public Detective(String str) {

        arrLen = 0;
        letters = str;
        charArr = new char[letters.length()];
        charCountArr = new int[letters.length()];
    }

    /**
     * Check if the character is existed in counted array.
     * If character exist then increase the count of that character.
     * @param c Character to check existence.
     * @param len To check in between the length.
     * @return True if character exist else False.
     */
    private boolean checkCharExist(char c, int len) {

        for (int j = 0; j < len; j++) {
            if (charArr[j] == c) {
                charCountArr[j]++;
                return true;
            }
        }
        return false;
    }

    /**
     * see {@link #checkCharExist(char, int)}
     * Calculate the unique characters and their number of appearance.
     * using checkCharExist it will take decision.
     * @see #checkCharExist(char, int)
     */
    private void calcChars() {

        for (int i = 0; i < letters.length(); i++) {
            char tempChar = letters.charAt(i);
            boolean charExist = checkCharExist(tempChar, arrLen);

            if (!charExist) {
                charArr[arrLen] = tempChar;
                charCountArr[arrLen] = 1;
                arrLen++;
            }
        }
    }

    /**
     * Give how many times the number of character count appear in charCountArr.
     * @param n Get its occurrence.
     * @return Occurrence of the given number.
     */
    private int giveOccurrence(int n) {

        int cnt = 0;
        for (int i = 0; i < arrLen; i++) {
            if (charCountArr[i] == n) {
                cnt++;
            }
        }
        return cnt;
    }

    /**
     * see {@link #calcChars()}
     * see {@link #giveOccurrence(int)}
     * A function to check if the given letter of string is valid or not.
     * @see #calcChars()
     * @see #giveOccurrence(int)
     * @return True if letters are valid else False.
     */
    private boolean isValid() {

        calcChars();
        StringBuilder distinctCounts = new StringBuilder();

        for (int i = 0; i < arrLen; i++) {
            if (!distinctCounts.toString().contains(String.valueOf(charCountArr[i]))) {
                distinctCounts.append(charCountArr[i]);
            }
        }

        if (distinctCounts.length() == 1) return true;
        if (distinctCounts.length() > 2) return false;

        charCnt1 = Integer.parseInt(String.valueOf(distinctCounts.charAt(0)));
        charCnt2 = Integer.parseInt(String.valueOf(distinctCounts.charAt(1)));
        int difference = charCnt1 - charCnt2;
        difference = (difference < 0) ? difference * -1 : difference;

        if (difference == 1) {

            int occurrence1 = giveOccurrence(charCnt1);
            int occurrence2 = giveOccurrence(charCnt2);

            if (occurrence1 == 1 || occurrence2 == 1) {
                if (occurrence1 == 1 && (charCnt1 == 1 || charCnt1 - charCnt2 == 1)) {
                    return true;
                } else return occurrence2 == 1 && (charCnt2 == 1 || charCnt2 - charCnt1 == 1);
            } else return false;
        }
        return false;
    }

    /**
     * Remove the character that appear give number of time in letters.
     * @param counts Appear count to remove.
     * @return The character that will be removed.
     */
    private char removeChar(int counts) {

        for (int i = 0; i < arrLen; i++) {
            if (counts == charCountArr[i]) {
                return charArr[i];
            }
        }
        return 0;
    }

    /**
     * see {@link #removeChar(int)}
     * see {@link #giveOccurrence(int)}
     * Decide which character to remove based on its appearance.
     * @see #removeChar(int)
     * @see #giveOccurrence(int)
     * @return The character that will be removed.
     */
    private char getRemovalChar() {

        char removedChar = ' ';
        if (charCnt1 == charCnt2) return removedChar;

        int occurrence1 = giveOccurrence(charCnt1);
        int occurrence2 = giveOccurrence(charCnt2);

        if (occurrence1 == 1 && occurrence2 == 1) {
            removedChar = removeChar(Math.max(charCnt1, charCnt2));
        } else if (occurrence1 == 1 && occurrence2 == 2) {
            removedChar = removeChar(charCnt1);
        } else if (occurrence2 == 1 && occurrence1 == 2) {
            removedChar = removeChar(charCnt2);
        } else if (occurrence1 == 1) {
            removedChar = removeChar(charCnt1);
        } else if (occurrence2 == 1) {
            removedChar = removeChar(charCnt2);
        }
        return removedChar;
    }

    /**
     * see {@link #isValid()}
     * see {@link #getRemovalChar()}
     * Decode the letters and give removal character.
     * @see #isValid()
     * @see #getRemovalChar()
     * @return The character that will be removed.
     */
    public char decode() {
        return (!isValid()) ? 0 : getRemovalChar();
    }

}

class Q2 {

    public static void main(String[] args) {

        String str;
        char extraLetter;
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 5; i++) {

            System.out.print("\nEnter Letter: ");
            str = sc.nextLine();

            Detective rahul = new Detective(str);
            extraLetter = rahul.decode();

            System.out.println((extraLetter != 0) ? ("Yes " + extraLetter) : "No");
        }
    }
}