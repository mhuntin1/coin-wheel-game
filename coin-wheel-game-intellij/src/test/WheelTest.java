import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * Tests for Wheel class
 *
 * @author Jon Bowen, Jackie Nugent, Mark Huntington
 * @version 0.0.1
 */
public class WheelTest {

    @Test
    public void makeRandomWheelTest() {
        for (int coinsPerWheel = 1; coinsPerWheel < 25; coinsPerWheel++) {

            Wheel w = new Wheel(coinsPerWheel, coinsPerWheel);
            String reveal = Utility.repeatChars('?', coinsPerWheel);
            CharSequence wholeWheel = w.getRevealedPattern(reveal);

            String regex = "[HT]{" + coinsPerWheel + "}";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(wholeWheel);
            assertTrue(m.matches());
        }
    }

    @Test
    public void getRevealedPatternTest() {

        Wheel w = new Wheel(5, 2);
        CharSequence revealed = w.getRevealedPattern("??---");
        Pattern p = Pattern.compile("[HT][HT]---");
        Matcher m = p.matcher(revealed);
        assertTrue(m.matches());

        revealed = w.getRevealedPattern("?-?--");
        p = Pattern.compile("[HT]-[HT]--");
        m = p.matcher(revealed);
        assertTrue(m.matches());

        revealed = w.getRevealedPattern("?-?-?"); // Request too many!
        p = Pattern.compile("[HT]-[HT]--");
        m = p.matcher(revealed);
        assertTrue(m.matches());

        revealed = w.getRevealedPattern("-----"); // Request too few!
        p = Pattern.compile("-----");
        m = p.matcher(revealed);
        assertTrue(m.matches());
    }
}