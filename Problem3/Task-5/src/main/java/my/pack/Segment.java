package my.pack;

import org.graalvm.polyglot.*;
import java.io.IOException;
import java.nio.file.Paths;
//import org.python.core.PyInteger;
//import org.python.core.PyObject;

public class Segment {
    private Point p1;
    private Point p2;
    private double length;

    private Context context;
    private void initializeContext() {
        try {
            context = Context.create();
            context.eval(Source.newBuilder("python", Paths.get("src/main/resources/Segment.py").toFile()).build());
        } catch (IOException e) {
            throw new IllegalArgumentException("An error occurred while initializing the Python script.");
        }
    }


    private void initializePythonPoint(Point p1, Point p2) {
        try {
//            PyObject p1Dict = new PyObject();
//            p1Dict.__setitem__("getX", new PyInteger(p1.getX()));
//            p1Dict.__setitem__("getY", new PyInteger(p1.getY()));
//
//            PyObject p2Dict = new PyObject();
//            p2Dict.__setitem__("getX", new PyInteger(p2.getX()));
//            p2Dict.__setitem__("getY", new PyInteger(p2.getY()));
            String script = String.format("segment = Segment(%s, %s)\n", p1, p2);
            script += "p1 = segment.getP1()\n";
            script += "p2 = segment.getP2()\n";
            script += "length = segment.length()\n";

            context.eval("python", script);
        } catch (Exception e) {
            throw new IllegalArgumentException("Points must differ!");
        }
    }
    public Segment(Point p1, Point p2) {
        initializeContext();
        initializePythonPoint(p1, p2);
    }

    Point getP1() {
        try {
            p1 = context.eval("python", "p1").as(Point.class);
            return p1;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("An error occurred while getting X from Python.");
        }
    }

    Point getP2() {
        try {
            p2 = context.eval("python", "p1").as(Point.class);
            return p2;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("An error occurred while getting X from Python.");
        }
    }

    double length() {
        try {
            length = context.eval("python", "length").asDouble();
            return length;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("An error occurred while getting X from Python.");
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Testing 'testDifferent()' method of 'SegmentTest' class:");
        Segment s1 = new Segment(new Point(0, 0), new Point(3, 4));
        System.out.println(s1.length());

        System.out.println("\n---------------------------------------\n");

        System.out.println("Testing 'testEqual()' method of 'SegmentTest' class: (Error expected)");
        Segment s2 = new Segment(new Point(1, 2), new Point(1, 2));
        System.out.println(s2.length());
    }
}
