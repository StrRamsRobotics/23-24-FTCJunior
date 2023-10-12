package org.firstinspires.ftc.teamcode.opmodes.auto.actions;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.opmodes.auto.classes.AutoAction;
import org.firstinspires.ftc.teamcode.wrappers.Chassis;

public class PivotAction extends AutoAction {
    public double angle;
    public double power;

    public PivotAction(Chassis chassis, double power, double angle) {
        super(chassis);
        this.angle = angle;
        this.power = power;

        chassis.pivot.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        chassis.pivot.setTargetPosition((int) (this.angle * Chassis.CORE_HEX_TICKS_PER_REV / 360.0));
    }

    public void tick() {
        chassis.logHelper.addData("Running", "PivotAction");
        chassis.logHelper.addData("Angle", angle);
        chassis.pivot.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        chassis.pivot.setPower(this.power);
        if (chassis.pivot.isBusy()) {
        } else {
            chassis.pivot.setPower(0);
            chassis.pivot.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            active = false;
        }
    }
}
