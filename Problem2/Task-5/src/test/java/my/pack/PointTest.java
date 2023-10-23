package my.pack;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PointTest {
    @Test
    public void testIntegers() {
        Point p = new Point(1, 2, false);
        assertEquals(1, p.getX());
        assertEquals(2, p.getY());
    }

    @Test
    public void testDoublesWorking() {
        Point p = new Point(Math.sqrt(2), Math.PI / 4, true);
        assertEquals(1, p.getX());
        assertEquals(1, p.getY());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDoublesNotWorking() {
        new Point(Math.sqrt(2), Math.PI / 2, true);
    }

    @Test
    public void testToString() {
        Point p = new Point(1, 2, false);
        assertEquals("(1, 2)", p.toString());
    }
}
