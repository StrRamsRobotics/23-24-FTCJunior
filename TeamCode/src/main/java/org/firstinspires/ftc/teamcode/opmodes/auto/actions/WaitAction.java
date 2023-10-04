package org.firstinspires.ftc.teamcode.opmodes.auto.actions;

import org.firstinspires.ftc.teamcode.opmodes.auto.classes.AutoAction;

public class WaitAction extends AutoAction {
    public long startTime;
    public long waitTime;

    public WaitAction (long waitTime) {
        super(null);
        this.startTime = System.currentTimeMillis();
        this.waitTime = waitTime;
    }

    public WaitAction tick() {
        chassis.telemetry.addData("Running", "WaitAction");
        chassis.telemetry.update();
        if (System.currentTimeMillis() - startTime >= waitTime) {
            return null;
        }
        return this;
    }
}
