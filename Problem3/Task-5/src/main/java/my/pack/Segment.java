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
            Value segmentClass = context.getBindings("python").getMember("Segment");
            Value pythonConverter = context.getBindings("python").getMember("ToPython");

            try {
                Value segmentInstance = segmentClass.newInstance(p1.getX(), p1.getY(), p2.getX(), p2.getY());

                double length = segmentInstance.getMember("length").execute().asDouble();
                this.length = length;
                // Calling methods on the Python Point instance

            } catch (PolyglotException e) {
                e.printStackTrace();
//                throw new IllegalArgumentException("Points must differ!");
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

