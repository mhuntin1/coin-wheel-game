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

    @Test
    public void fourTwoPlayerTest() {
        Player p = new Player();

        for(int gameNum = 0; gameNum < 10; gameNum++){
            boolean didWin = false;
            p.beginGame(4, 2, 999);
            CharSequence wheel = makeRandomWheel(4);

            for (int i = 0; i < 5; i++){
                System.out.print(wheel + " | ");

                CharSequence reveal = p.getSlotsToReveal().toString();
                CharSequence revealed = getRevealedPattern(wheel, reveal);
                CharSequence newStates = p.getNewCoinStates(revealed);
                wheel = setNewStates(wheel, newStates);

                System.out.print(reveal + " | ");
                System.out.print(revealed + " | ");
                System.out.print(newStates + " | ");
                System.out.print(wheel);

                if (didWin(wheel)){
                    System.out.print(" | WIN!!!");
                    didWin = true;
                }
                System.out.println();

                wheel = spinWheel(wheel);
            }
            assertTrue(didWin);
            System.out.println();
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

    private boolean didWin(CharSequence wheel){
        for (int i = 1; i < wheel.length(); i++){
            if (wheel.charAt(i-1) != wheel.charAt(i))
                return false;
        }
        return true;
    }
}