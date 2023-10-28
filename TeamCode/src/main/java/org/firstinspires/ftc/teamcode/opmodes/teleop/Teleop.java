package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.opmodes.auto.actions.ArmAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.actions.FlapAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.actions.PivotAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.actions.WaitAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.classes.AutoAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.classes.AutoPath;
import org.firstinspires.ftc.teamcode.opmodes.auto.classes.AutoPoint;
import org.firstinspires.ftc.teamcode.opmodes.auto.helpers.AutoPathHelper;
import org.firstinspires.ftc.teamcode.opmodes.base.BaseTeleop;
import org.firstinspires.ftc.teamcode.generic.classes.Point;
import org.firstinspires.ftc.teamcode.generic.helpers.MathHelper;
import org.firstinspires.ftc.teamcode.wrappers.Chassis;

import java.util.ArrayList;

@TeleOp(name = "TeleOp")
public class Teleop extends BaseTeleop {
    public static final double JOYSTICK_DEADZONE = 0;

    public AutoPath path;
    public boolean isArmUp = false;

    @Override
    public void runSetup() {
        chassis.arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    @Override
    public void runLoop() {
        controlGP1();
        controlGP2();
        tick();
    }

    public void controlGP1() {
        double lx = gamepad1.left_stick_x, ly = gamepad1.left_stick_y, rx = gamepad1.right_stick_x, ry = gamepad1.right_stick_y;
        double lxp = Math.pow(lx, 2), lyp = Math.pow(ly, 2), rxp = Math.pow(rx, 2), ryp = Math.pow(ry, 2);
        boolean a = gamepad1.a, b = gamepad1.b, x = gamepad1.x, y = gamepad1.y;
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
        if (Chassis.HAS_ROLLER) {
            chassis.roller.setPower(ly);
        }
        if (path == null) {
            ArrayList<AutoPoint> armPoints = new ArrayList<>();
            ArrayList<AutoAction> armActions = new ArrayList<>();
            // lift arm
            if (a) {
                AutoPathHelper.addArmUpMovement(chassis, armActions);
            }
            // lower arm
            if (b) {
                AutoPathHelper.addArmDownMovement(chassis, armActions);
            }
            // drop pixel
            if (x) {
                AutoPathHelper.addFlapOpenMovement(chassis, armActions);
                armActions.add(new WaitAction(chassis, 1000));
                AutoPathHelper.addFlapCloseMovement(chassis, armActions);
            }
            armPoints.add(new AutoPoint(new Point(0, 0), armActions));
            path = new AutoPath(chassis, armPoints, false);
        }
    }

    public void tick() {
        if (path != null) {
            path.tick();
            if (!path.active) path = null;
        }
    }
}
