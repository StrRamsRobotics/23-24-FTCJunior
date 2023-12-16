package org.firstinspires.ftc.teamcode.opmodes.auto.actions;

import org.firstinspires.ftc.teamcode.opmodes.auto.classes.AutoAction;
import org.firstinspires.ftc.teamcode.wrappers.Chassis;

public class WaitAction extends AutoAction {
    public long startTime;
    public long waitTime;
    public boolean isInitialized = false;
    public double time = 0;

    public WaitAction (Chassis chassis, long waitTime) {
        super(chassis);
        this.waitTime = waitTime;
    }

    public void tick() {
        time += Chassis.OSCILLATING_TIME_STEP;
        if (time > 2 * Math.PI) time -= 2 * Math.PI;
        chassis.logHelper.addData("Running", "WaitAction");
        if (!this.isInitialized) {
            this.startTime = System.currentTimeMillis();
            this.isInitialized = true;
        }
        chassis.logHelper.addData("StartTime", startTime);
        chassis.logHelper.addData("WaitTime", waitTime);
        chassis.logHelper.addData("CurrentTime", System.currentTimeMillis());
        chassis.logHelper.addData("Difference", System.currentTimeMillis() - startTime);
        oscillate();
        if (System.currentTimeMillis() - startTime >= waitTime) {
            active = false;
            chassis.fl.setPower(0);
            chassis.fr.setPower(0);
            if (!Chassis.TWO_WHEELED) {
                chassis.bl.setPower(0);
                chassis.br.setPower(0);
            }
        }
    }

    public void oscillate() {
        chassis.fr.setPower(Chassis.OSCILLATING_POWER * Math.cos(time));
        chassis.fl.setPower(Chassis.OSCILLATING_POWER * Math.cos(time));
        if (!Chassis.TWO_WHEELED) {
            chassis.br.setPower(Chassis.OSCILLATING_POWER * Math.cos(time));
            chassis.bl.setPower(Chassis.OSCILLATING_POWER * Math.cos(time));
        }
        chassis.logHelper.addData("Time", time);
    }
}
