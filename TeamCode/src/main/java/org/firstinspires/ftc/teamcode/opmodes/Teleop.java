package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.utils.helpers.MathHelper;
import org.firstinspires.ftc.teamcode.wrappers.TankChassis;

@TeleOp(name="TeleOp")
public class Teleop extends LinearOpMode {
    private TankChassis chassis;

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        while (opModeIsActive()) {
            // Tank drive
            double lx = gamepad1.left_stick_x, ly = gamepad1.left_stick_y, rx = gamepad1.right_stick_x, ry = gamepad1.right_stick_y;
//            chassis.fr.setPower(ry);
//            chassis.br.setPower(ry);
//            chassis.fl.setPower(ly);
//            chassis.bl.setPower(ly);

//            double lr = MathHelper.radius(lx, ly);
//            double rr = MathHelper.radius(rx, ry);
//            double lp = Math.pow(lr, 2);
//            double rp = Math.pow(rr, 2);

            double lp = Math.pow(ly, 2);
            double rp = Math.pow(ry, 2);

            chassis.fl.setPower(MathHelper.deadzone(ly, 0.1));
            chassis.bl.setPower(MathHelper.deadzone(ly, 0.1));
            chassis.fr.setPower(MathHelper.deadzone(ry, 0.1));
            chassis.br.setPower(MathHelper.deadzone(ry, 0.1));
        }
    }
}
