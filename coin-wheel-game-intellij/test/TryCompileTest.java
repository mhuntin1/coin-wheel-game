import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TryCompileTest {

    @Test
    public void getInt() {
        int n = 3;
        TryCompile tc = new TryCompile(n);
        assertEquals(3, tc.getInt());
    }

    @Test
    public void printInt() {
        Integer n = 3;
        TryCompile tc = new TryCompile(n);
        assertTrue(n.toString().equals(tc.getString()));
    }
}