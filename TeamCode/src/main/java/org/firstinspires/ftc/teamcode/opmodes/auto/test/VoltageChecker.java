package org.firstinspires.ftc.teamcode.opmodes.auto.test;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmodes.base.BaseAuto;
import org.firstinspires.ftc.teamcode.wrappers.Chassis;

@Autonomous(name="VoltageChecker")
public class VoltageChecker extends BaseAuto {
    @Override
    public void runSetup() {

    }

    public void createPoints() {

    }

    @Override
    public void runLoop() {
        chassis.logHelper.addData("Voltage", chassis.voltageSensor.getVoltage());
        chassis.logHelper.update();
    }
}
