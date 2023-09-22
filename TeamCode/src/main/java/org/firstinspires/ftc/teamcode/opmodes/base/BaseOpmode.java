package org.firstinspires.ftc.teamcode.opmodes.base;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.wrappers.Chassis;

public abstract class BaseOpmode extends LinearOpMode {
    public Chassis chassis;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initializing...");
        chassis = new Chassis(hardwareMap);
        runSetup();
        waitForStart();
        telemetry.addData("Status", "Initialized!");
        while (opModeIsActive()) {
            runLoop();
        }
    }

    public abstract void runSetup();
    public abstract void runLoop();
}
