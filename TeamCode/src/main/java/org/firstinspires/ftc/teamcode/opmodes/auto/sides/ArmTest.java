package org.firstinspires.ftc.teamcode.opmodes.auto.sides;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmodes.base.BaseAuto;
import org.firstinspires.ftc.teamcode.wrappers.Chassis;

@Autonomous(name="ArmTest")
public class ArmTest extends BaseAuto {
    @Override
    public void runSetup() {

    }

    public void createPoints() {

    }

    @Override
    public void runLoop() {
        if (Chassis.HAS_ARM) {
            chassis.arm.setPower(-1);
            chassis.logHelper.addData("arm", "up");
            chassis.logHelper.update();
        }
    }
}
