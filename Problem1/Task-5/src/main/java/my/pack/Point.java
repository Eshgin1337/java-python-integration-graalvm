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

                // Update the x and y values from Python
                this.x = newX;
                this.y = newY;
                this.string = toStringResult;
            } catch (PolyglotException e) {
                if (e.getMessage().contains("ValueError")) {
                    throw new IllegalArgumentException("Likely not integers!");
                } else {
                    throw e; // Re-throw other exceptions
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public static void main(String[] args) throws IOException {
        Point p = new Point(Math.sqrt(2), Math.PI / 4,true);
        System.out.println(p.getY());
//        try (Context context = Context.newBuilder().allowAllAccess(true).build()) {
//            Value array = context.eval("python", "[1,2,42,4]");
//            int result = array.getArrayElement(2).asInt();
//            System.out.println(result);
//        }
    }
}

