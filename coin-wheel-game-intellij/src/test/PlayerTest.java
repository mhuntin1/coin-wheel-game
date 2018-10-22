import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the Player class
 *
 * @author Jon Bowen, Jackie Nugent, Mark Huntington
 * @version 0.0.1
 */
public class PlayerTest {

    /**
     * A test which asserts that getSlotsToReveal() produces
     * the expected output.
     */
    @Test
    public void getSlotsToRevealGeneralTest() {
        Player p = new Player();
        int coinsPerWheel = 10;
        for (int revealsPerSpin = 1; revealsPerSpin < coinsPerWheel; revealsPerSpin++) {
            p.beginGame(coinsPerWheel, revealsPerSpin, 999);

            int numHidden = coinsPerWheel - revealsPerSpin;
            String numQs = Utility.repeatChars('?', revealsPerSpin);
            String padDash = Utility.repeatChars('-', numHidden);

            String expected = numQs + padDash;
            String actual = p.getSlotsToRevealGeneral().toString();
            assertEquals(expected, actual);
        }
    }

    /**
     * A test which begins a mock game and asserts the
     * getNewCoinStateGeneral() is correctly revealing coins
     */
    @Test
    public void getNewCoinStatesGeneralTest() {
        int maxCoinsPerWheel = 10;
        for (int coinsPerWheel = 1; coinsPerWheel <= maxCoinsPerWheel; coinsPerWheel++) {
            for (int revealsPerSpin = 1; revealsPerSpin <= coinsPerWheel; revealsPerSpin++) {
                Player p = new Player();
                p.beginGame(coinsPerWheel, revealsPerSpin, 999);

                int numHidden = coinsPerWheel - revealsPerSpin;
                String padDash = Utility.repeatChars('-', numHidden);
                String startHeads = Utility.repeatChars('H', revealsPerSpin);
                String startTails = Utility.repeatChars('T', revealsPerSpin);

                List<String> combos = getAllBinaryPermutations(revealsPerSpin);

                for (String combo : combos) {
                    String htString = combo
                            .replace('1', 'H')
                            .replace('0', 'T');

                    String pattern = htString + padDash;
                    String resp = p.getNewCoinStatesGeneral(pattern).toString();

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

    /**
     * A test which asserts the 4/2 game is working as intended
     * with the given methods
     */
    @Test
    public void fourTwoPlayerTest() {
        Player p = new Player();

        for (int gameNum = 0; gameNum < 10; gameNum++) {
            boolean didWin = false;
            p.beginGame(4, 2, 999);
            Wheel w = new Wheel(4, 2);

            for (int i = 0; i < 5; i++) {

                CharSequence reveal = p.getSlotsToReveal().toString();
                CharSequence revealed = w.getRevealedPattern(reveal);

                CharSequence newStates = p.getNewCoinStates(revealed);
                w.setNewStates(newStates);

                if (w.didWin()) {
                    didWin = true;
                    break;
                }

                w.spinWheel();
            }
            assertTrue(didWin);
        }
    }

    /**
     * A test which asserts that the flipAll
     * method is manipulating the CharSequence
     * as expected
     */
    @Test
    public void flipAllTest() {
        Player p = new Player();

        CharSequence testWheel = "H-T-H-HTTH";
        CharSequence testFlip = p.flipAll(testWheel);
        assertEquals(testFlip, "T-H-T-THHT");

    }

    /**
     * Gets all of the binary permutations for a string
     *
     * @param bitStringLength
     */
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