package org.firstinspires.ftc.teamcode.opmodes.auto.sides;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmodes.base.BaseAuto;

@Autonomous(name="ArmTest")
public class ArmTest extends BaseAuto {
    @Override
    public void runSetup() {

    }

    public void createPoints() {

    }

    @Override
    public void runLoop() {
        chassis.arm.setPower(0.5);
    }
}
