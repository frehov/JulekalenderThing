package com.crappyengineering.helper.luke2;

import static java.lang.Double.parseDouble;

public class Point{

    double x,y;

    private Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    static Point of(String tuple) {
        String[] values = tuple.substring(1, tuple.length()-1).split(",");
        return new Point(parseDouble(values[0]), parseDouble(values[1]));
    }
}
