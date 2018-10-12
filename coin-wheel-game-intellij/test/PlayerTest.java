import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the Player class
 */
public class PlayerTest {

    @Test
    public void getSlotsToRevealTest() {
        Player p = new Player();
        p.beginGame(7, 3, 4);
        String expected = "???----";
        String actual = p.getSlotsToReveal().toString();
        assertTrue(expected.equals(actual));
    }

    @Test
    public void getNewCoinStatesTest() {
        Player p = new Player();
        p.beginGame(7, 3, 4);
        List<String> currentStates = new ArrayList<>();
        currentStates.add("HHH----");
        currentStates.add("HHT----");
        currentStates.add("HTT----");
        currentStates.add("HTH----");
        currentStates.add("TTT----");
        currentStates.add("THT----");

        List<String> expectedNewState = new ArrayList<>();
        expectedNewState.add("HHH----");
        expectedNewState.add("HHH----");
        expectedNewState.add("TTT----");
        expectedNewState.add("HHH----");
        expectedNewState.add("TTT----");
        expectedNewState.add("TTT----");

        for (int i = 0; i < currentStates.size(); i++){
            String s = currentStates.get(i);
            String resp = p.getNewCoinStates(s).toString();

            System.out.println(s);
            System.out.println(resp);

            assertTrue(resp.equals(expectedNewState.get(i)));
        }
    }
}