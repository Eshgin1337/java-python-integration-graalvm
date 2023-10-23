package my.pack;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
@RunWith(value = BlockJUnit4ClassRunner.class)
public class SegmentTest {
    @Test
    public void testDifferent() throws IOException {
        Segment s = new Segment(new Point(0, 0), new Point(3, 4));
        assertEquals(5.0, s.length(), Point.EPS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEqual() throws IOException {
        new Segment(new Point(1, 2), new Point(1, 2));
    }
}
