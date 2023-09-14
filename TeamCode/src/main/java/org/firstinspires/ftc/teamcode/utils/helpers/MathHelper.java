package org.firstinspires.ftc.teamcode.utils.helpers;

public class MathHelper {
    public static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    public static double deadzone(double value, double deadzone) {
        return Math.abs(value) < deadzone ? 0 : value;
    }

    public static double radius(double x, double y) {
        return Math.sqrt(x * x + y * y);
    }
}
