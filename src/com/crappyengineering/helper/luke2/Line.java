package com.crappyengineering.helper.luke2;

public class Line {

    private Point x, y;
    private double slope;

    private Line(Point x, Point y) {
        this.x = x;
        this.y = y;
        slope = ((y.y - x.y)/(y.x - x.x));
    }

    public double getSlope() {
        return slope;
    }

    public static Line of(String...points) {
        return new Line(Point.of(points[0]), Point.of(points[1]));
    }
}
