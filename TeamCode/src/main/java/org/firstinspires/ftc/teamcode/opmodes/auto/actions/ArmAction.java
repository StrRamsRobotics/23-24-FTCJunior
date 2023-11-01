package org.firstinspires.ftc.teamcode.opmodes.auto.actions;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.opmodes.auto.classes.AutoAction;
import org.firstinspires.ftc.teamcode.generic.helpers.MathHelper;
import org.firstinspires.ftc.teamcode.wrappers.Chassis;

public class ArmAction extends AutoAction {
    public double angle;
    public double power;

    public ArmAction(Chassis chassis, double power, double angle) {
        super(chassis);
        chassis.arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.angle = MathHelper.clamp(angle, 0, Chassis.ARM_STRAIGHT_DEGREES + Chassis.ARM_TURN_DEGREES);
//        if (power < 0) chassis.arm.setDirection(DcMotor.Direction.REVERSE);
//        else chassis.arm.setDirection(DcMotor.Direction.FORWARD);
        this.power = power;
//        this.power = Math.abs(power);
    }

    public void tick() {
        chassis.logHelper.addData("Running", "ArmAction");
        if (!this.isInitialized) {
            chassis.arm.setTargetPosition((int) (this.angle * Chassis.CORE_HEX_TICKS_PER_REV / 360.0));
            this.isInitialized = true;
        }
        chassis.logHelper.addData("Arm Power", power);
        chassis.logHelper.addData("Arm Angle", angle);
        chassis.logHelper.addData("Arm Position", chassis.arm.getCurrentPosition());
        chassis.logHelper.addData("Arm Target", chassis.arm.getTargetPosition());

        chassis.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        if (chassis.arm.isBusy()) {
        if (chassis.arm.getTargetPosition() - chassis.arm.getCurrentPosition() > 0) {
            chassis.arm.setPower(this.power);
            chassis.logHelper.addData("Arm Busy", true);
        } else {
            chassis.logHelper.addData("Arm Busy", false);
            chassis.arm.setPower(0);
            chassis.arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            active = false;
        }
    }
}
