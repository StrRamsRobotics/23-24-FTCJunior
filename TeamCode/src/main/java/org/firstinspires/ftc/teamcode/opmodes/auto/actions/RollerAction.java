package org.firstinspires.ftc.teamcode.opmodes.auto.actions;

import org.firstinspires.ftc.teamcode.opmodes.auto.AutoAction;
import org.firstinspires.ftc.teamcode.wrappers.Chassis;

public class RollerAction extends AutoAction {
    public double power;

    public RollerAction(Chassis chassis, double power) {
        super(chassis);
        this.power = power;
    }

    public RollerAction tick() {
        chassis.roller.setPower(power);
        return this;
    }
}
