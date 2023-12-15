package org.firstinspires.ftc.teamcode.opmodes.auto.test;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmodes.base.BaseAuto;
import org.firstinspires.ftc.teamcode.wrappers.Chassis;


@Autonomous(name="HangTest")
public class HangTest extends BaseAuto {
    public static boolean LOOP_HAS_RUN = false;

    @Override
    public void runSetup() {

    }

    public void createPoints() {

    }

    @Override
    public void runLoop() {
        if (Chassis.HAS_HANG && !LOOP_HAS_RUN) {
            chassis.hang1.setPower(-0.8);
            chassis.hang2.setPower(-0.8);
            chassis.logHelper.addData("hang", "up");
            chassis.logHelper.update();
            sleep(1000);
            chassis.hang1.setPower(0.05);
            chassis.hang2.setPower(0.05);
            sleep(10000);
            chassis.logHelper.addData("hang", "hover");
            chassis.logHelper.update();
            chassis.hang1.setPower(0.8);
            chassis.hang2.setPower(0.8);
            chassis.logHelper.addData("hang", "down");
            chassis.logHelper.update();
            LOOP_HAS_RUN = true;
        }
    }
}
