import org.junit.Test;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void multiply() {
        assertEquals(Main.multiply(4, 8), 4 * 8);
    }

    @Test
    public void multiplyTwoNegativ() {
        assertEquals(Main.multiply(-2, -4), -2 * -4);
    }
    @Test
    public void multiplyNegativFirst() {
        assertEquals(Main.multiply(-2, 2), -2 * 2);
    }
    @Test
    public void multiplynNegativSecond() {
        assertEquals(Main.multiply(6, -3), 6 * -3);
    }
    @Test
    public void multiplyTwoZero() {
        assertEquals(Main.multiply(0, 0), 0 * 0);
    }
    @Test
    public void multiplyZeroFirst() {
        assertEquals(Main.multiply(0, -8), 0 * -8);
    }
    @Test
    public void multiplyZeroSecond() {
        assertEquals(Main.multiply(1, 0), 1 * 0);
    }
    @Test
    public void main() {

    }
}