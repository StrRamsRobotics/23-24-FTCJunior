package org.firstinspires.ftc.teamcode.opmodes.auto;

import org.firstinspires.ftc.teamcode.utils.classes.Point;
import org.firstinspires.ftc.teamcode.utils.helpers.MathHelper;

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

    public double getAngle() {
        double heading1 = 0, heading2 = 0;
        if (point1.isReverse) heading1 = MathHelper.toHeading(point1.heading - 180);
        if (point2.isReverse) heading2 = MathHelper.toHeading(point2.heading - 180);
        return MathHelper.toHeading(heading2 - heading1);
    }

    public double getHeading() {
        return Math.toDegrees(-Math.atan2(point2.y - point1.y, point2.x - point1.x) + Math.PI / 2);
    }

    public AutoPoint getNextPoint(AutoPoint point, double distance) {
        double x = point.x + Math.sqrt(Math.pow(distance, 2) / (1 + Math.pow(slope, 2)));
        double y = slope * x + yIntercept;
        return new AutoPoint(new Point(x, y), new ArrayList<>());
    }
}
