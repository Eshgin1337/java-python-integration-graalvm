package my.pack;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SegmentTest {
    @Test
    public void testDifferent() {
        Segment s = new Segment(new Point(0, 0), new Point(3, 4));
        assertEquals(5.0, s.length(), Point.EPS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEqual() {
        new Segment(new Point(1, 2), new Point(1, 2));
    }
}
