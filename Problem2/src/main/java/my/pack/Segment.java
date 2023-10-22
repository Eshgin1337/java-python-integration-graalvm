package my.pack;

import org.graalvm.polyglot.*;

import java.io.IOException;
import java.nio.file.Paths;

public class Segment {
    Point p1;
    Point p2;
    double length;
    Segment(Point p1, Point p2) {
        try (Context context = Context.newBuilder().allowAllAccess(true).build()) {
            // Loading the Python script from the Python file
            Source source = Source.newBuilder("python", Paths.get("src/main/resources/Segment.py").toFile()).build();
            context.eval(source);

            // Accessing the Point class defined in Python
            Value pointClass = context.getBindings("python").getMember("Segment");

            try {
                Value pointInstance = pointClass.newInstance(p1, p2);
                // Calling methods on the Python Point instance
                Point newP1 = pointInstance.getMember("getP1").execute().as(Point.class);
                Point newP2 = pointInstance.getMember("getP2").execute().as(Point.class);
                double length = pointInstance.getMember("length").execute().asDouble();

                // Updating the x and y values from Python
                this.p1 = newP1;
                this.p2 = newP2;
                this.length = length;
            } catch (PolyglotException e) {
                throw new IllegalArgumentException("Points must differ!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    Point getP1() {
        return this.p1;
    }

    Point getP2() {
        return this.p2;
    }

    double length() {
        return this.length;
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Testing 'testDifferent()' method of 'SegmentTest' class:");
        Segment s1 = new Segment(new Point(0, 0), new Point(3, 4));
        System.out.println(s1.length());

        System.out.println("\n---------------------------------------\n");

        System.out.println("Testing 'testEqual()' method of 'SegmentTest' class:");
        Segment s2 = new Segment(new Point(1, 2), new Point(1, 2));
        System.out.println(s2.length());
    }
}

