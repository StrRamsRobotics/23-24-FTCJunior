package org.firstinspires.ftc.teamcode.opmodes.auto.actions;

import org.firstinspires.ftc.teamcode.opmodes.auto.classes.AutoAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.classes.AutoLine;
import org.firstinspires.ftc.teamcode.wrappers.Chassis;

public class TurnAction extends AutoAction {
    public double power;
    public AutoLine line;
    public double angle;
    public double turnedAngle;
    public double initialAngleIMU;
    public double turnedAngleIMU;
    public long startTime;

    public TurnAction(Chassis chassis, double power, double angle) {
        super(chassis);
        this.power = Math.signum(angle) * power;
        this.angle = Math.abs(angle);
        this.turnedAngle = 0;
        this.turnedAngleIMU = 0;
    }

    public void tick() {
        if (!this.isInitialized) {
            this.startTime = System.currentTimeMillis();
            this.initialAngleIMU = chassis.getAngle();
            this.isInitialized = true;
        }
        chassis.logHelper.addData("Running", "TurnAction");
        long currentTime = System.currentTimeMillis();
        chassis.logHelper.addData("Angle", angle);
        chassis.logHelper.addData("Turned Angle", turnedAngle);
        chassis.logHelper.addData("Turned Angle IMU", turnedAngleIMU);
        if (turnedAngle < angle) {
//        if (turnedAngleIMU < angle) {
            // positive angle should turn right
            // negative angle should turn left
            chassis.fr.setPower(power);
            chassis.fl.setPower(-power);
            if (!Chassis.TWO_WHEELED) {
                chassis.br.setPower(power);
                chassis.bl.setPower(-power);
            }
            chassis.logHelper.addData("Power FR", -power);
            chassis.logHelper.addData("Power FL", power);
            chassis.logHelper.addData("Power BR", -power);
            chassis.logHelper.addData("Power BL", power);
            turnedAngle = (currentTime - startTime) / 1000.0 * Chassis.TURN_ANGLE_PER_SECOND;
            //turnedAngleIMU = chassis.getAngle() - initialAngleIMU;
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
