package org.firstinspires.ftc.teamcode.opmodes.auto.classes;

import org.firstinspires.ftc.teamcode.generic.classes.Point;
import org.firstinspires.ftc.teamcode.generic.helpers.MathHelper;

import java.util.ArrayList;

public class AutoLine {
    public AutoPoint point1;
    public AutoPoint point2;
    public double slope;
    public double yIntercept;

    public AutoLine(AutoPoint point1, AutoPoint point2) {
        this.point1 = point1;
        this.point2 = point2;
        this.slope = (point2.y - point1.y) / (point2.x - point1.x);
        this.yIntercept = point1.y - (slope * point1.x);
    }

//    public double getAngle() {
//        double heading1 = point1.heading;
//        if (point1.isReverse) heading1 = MathHelper.toHeading(point1.heading - 180);
//        double heading2 = point2.heading;
//        return MathHelper.toHeading((point2.isReverse ? -getHeading() : getHeading()) - heading1);
//    }

    public double getHeading() {
        double heading = Math.toDegrees(-(Math.atan2(point2.y - point1.y, point2.x - point1.x) - Math.PI / 2));
        return point1.isReverse ? MathHelper.toHeading(heading - 180) : MathHelper.toHeading(heading);
    }

    public AutoPoint getNextPoint(AutoPoint point, double distance) {
        if (slope == Double.POSITIVE_INFINITY || slope == Double.NEGATIVE_INFINITY) {
            return new AutoPoint(new Point(point.x, point.y + distance), new ArrayList<>());
        }
        double x = point.x + Math.sqrt(Math.pow(distance, 2) / (1 + Math.pow(slope, 2)));
        double y = slope * x + yIntercept;
        return new AutoPoint(new Point(x, y), new ArrayList<>());
    }
}
