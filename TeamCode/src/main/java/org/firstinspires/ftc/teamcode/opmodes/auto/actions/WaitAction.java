package org.firstinspires.ftc.teamcode.opmodes.auto.actions;

import org.firstinspires.ftc.teamcode.opmodes.auto.classes.AutoAction;
import org.firstinspires.ftc.teamcode.wrappers.Chassis;

public class WaitAction extends AutoAction {
    public long startTime;
    public long waitTime;
    public boolean isWaitInitalized = false;

    public WaitAction (Chassis chassis, long waitTime) {
        super(chassis);
        this.waitTime = waitTime;
        this.isWaitInitalized = false;
    }

    public void tick() {
        if (!this.isWaitInitalized) {
            this.startTime = System.currentTimeMillis();
            this.isWaitInitalized = true;
        }
        chassis.logHelper.addData("Running", "WaitAction");
        chassis.logHelper.addData("WaitTime", waitTime);
        if (System.currentTimeMillis() - startTime >= waitTime) {
            active = false;
        }
    }
}
