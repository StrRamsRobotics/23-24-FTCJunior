package org.firstinspires.ftc.teamcode.utils.classes;

import java.util.ArrayList;

public class Curve {
    public ArrayList<Point> points;

    public Curve(ArrayList<Point> points) {
        this.points = points;
    }

    public Curve(Curve c) {
        this.points = c.points;
    }

    public Curve() {
        this.points.add(new Point());
    }

    public void addPoint(Point p) {
        points.add(p);
    }

    public void addPoint(double x, double y) {
        points.add(new Point(x, y));
    }

    public void addPoint(double x) {
        points.add(new Point(x, getY(x)));
    }

    public void removePoint(int index) {
        points.remove(index);
    }

    public void removePoint(Point p) {
        points.remove(p);
    }

    public void removePoint(double x, double y) {
        points.remove(new Point(x, y));
    }

    public void removePoint(double x) {
        points.remove(new Point(x, getY(x)));
    }

    public void setPoint(int index, Point p) {
        points.set(index, p);
    }

    public void setPoint(int index, double x, double y) {
        points.set(index, new Point(x, y));
    }

    public void setPoint(int index, double x) {
        points.set(index, new Point(x, getY(x)));
    }

    public Point getPoint(int index) {
        return points.get(index);
    }

    public double getX(int index) {
        return points.get(index).x;
    }

    public double getY(int index) {
        return points.get(index).y;
    }

    public void setX(int index, double x) {
        points.get(index).x = x;
    }

    public void setY(int index, double y) {
        points.get(index).y = y;
    }

    public double getY(double x) {
        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).x == x) {
                return points.get(i).y;
            }
            else if (i <= points.size() - 2 && points.get(i).x < x && x < points.get(i + 1).x) {
                double slope = (points.get(i + 1).y - points.get(i).y) / (points.get(i + 1).x - points.get(i).x);
                return slope * (x - points.get(i).x) + points.get(i).y;
            }
            else if ((i == 0 && points.get(i).x > x) || (i == points.size() - 1 && points.get(i).x < x)) {
                return 0;
            }
        }
        return 0;
    }
}
