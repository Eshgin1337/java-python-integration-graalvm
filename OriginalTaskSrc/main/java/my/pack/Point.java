package my.pack;

import java.lang.Math;

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this(x, y, false);
    }

    static final double EPS = 0.0001;

    Point(double r, double a/* ngle */, boolean polar) {
        double x = polar ? r * Math.cos(a) : r;
        double y = polar ? r * Math.sin(a) : a;
        this.x = (int) x;
        this.y = (int) y;
        if (Math.abs(this.x - x) >= EPS || Math.abs(this.y - y) >= EPS) {
            throw new IllegalArgumentException("Likely not integers!");
        }
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
