package org.firstinspires.ftc.teamcode.opmodes.auto.actions;

import org.firstinspires.ftc.teamcode.opmodes.auto.classes.AutoAction;
import org.firstinspires.ftc.teamcode.wrappers.Chassis;

public class FlapAction extends AutoAction {
//    public double position;

    public double power;
//    public FlapAction(Chassis chassis, double position) {
//        super(chassis);
//        this.position = position;
//    }

    public FlapAction(Chassis chassis, double power) {
        super(chassis);
        this.power = power;
    }

    public void tick() {
        chassis.logHelper.addData("Running", "FlapAction");
        chassis.logHelper.addData("Direction", chassis.flap.getDirection());
//        chassis.logHelper.addData("Position", position);
        if (!this.isInitialized) {
            chassis.logHelper.addData("Initializing", "FlapAction");
//            chassis.flap.setPosition(position);
            chassis.flap.setPower(power);
            isInitialized = true;
        }
        chassis.flap.setPower(power);
        active = false;
    }
}
