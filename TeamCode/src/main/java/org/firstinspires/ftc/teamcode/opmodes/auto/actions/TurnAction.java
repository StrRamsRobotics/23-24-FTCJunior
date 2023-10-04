package org.firstinspires.ftc.teamcode.opmodes.auto.actions;

import org.firstinspires.ftc.teamcode.opmodes.auto.classes.AutoAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.classes.AutoLine;
import org.firstinspires.ftc.teamcode.wrappers.Chassis;

public class TurnAction extends AutoAction {
    public double power;
    public AutoLine line;
    public double angle;
    public double arcLength;
    public long startTime;

    public TurnAction(Chassis chassis, double power, AutoLine line) {
        super(chassis);
        this.power = power;
        this.angle = line.getAngle();
        this.arcLength = Math.toDegrees(angle * Chassis.ROBOT_WIDTH / 2);
        this.startTime = System.currentTimeMillis();
    }

    public TurnAction(Chassis chassis, double power, double angle) {
        super(chassis);
        this.power = power;
        this.angle = angle;
        this.arcLength = Math.toDegrees(angle * Chassis.ROBOT_WIDTH / 2);
        this.startTime = System.currentTimeMillis();
    }

    public TurnAction tick() {
        chassis.telemetry.addData("Running", "TurnAction");
        chassis.telemetry.update();
        long currentTime = System.currentTimeMillis();
        double distance = (currentTime - startTime) * Chassis.DISTANCE_PER_SECOND / 1000.0;
        if (distance < arcLength) {
            chassis.fr.setPower(power);
            chassis.fl.setPower(power);
            if (!Chassis.TWO_WHEELED) {
                chassis.br.setPower(power);
                chassis.bl.setPower(power);
            }
            return this;
        } else {
            chassis.fr.setPower(0);
            chassis.fl.setPower(0);
            if (!Chassis.TWO_WHEELED) {
                chassis.br.setPower(0);
                chassis.bl.setPower(0);
            }
            return null;
        }
    }
}
