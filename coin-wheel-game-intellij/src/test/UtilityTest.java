import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Tests for the Utility class
 *
 * @author Jon Bowen, Jackie Nugent, Mark Huntington
 * @version 0.0.1
 */
public class UtilityTest {
    /**
     * A test which asserts that getCharCount() produces
     * the expected output.
     */
    @Test
    public void getCharCountTest() {

        Utility u = new Utility();
        CharSequence testSeq = "HTHHHTHT";

        char charToCount = 'H';
        int getCharTest = u.getCharCount(testSeq, charToCount);
        assertEquals(getCharTest, 5);

        char charToCount2 = 'T';
        int getCharTest2 = u.getCharCount(testSeq, charToCount2);
        assertEquals(getCharTest2, 3);

    }
    /**
     * A test which asserts that repeatCharsTest() produces
     * the expected output.
     */

    @Test
    public void repeatCharsTest() {

        Utility u = new Utility();
        char c = 'H';
        int times = 10;

        String response = "HHHHHHHHHH";
        String repeatCharsTest = u.repeatChars(c, times);
        assertEquals(repeatCharsTest, response);

        char c2 = 'T';
        int times2 = 4;

        String response2 = "TTTT";
        String repeatCharsTest2 = u.repeatChars(c2, times2);
        assertEquals(repeatCharsTest2, response2);
    }

}
