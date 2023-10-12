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
        active = false;
    }

    public WaitAction tick() {
        chassis.telemetry.addData("Running", "WaitAction");
        chassis.telemetry.addData("WaitTime", waitTime);
        if (System.currentTimeMillis() - startTime >= waitTime) {
            active = false;
            return null;
        }
        return this;
    }
}
