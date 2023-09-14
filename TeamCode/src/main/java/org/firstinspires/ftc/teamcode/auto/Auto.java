package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="autonomous")
public class Auto extends BaseAuto{

    @Override
    public void runOpMode() throws InterruptedException {
        move(10, 10, 10, 10);
    }
}