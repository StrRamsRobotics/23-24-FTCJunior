package org.firstinspires.ftc.teamcode.opmodes.auto.actions;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.opmodes.auto.classes.AutoAction;
import org.firstinspires.ftc.teamcode.generic.helpers.MathHelper;
import org.firstinspires.ftc.teamcode.wrappers.Chassis;

public class LauncherMotorAction extends AutoAction {
    public double power;

    public LauncherMotorAction(Chassis chassis, double power) {
        super(chassis);
        this.power = power;
    }

    public void tick() {
        chassis.hang1.setPower(power);
        chassis.hang2.setPower(power);
        active = false;
    }
}
