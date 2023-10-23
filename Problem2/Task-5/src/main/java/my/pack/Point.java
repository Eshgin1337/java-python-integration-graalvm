package my.pack;

import org.graalvm.polyglot.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

public class Point {
    int x;
    int y;

    String string = new String("");
    static final double EPS = 0.0001;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Point(double r, double a, boolean polar) {
        try (Context context = Context.newBuilder().allowAllAccess(true).build()) {
            // Load the Python script from the Python file
            Source source = Source.newBuilder("python", Paths.get("src/main/resources/Point.py").toFile()).build();
            context.eval(source);

            // Access the Point class defined in Python
            Value pointClass = context.getBindings("python").getMember("Point");

            try {
                String polOrNot = "";
                if (polar)
                {
                    polOrNot = "True";
                } else {
                    polOrNot = "False";
                }
                Value pointInstance = pointClass.newInstance(r, a, polOrNot);
                // Call methods on the Python Point instance
                int newX = pointInstance.getMember("getX").execute().asInt();
                int newY = pointInstance.getMember("getY").execute().asInt();
                String toStringResult = pointInstance.getMember("toString").execute().asString();
                System.out.print("New x: ");
                System.out.println(newX);
                System.out.print("New y: ");
                System.out.println(newY);
                // Update the x and y values from Python
                this.x = newX;
                this.y = newY;
                this.string = toStringResult;
            } catch (PolyglotException e) {
                throw new IllegalArgumentException("Likely not integers!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public static void main(String[] args) throws IOException {
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

