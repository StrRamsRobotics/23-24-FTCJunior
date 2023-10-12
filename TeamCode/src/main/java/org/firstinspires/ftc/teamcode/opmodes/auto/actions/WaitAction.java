package org.firstinspires.ftc.teamcode.opmodes.auto.actions;

import org.firstinspires.ftc.teamcode.opmodes.auto.classes.AutoAction;
import org.firstinspires.ftc.teamcode.wrappers.Chassis;

public class WaitAction extends AutoAction {
    public long startTime;
    public long waitTime;

    public WaitAction (Chassis chassis, long waitTime) {
        super(chassis);
        this.startTime = System.currentTimeMillis();
        this.waitTime = waitTime;
    }

    public void tick() {
        chassis.logHelper.addData("Running", "WaitAction");
        chassis.logHelper.addData("WaitTime", waitTime);
        if (System.currentTimeMillis() - startTime >= waitTime) {
            active = false;
        }
    }
}
