package org.firstinspires.ftc.teamcode.opmodes.auto.actions;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.opmodes.auto.classes.AutoAction;
import org.firstinspires.ftc.teamcode.utils.helpers.MathHelper;
import org.firstinspires.ftc.teamcode.wrappers.Chassis;

public class ArmAction extends AutoAction {
    public double angle;
    public double power;

    public ArmAction(Chassis chassis, double power, double angle) {
        super(chassis);
        this.angle = MathHelper.clamp(angle, 0, 120);
        this.power = power;
        chassis.arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        chassis.arm.setTargetPosition((int) (this.angle * Chassis.CORE_HEX_TICKS_PER_REV / 360.0));
        active = true;
    }

    public ArmAction tick() {
        chassis.telemetry.addData("Running", "ArmAction");
        chassis.telemetry.addData("Angle", angle);

        chassis.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        chassis.arm.setPower(this.power);
        if (chassis.arm.isBusy()) {
            return this;
        } else {
            chassis.pivot.setPower(0);
            chassis.pivot.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            active = false;
            return null;
        }
    }
}
