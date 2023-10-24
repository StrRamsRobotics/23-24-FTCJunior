package org.firstinspires.ftc.teamcode.opmodes.auto.actions;

import org.firstinspires.ftc.teamcode.opmodes.auto.classes.AutoAction;
import org.firstinspires.ftc.teamcode.wrappers.Chassis;

public class FlapAction extends AutoAction {
    public double p;

    public FlapAction(Chassis chassis, double p) {
        super(chassis);
        this.p = p;
    }

    public void tick() {
        chassis.logHelper.addData("Running", "FlapAction");
        chassis.logHelper.addData("Direction", chassis.flap.getDirection());
        chassis.logHelper.addData("P", p);
        if (!this.isInitialized) {
            if (Chassis.IS_FLAP_CR) {
                chassis.flapCR.setPower(p);
            }
            else {
                chassis.flap.setPosition(p);
            }
            isInitialized = true;
        }
        active = false;
    }
}
