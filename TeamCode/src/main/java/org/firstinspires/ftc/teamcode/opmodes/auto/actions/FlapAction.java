package org.firstinspires.ftc.teamcode.opmodes.auto.actions;

import org.firstinspires.ftc.teamcode.opmodes.auto.AutoAction;
import org.firstinspires.ftc.teamcode.wrappers.Chassis;

public class FlapAction extends AutoAction {
    public double position;

    public FlapAction(Chassis chassis, double position) {
        super(chassis);
        this.position = position;
    }

    public FlapAction tick() {
        chassis.flap.setPosition(position);
        return null;
    }
}
