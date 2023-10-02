package org.firstinspires.ftc.teamcode.opmodes.auto.actions;

import org.firstinspires.ftc.teamcode.opmodes.auto.AutoAction;

public class WaitAction extends AutoAction {
    public long startTime;
    public long waitTime;

    public WaitAction (long waitTime) {
        super(null);
        this.startTime = System.currentTimeMillis();
        this.waitTime = waitTime;
    }

    public WaitAction tick() {
        if (System.currentTimeMillis() - startTime >= waitTime) {
            return null;
        }
        return this;
    }
}
