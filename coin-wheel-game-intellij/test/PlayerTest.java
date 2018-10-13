import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the Player class
 */
public class PlayerTest {

    @Test
    public void getSlotsToRevealTest() {
        Player p = new Player();
        int coinsPerWheel = 10;
        for (int revealsPerSpin = 1; revealsPerSpin < coinsPerWheel; revealsPerSpin++) {
            p.beginGame(coinsPerWheel, revealsPerSpin, 999);

            int numHidden = coinsPerWheel - revealsPerSpin;
            String numQs = repeatChars('?', revealsPerSpin);
            String padDash = repeatChars('-', numHidden);

            String expected = numQs + padDash;
            String actual = p.getSlotsToReveal().toString();
            assertEquals(expected, actual);
        }
    }

    @Test
    public void getNewCoinStatesTest() {
        int maxCoinsPerWheel = 10;
        for (int coinsPerWheel = 1; coinsPerWheel <= maxCoinsPerWheel; coinsPerWheel++) {
            for (int revealsPerSpin = 1; revealsPerSpin <= coinsPerWheel; revealsPerSpin++) {
                Player p = new Player();
                p.beginGame(coinsPerWheel, revealsPerSpin, 999);

                int numHidden = coinsPerWheel - revealsPerSpin;
                String padDash = repeatChars('-', numHidden);
                String startHeads = repeatChars('H', revealsPerSpin);
                String startTails = repeatChars('T', revealsPerSpin);

                List<String> combos = getAllBinaryPermutations(revealsPerSpin);

                for (String combo : combos) {
                    String htString = combo
                            .replace('1', 'H')
                            .replace('0', 'T');

                    String pattern = htString + padDash;
                    String resp = p.getNewCoinStates(pattern).toString();

                    int numHeads = numOnesInBinaryString(combo);
                    if (numHeads > revealsPerSpin - numHeads) {
                        assertEquals(resp, startHeads + padDash);
                    } else {
                        assertEquals(resp, startTails + padDash);
                    }
                }
            }
        }
    }

    private String repeatChars(char c, int times) {
        char[] charArr = new char[times];
        Arrays.fill(charArr, c);
        return new String(charArr);
    }

    private List<String> getAllBinaryPermutations(int bitStringLength) {
        String format = "%0" + bitStringLength + "d";
        List<String> combos = new ArrayList<>(bitStringLength);
        for (int i = 0; i < Math.pow(2, bitStringLength); i++) {
            String combo = String.format(
                    format,
                    Integer.valueOf(Integer.toBinaryString(i)));
            combos.add(combo);
        }
        return combos;
    }

    private int numOnesInBinaryString(String binaryString) {
        int numHeads = 0;
        for (int j = 0; j < binaryString.length(); j++) {
            numHeads += binaryString.charAt(j) - '0';
        }
        return numHeads;
    }
}