package org.firstinspires.ftc.teamcode.opmodes.auto.actions;

import org.firstinspires.ftc.teamcode.opmodes.auto.classes.AutoAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.classes.AutoLine;
import org.firstinspires.ftc.teamcode.wrappers.Chassis;

public class TurnAction extends AutoAction {
    public double power;
    public AutoLine line;
    public double angle;
    public double turnedAngle;
//    public double arcLength;
    public long startTime;

//    public TurnAction(Chassis chassis, double power, AutoLine line) {
//        super(chassis);
////        this.power = power * Math.signum(line.getAngle());
//        this.power = Math.signum(line.getAngle()) * power;
//        this.angle = Math.abs(line.getAngle());
//        this.turnedAngle = 0;
////        this.arcLength = Math.toRadians(angle) * Chassis.ROBOT_WIDTH / 2;
//        this.startTime = System.currentTimeMillis();
//    }

    public TurnAction(Chassis chassis, double power, double angle) {
        super(chassis);
        this.power = Math.signum(angle) * power;
        this.angle = Math.abs(angle);
        this.turnedAngle = 0;
//        this.arcLength = Math.toRadians(angle) * Chassis.ROBOT_WIDTH / 2;
    }

    public void tick() {
        if (!this.isInitialized) {
            this.startTime = System.currentTimeMillis();
            this.isInitialized = true;
        }
        chassis.logHelper.addData("Running", "TurnAction");
        long currentTime = System.currentTimeMillis();
//        double distance = (currentTime - startTime) / 1000.0 * Chassis.MOVE_DISTANCE_PER_SECOND;
//        double distance = (currentTime - startTime) / 1000.0 * Chassis.TURN_DISTANCE_PER_SECOND;
        chassis.logHelper.addData("Angle", angle);
        chassis.logHelper.addData("Turned Angle", turnedAngle);
//        chassis.logHelper.addData("Distance", distance);
//        chassis.logHelper.addData("ArcLength", arcLength);
//        if (distance < arcLength) {
        if (turnedAngle < angle) {
            chassis.fr.setPower(power);
            chassis.fl.setPower(-power);
            if (!Chassis.TWO_WHEELED) {
                chassis.br.setPower(power);
                chassis.bl.setPower(-power);
            }
            chassis.logHelper.addData("Power FR", power);
            chassis.logHelper.addData("Power FL", -power);
            chassis.logHelper.addData("Power BR", power);
            chassis.logHelper.addData("Power BL", -power);
            turnedAngle += (currentTime - startTime) / 1000.0 * Chassis.TURN_ANGLE_PER_SECOND;
        } else {
            chassis.fr.setPower(0);
            chassis.fl.setPower(0);
            if (!Chassis.TWO_WHEELED) {
                chassis.br.setPower(0);
                chassis.bl.setPower(0);
            }
            active = false;
        }
    }
}
