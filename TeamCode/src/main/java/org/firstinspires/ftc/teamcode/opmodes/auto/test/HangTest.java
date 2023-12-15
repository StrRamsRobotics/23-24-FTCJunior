package org.firstinspires.ftc.teamcode.opmodes.auto.test;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmodes.base.BaseAuto;
import org.firstinspires.ftc.teamcode.wrappers.Chassis;

@Autonomous(name="HangTest")
public class HangTest extends BaseAuto {
    @Override
    public void runSetup() {

    }

    public void createPoints() {

    }

    @Override
    public void runLoop() {
        if (Chassis.HAS_HANG) {
            chassis.hang1.setPower(-1);
            chassis.hang2.setPower(-1);
            chassis.logHelper.addData("hang", "up");
            chassis.logHelper.update();
        }
    }
}
