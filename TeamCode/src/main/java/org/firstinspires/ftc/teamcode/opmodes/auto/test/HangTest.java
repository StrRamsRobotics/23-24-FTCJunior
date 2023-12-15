package org.firstinspires.ftc.teamcode.opmodes.auto.test;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmodes.base.BaseAuto;
import org.firstinspires.ftc.teamcode.wrappers.Chassis;


@Autonomous(name="HangTest")
public class HangTest extends BaseAuto {
    public static double FIRST_SECTION_TIME = 450;
    public static double SECOND_SECTION_TIME = 5000;
    public static double THIRD_SECTION_TIME = 10000;
    public boolean firstSection = true;
    public boolean isFirstSectionRan = false;
    public boolean secondSection = false;
    public boolean isSecondSectionRan = false;
    public boolean thirdSection = false;
    public boolean isThirdSectionRan = false;
    public double startTime = 0;
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
        if (Chassis.HAS_HANG) {
            if (firstSection) {
                if (!isFirstSectionRan) {
                    startTime = System.currentTimeMillis();
                    chassis.hang1.setPower(-0.8);
                    chassis.hang2.setPower(-0.8);
                    chassis.logHelper.addData("hang", "up");
                    chassis.logHelper.update();
                    isFirstSectionRan = true;
                }
                if (System.currentTimeMillis() - startTime >= FIRST_SECTION_TIME) {
                    firstSection = false;
                    secondSection = true;
                }
            }
            if (secondSection) {
                if (!isSecondSectionRan) {
                    startTime = System.currentTimeMillis();
                    isSecondSectionRan = true;
                }
                chassis.hang1.setPower(0.1 * Math.cos(time));
                chassis.hang2.setPower(0.1 * Math.cos(time));
                if (System.currentTimeMillis() - startTime >= SECOND_SECTION_TIME) {
                    secondSection = false;
                    thirdSection = true;
                }
            }

            if (thirdSection) {
                if (!isThirdSectionRan) {
                    startTime = System.currentTimeMillis();
                    chassis.hang1.setPower(1);
                    chassis.hang2.setPower(1);
                    chassis.logHelper.addData("hang", "hold");
                    chassis.logHelper.update();
                    isThirdSectionRan = true;
                }
                if (System.currentTimeMillis() - startTime >= THIRD_SECTION_TIME) {
                    firstSection = false;
                    secondSection = false;
                    thirdSection = false;
                }
            }
        }
    }
}
