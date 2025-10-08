package tp4;

import org.junit.Test;
import static org.junit.Assert.*;

public class CalculetteTest {

    @Test
    public void testOperationsElementaires() {
        Calculette c = new Calculette();
        c.push(1.0);
        c.push(2.5);
        assertEquals(3.5, c.add(), 1e-9);
        c.push(4.0);
        assertEquals(14.0, c.mul(), 1e-9); // 3.5 * 4.0
    }

    @Test
    public void testRPNExample() {
        Calculette c = new Calculette();
        double res = c.evaluateRPN("1.0 3 + 2 3.2 / +");
        assertEquals(4.625, res, 1e-9); // (1.0+3) + (2/3.2)
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTokenInvalide() {
        Calculette c = new Calculette();
        c.evaluateRPN("1 2 &");
    }
}
