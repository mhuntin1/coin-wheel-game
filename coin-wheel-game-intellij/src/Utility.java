import java.util.Arrays;

/**
 * Utility methods
 * @author Jon Bowen, Jackie Nugent, Mark Huntington

 * @version 0.0.1
 */
public class Utility {

    /**
     * Counts the number of lower and upper case instances of token
     * in the char sequence
     *
     * @param charSeq     The sequence to consider
     * @param charToCount The character to count occurrences of
     * @return The number of times that upper or lower case charToCount
     * occurs in charSeq
     */
    public static int getCharCount(CharSequence charSeq, char charToCount) {
        int headsCount = (int) charSeq
                .chars()
                .map(Character::toUpperCase)
                .filter(c -> c == charToCount)
                .count();

        return headsCount;
    }

    /**
     * Creates a string of the same chars
     */
    public static String repeatChars(char c, int times) {
        char[] charArr = new char[times];
        Arrays.fill(charArr, c);
        return new String(charArr);
    }
}
