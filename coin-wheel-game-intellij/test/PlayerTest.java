import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the Player class
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
            String numQs = repeatChars('?', revealsPerSpin);
            String padDash = repeatChars('-', numHidden);

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
                String padDash = repeatChars('-', numHidden);
                String startHeads = repeatChars('H', revealsPerSpin);
                String startTails = repeatChars('T', revealsPerSpin);

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

        for(int gameNum = 0; gameNum < 10; gameNum++){
            boolean didWin = false;
            p.beginGame(4, 2, 999);
            CharSequence wheel = makeRandomWheel(4);

            for (int i = 0; i < 5; i++){

                CharSequence reveal = p.getSlotsToReveal().toString();
                CharSequence revealed = getRevealedPattern(wheel, reveal);
                CharSequence newStates = p.getNewCoinStates(revealed);
                wheel = setNewStates(wheel, newStates);

                if (didWin(wheel)){
                    didWin = true;

                }
                wheel = spinWheel(wheel);
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
    public void flipAllTest(){
        Player p = new Player();

        CharSequence testWheel = "H-T-H-HTTH";
        CharSequence testFlip = p.flipAll(testWheel);
        assertEquals(testFlip, "T-H-T-THHT");

    }

    /**
     * Creates a string of the same chars
     */
    private String repeatChars(char c, int times) {
        char[] charArr = new char[times];
        Arrays.fill(charArr, c);
        return new String(charArr);
    }

    /**
     * Gets all of the binary permutations for a string
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

    /**
     * A method which spins wheel by randomizing string elements
     */
    private String spinWheel(CharSequence wheel) {
        int wheelLength = wheel.length();
        int copyIndex = (int) (Math.random() * wheelLength);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < wheelLength; i++){
            sb.append(wheel.charAt(copyIndex));
            copyIndex = (copyIndex + 1) % wheelLength;
        }
        return sb.toString();
    }

    /**
     * get the reveal pattern of the wheel by keeping the "?"
     * and changing everything else to "-"
     */
    private CharSequence getRevealedPattern(CharSequence wheel, CharSequence reveal){
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < reveal.length(); i++){
            if (reveal.charAt(i) == '?')
                sb.append(wheel.charAt(i));
            else
                sb.append('-');
        }
        return sb.toString();
    }

    /**
     * sets new coin states for values in the string which are "-"
     * @param wheel
     * @param newStates
     */
    private CharSequence setNewStates(CharSequence wheel, CharSequence newStates){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < wheel.length(); i++){
            if (newStates.charAt(i) != '-')
                sb.append(newStates.charAt(i));
            else
                sb.append(wheel.charAt(i));
        }
        return sb.toString();
    }

    /**
     * Creates a random wheel for testing
     */
    private CharSequence makeRandomWheel(int coinsPerWheel){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < coinsPerWheel; i ++){
            if (Math.random() < 0.5)
                sb.append('H');
            else
                sb.append('T');
        }
        return sb.toString();
    }

    /**
     * Checks if the user won the game
     */
    private boolean didWin(CharSequence wheel){
        for (int i = 1; i < wheel.length(); i++){
            if (wheel.charAt(i-1) != wheel.charAt(i))
                return false;
        }
        return true;
    }
}