package Q3;

import java.util.Scanner;

/**
 * Famous game bulls and cows.
 * On a sheet of paper, the players each write a 4-digit secret number.
 * The digits must be all different. Then, in turn,
 * the players try to guess their opponent's number who gives the number of matches.
 * If the matching digits are in their right positions, they are "bulls",
 * if in different positions, they are "cows"
 * @version 22.2.23
 * @author SaGaR VaRiyA
 */
public class BullsAndCows {

    /**
     * using secret to store the secret number.
     * using answer to store the correct answer.
     */
    private final int secret;
    private final String ans;

    /**
     * Construct a game with secret number to guess.
     * Initialize the correct answer.
     * @param secret Secret number to guess.
     */
    public BullsAndCows(int secret) {
        this.secret = secret;
        ans = guess(this.secret);
    }

    /**
     * Get the correct answer of secret number.
     * @return correct answer.
     */
    public String getCorrectGuess() {
        return ans;
    }

    /**
     * Guess the number.
     * @param guess guessed number.
     * @return answer of guessed number.
     */
    public String guess(int guess) {

        int s = secret, g = guess;
        int Length = Integer.toString(this.secret).length();
        int[] numbersArr = new int[10];
        int[] guessArr = new int[Length];
        int[] secretArr = new int[Length];
        int bulls = 0, cows = 0, index = 0;

        do {
            secretArr[index++] = (s % 10);
        } while ((s /= 10) > 0);

        index = 0;
        do {
            guessArr[index++] = (g % 10);
        } while ((g /= 10) > 0);


        for (int i = 0; i < Length; i++) {
            if (secretArr[i] == guessArr[i]) bulls++;
            else {
                if (numbersArr[secretArr[i]]++ < 0) cows++;
                if (numbersArr[guessArr[i]]-- > 0) cows++;
            }
        }
        return (bulls + "A" + cows + "B");
    }

}

class Q3 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("\nSecret : ");
        BullsAndCows player = new BullsAndCows(sc.nextInt());

        while (true) {

            System.out.print("\nGuess : ");
            String ans = player.guess(sc.nextInt());

            System.out.println("Ans: " + ans);
            if (ans.equals(player.getCorrectGuess())) {
                System.out.println((char) 9733 + " " + (char) 9734 + " Congratulations you guessed it right!!! " + (char) 9734 + " " + (char) 9733);
                break;
            }
        }
    }
}
