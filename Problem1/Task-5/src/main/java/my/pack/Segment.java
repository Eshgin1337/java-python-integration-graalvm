//package my.pack;
//
//import java.lang.Math;
//
//class Segment {
//    Point p1;
//    Point p2;
//
//    Segment(Point p1, Point p2) {
//        if (p1.getX() == p2.getX() && p1.getY() == p2.getY()) {
//            throw new IllegalArgumentException("Points must differ!");
//        }
//        this.p1 = p1;
//        this.p2 = p2;
//    }
//
//    Point getP1() {
//        return p1;
//    }
//
//    Point getP2() {
//        return p2;
//    }
//
//    double length() {
//        return Math.sqrt(Math.pow((p1.getX() - p2.getX()), 2) + Math.pow((p1.getY() - p2.getY()), 2));
//    }
//
//    public String toString() {
//        return "(" + p1 + ", " + p2 + ")";
//    }
//}
