package org.firstinspires.ftc.teamcode.opmodes.auto.test;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmodes.base.BaseAuto;
import org.firstinspires.ftc.teamcode.wrappers.Chassis;


@Autonomous(name="HangTest")
public class HangTest extends BaseAuto {
    public boolean loopHasRun = false;
    public double time = 0;

    @Override
    public void runSetup() {

    }

    public void createPoints() {

    }

    @Override
    public void runLoop() {
        time += Chassis.OSCILLATING_TIME_STEP;
        if (time > 2 * Math.PI) time -= 2 * Math.PI;

        if (Chassis.HAS_HANG && !loopHasRun) {
            chassis.hang1.setPower(-0.8);
            chassis.hang2.setPower(-0.8);
            chassis.logHelper.addData("hang", "up");
            chassis.logHelper.update();
            sleep(1000);
            chassis.hang1.setPower(0.05 * Math.cos(time));
            chassis.hang2.setPower(0.05 * Math.cos(time));
            sleep(10000);
            chassis.logHelper.addData("hang", "hover");
            chassis.logHelper.update();
            chassis.hang1.setPower(0.8);
            chassis.hang2.setPower(0.8);
            chassis.logHelper.addData("hang", "down");
            chassis.logHelper.update();
            loopHasRun = true;
        }
    }
}
