package my.pack;

import org.graalvm.polyglot.*;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Point {
    private int x;
    private int y;
    private String string;

    private Context context;

    public Point(int x, int y) {
        initializeContext();
        initializePythonPoint(x,y,false);
    }

    public Point(double r, double a, boolean polar) {
        initializeContext();
        initializePythonPoint(r, a, polar);
    }

    private void initializeContext() {
        try {
            context = Context.create();
            context.eval(Source.newBuilder("python", Paths.get("src/main/resources/Point.py").toFile()).build());
        } catch (IOException e) {
            throw new IllegalArgumentException("An error occurred while initializing the Python script.");
        }
    }


    private void initializePythonPoint(double r, double a, boolean polar) {
        try {
            String polarStr = polar ? "True" : "False";
            String script = String.format("point = Point(%f, %f, %s)\n", r, a, polarStr);
            script += "x = point.getX()\n";
            script += "y = point.getY()\n";
            script += "s = point.toString()\n";

            context.eval("python", script);
        } catch (Exception e) {
            throw new IllegalArgumentException("Likely not integers!");
        }
    }


    public int getX() {
        try {
            x = context.eval("python", "x").asInt();
            return x;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("An error occurred while getting X from Python.");
        }
    }

    public int getY() {
        try {
            y = context.eval("python", "y").asInt();
            return y;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("An error occurred while getting Y from Python.");
        }
    }

    public String toString() {
        try {
            string = context.eval("python", "s").asString();
            return string;
        } catch (Exception e) {
            throw new IllegalArgumentException("An error occurred while getting the string representation from Python.");
        }
    }

    public static void main(String[] args) {
        System.out.println("Testing 'testIntegers()' method of 'PointTest' class:");
        Point p1 = new Point(1, 2);
        System.out.println("Expected: 1 -- Result: " + p1.getX());
        System.out.println("Expected: 2 -- Result: " + p1.getY());

        System.out.println("\n---------------------------------------\n");

        System.out.println("Testing 'testDoublesWorking()' method of 'PointTest' class:");
        Point p2 = new Point(Math.sqrt(2), Math.PI / 4, true);
        System.out.println("Expected: 1 -- Result: " + p2.getX());
        System.out.println("Expected: 1 -- Result: " + p2.getY());

        System.out.println("\n---------------------------------------\n");

        System.out.println("Testing 'testToString() ' method of 'PointTest' class:");
        Point p3 = new Point(1, 2);
        System.out.println("Expected: (1, 2) -- Result: " + p3.toString());

        System.out.println("\n---------------------------------------\n");

        System.out.println("Testing 'testDoublesNotWorking()' method of 'PointTest' class (Error expected):");
        Point p4 = new Point(Math.sqrt(2), Math.PI / 2, true);

        System.out.println("\n---------------------------------------\n");
    }
}
