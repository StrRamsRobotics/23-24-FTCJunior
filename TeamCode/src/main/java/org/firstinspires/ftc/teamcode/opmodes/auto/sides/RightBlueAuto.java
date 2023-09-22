package org.firstinspires.ftc.teamcode.opmodes.auto.sides;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmodes.auto.MainAuto;
import org.firstinspires.ftc.teamcode.opmodes.base.BaseAuto;

@Autonomous(name="rightBlueAuto")
public class RightBlueAuto extends BaseAuto {
    public MainAuto mainAuto;
    @Override
    public void runSetup() {
        mainAuto = new MainAuto();
        mainAuto.runSetup(MainAuto.RIGHT_BLUE, chassis);
    }

    @Override
    public void runLoop() {
        mainAuto.runLoop();
    }
}