package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.opmodes.base.BaseTeleop;
import org.firstinspires.ftc.teamcode.generic.helpers.MathHelper;
import org.firstinspires.ftc.teamcode.wrappers.Chassis;

@TeleOp(name = "ArcadeDriveTeleOp")
public class ArcadeDriveTeleOp extends BaseTeleop {
    public static final double JOYSTICK_DEADZONE = 0.3;

//    public AutoPath path;
//    public boolean isArmUp = false;

    @Override
    public void runSetup() {
//        if (Chassis.HAS_FLAP) {
//            chassis.flap.setPosition(0);
//        }
//        if (Chassis.HAS_ARM) {
//            chassis.arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
////            chassis.arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        }
    }

    @Override
    public void runLoop() {
        controlGP1();
//        controlGP2();
//        tick();
    }

    public void controlGP1() {
        double lx = gamepad1.left_stick_x, ly = gamepad1.left_stick_y, rx = gamepad1.right_stick_x, ry = gamepad1.right_stick_y;
        boolean a = gamepad1.a, b = gamepad1.b, x = gamepad1.x, y = gamepad1.y;
        boolean up = gamepad1.dpad_up, down = gamepad1.dpad_down, left = gamepad1.dpad_left, right = gamepad1.dpad_right;

        // Arcade drive
        chassis.fl.setPower(MathHelper.deadzone(- (ly + rx), JOYSTICK_DEADZONE) * Chassis.TELEOP_MOVE_POWER);
        chassis.fr.setPower(MathHelper.deadzone(- (ly - rx), JOYSTICK_DEADZONE) * Chassis.TELEOP_MOVE_POWER);
        if (!Chassis.TWO_WHEELED) {
            chassis.bl.setPower(MathHelper.deadzone(-(ly + rx), JOYSTICK_DEADZONE) * Chassis.TELEOP_MOVE_POWER);
            chassis.br.setPower(MathHelper.deadzone(-(ly - rx), JOYSTICK_DEADZONE) * Chassis.TELEOP_MOVE_POWER);
        }
        // Hang
        if (Chassis.HAS_HANG) {
            if (a) {
                chassis.hang1.setPower(0.1);
                chassis.hang2.setPower(0.1);
            } else if (x) {
                chassis.hang1.setPower(-0.1);
                chassis.hang2.setPower(-0.1);
            } else {
                chassis.hang1.setPower(0);
                chassis.hang2.setPower(0);
            }
        }
        if (Chassis.HAS_LAUNCHER) {
            if (y) {
                chassis.launcher.setPosition(1);
            } else {
                chassis.launcher.setPosition(0);
            }
        }
        if (Chassis.HAS_ARM) {
            if (up) {
                chassis.arm1.setPower(-1);
                chassis.arm2.setPower(-1);
            }
            else if (down) {
                chassis.arm1.setPower(1);
                chassis.arm2.setPower(1);
            } else {
                chassis.arm1.setPower(0);
                chassis.arm2.setPower(0);
            }
        }
    }

    public void controlGP2() {
        double lx = gamepad2.left_stick_x, ly = gamepad2.left_stick_y, rx = gamepad2.right_stick_x, ry = gamepad2.right_stick_y;
        double lxp = Math.pow(lx, 2), lyp = Math.pow(ly, 2), rxp = Math.pow(rx, 2), ryp = Math.pow(ry, 2);
        boolean a = gamepad2.a, b = gamepad2.b, x = gamepad2.x, y = gamepad2.y;
//        if (Chassis.HAS_PIVOT) {
//            chassis.pivot.setPower(MathHelper.deadzone(rx, JOYSTICK_DEADZONE));
//        }
//        if (Chassis.HAS_ROLLER) {
//            chassis.roller.setPower(MathHelper.deadzone(ly, JOYSTICK_DEADZONE));
//        }
        /*
        if (Chassis.HAS_ARM) {
            chassis.arm.setPower(MathHelper.deadzone(ry, JOYSTICK_DEADZONE));
        }
         */
//        if (Chassis.HAS_FLAP) {
//            if (lx > 0.9) {
//                chassis.flap.setPosition(1);
//            } else {
//                chassis.flap.setPosition(0);
//            }
//        }
//        if (path == null) {
//            ArrayList<AutoPoint> armPoints = new ArrayList<>();
//            ArrayList<AutoAction> armActions = new ArrayList<>();
//            // lift arm
//            if (a) {
//                AutoPathHelper.addArmUpMovement(chassis, armActions);
//            }
//            // lower arm
//            if (b) {
//                AutoPathHelper.addArmDownMovement(chassis, armActions);
//            }
//            // drop pixel
//            if (x) {
//                AutoPathHelper.addFlapOpenMovement(chassis, armActions);
//                armActions.add(new WaitAction(chassis, 1000));
//                AutoPathHelper.addFlapCloseMovement(chassis, armActions);
//            }
//            armPoints.add(new AutoPoint(new Point(0, 0), armActions, 0));
//            armPoints.add(new AutoPoint(new Point(0, 0), new ArrayList<>(), 0));
//            path = new AutoPath(chassis, armPoints, false);
//        }
    }

    public void tick() {
//        if (path != null) {
//            path.tick();
//            if (!path.active) path = null;
//        }
    }
}
