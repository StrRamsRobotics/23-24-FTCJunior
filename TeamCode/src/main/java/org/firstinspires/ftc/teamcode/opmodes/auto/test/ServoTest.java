package org.firstinspires.ftc.teamcode.opmodes.auto.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.opmodes.base.BaseAuto;

@Autonomous(name="ServoTest")
public class ServoTest extends BaseAuto {
    @Override
    public void runSetup() {
        testServoPower();
        testServoRotation();
    }

    public void createPoints() {

    }

    @Override
    public void runLoop() {
        chassis.flap.setPosition(0.5);
    }

    public void testServoPower() {
        for (double position = 0; position <= 1; position += 0.1) {
            chassis.flap.setPosition(position);
            sleep(1000); // wait for 1 second to observe the effect
        }
    }

    public void testServoRotation() {
        for (double position = 0; position <= 1; position += 0.1) {
            chassis.flap.setPosition(position);
            sleep(1000); // wait for 1 second to observe the effect
        }
    }
}