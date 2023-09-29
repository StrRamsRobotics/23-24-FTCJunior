package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.opmodes.base.BaseTeleop;
import org.firstinspires.ftc.teamcode.utils.helpers.MathHelper;
import org.firstinspires.ftc.teamcode.wrappers.Chassis;

@TeleOp(name = "TeleOp")
public class Teleop extends BaseTeleop {
    public static final double JOYSTICK_DEADZONE = 0.025;

    @Override
    public void runSetup() {

    }

    @Override
    public void runLoop() {
        controlGP1();
        controlGP2();
    }

    public void controlGP1() {
        double lx = gamepad1.left_stick_x, ly = gamepad1.left_stick_y, rx = gamepad1.right_stick_x, ry = gamepad1.right_stick_y;
        double lxp = Math.pow(lx, 2), lyp = Math.pow(ly, 2), rxp = Math.pow(rx, 2), ryp = Math.pow(ry, 2);
        if (Chassis.TANK_DRIVE) {
            // Tank drive
            chassis.fl.setPower(MathHelper.deadzone(lyp, JOYSTICK_DEADZONE));
            chassis.bl.setPower(MathHelper.deadzone(lyp, JOYSTICK_DEADZONE));
            chassis.fr.setPower(MathHelper.deadzone(ryp, JOYSTICK_DEADZONE));
            chassis.br.setPower(MathHelper.deadzone(ryp, JOYSTICK_DEADZONE));
            // two wheeled
            if (Chassis.TWO_WHEELED) {
                chassis.bl.setPower(MathHelper.deadzone(lyp, JOYSTICK_DEADZONE));
                chassis.br.setPower(MathHelper.deadzone(ryp, JOYSTICK_DEADZONE));
            }
        } else {
            // Arcade drive
            chassis.fl.setPower(MathHelper.deadzone(lyp - rxp, JOYSTICK_DEADZONE));
            chassis.bl.setPower(MathHelper.deadzone(lyp - rxp, JOYSTICK_DEADZONE));
            chassis.fr.setPower(MathHelper.deadzone(lyp + rxp, JOYSTICK_DEADZONE));
            chassis.br.setPower(MathHelper.deadzone(lyp + rxp, JOYSTICK_DEADZONE));
            if (Chassis.TWO_WHEELED) {
                chassis.bl.setPower(MathHelper.deadzone(lyp - rxp, JOYSTICK_DEADZONE));
                chassis.br.setPower(MathHelper.deadzone(lyp + rxp, JOYSTICK_DEADZONE));
            }
        }
    }

    public void controlGP2() {
        double lx = gamepad2.left_stick_x, ly = gamepad2.left_stick_y, rx = gamepad2.right_stick_x, ry = gamepad2.right_stick_y;
        double lxp = Math.pow(lx, 2), lyp = Math.pow(ly, 2), rxp = Math.pow(rx, 2), ryp = Math.pow(ry, 2);
        boolean a = gamepad2.a, b = gamepad2.b, x = gamepad2.x, y = gamepad2.y;
        // turn arm
        // intake using pressing x. we wont rotate the pivot since it is not necessary
    }
}
