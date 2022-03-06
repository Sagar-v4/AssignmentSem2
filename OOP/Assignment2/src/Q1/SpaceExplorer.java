package Q1;

import java.util.Scanner;

/**
 * Space Explorer class can check altered characters of message come from astronaut.
 * @version 22.2.26
 * @author SaGaR VaRiyA
 */
public class SpaceExplorer {

    /**
     * The original msg and count of altered  characters.
     */
    private final String sosMsg;
    private int altered;

    /**
     * Creates a new Space Explorer with the given original message.
     * @param sosMsg This original sos message
     */
    public SpaceExplorer(String sosMsg) {
        this.sosMsg = sosMsg;
    }

    /**
     * Correct the message came from astronaut.
     * @param msg message for correction
     * @return corrected message
     */
    private String messageCorrection(String msg) {

        int strLen = msg.length();
        int sosMsgLen = sosMsg.length();
        int missingChars = strLen % sosMsgLen;

        if (missingChars != 0) {
            missingChars = sosMsgLen - missingChars;
        }

        if (missingChars > 0) {
            StringBuilder strBuilder = new StringBuilder(msg);
            while (missingChars-- > 0) strBuilder.append("_");
            msg = strBuilder.toString();
        }

        return msg;
    }

    /**
     * see {@link #messageCorrection(String)}
     * Astronaut sending message to earth
     * @see #messageCorrection(String)
     * @param msg This is astronaut's message
     */
    public void sendMessage(String msg) {

        altered = 0;
        int strLen = msg.length();
        msg = messageCorrection(msg);
        int sosMsgLen = sosMsg.length();
        int startIndex = 0, msgCount = strLen / sosMsgLen;

        while (msgCount-- > 0) {

            String tempMsg = msg.substring(startIndex, startIndex += sosMsgLen);

            if (!sosMsg.equals(tempMsg)) {
                for (int i = 0; i < sosMsgLen; i++) {
                    if (sosMsg.charAt(i) != tempMsg.charAt(i)) {
                        altered++;
                    }
                }
            }
        }
    }

    /**
     * Get the altered character count of message
     * @return altered number
     */
    public int getAltered() {
        return altered;
    }

}

class Q1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        SpaceExplorer astronaut = new SpaceExplorer("MAYDAY");

        for (int i = 0; i < 5; i++) {

            System.out.print("\nEnter Message: ");
            astronaut.sendMessage(sc.nextLine());

            System.out.println("Total altered characters: " + astronaut.getAltered());
        }
    }
}