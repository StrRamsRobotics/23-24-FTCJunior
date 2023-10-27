package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.opmodes.auto.actions.ArmAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.actions.FlapAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.actions.PivotAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.actions.RollerAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.actions.WaitAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.classes.AutoAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.classes.AutoPath;
import org.firstinspires.ftc.teamcode.opmodes.auto.classes.AutoPoint;
import org.firstinspires.ftc.teamcode.opmodes.base.BaseTeleop;
import org.firstinspires.ftc.teamcode.utils.classes.Point;
import org.firstinspires.ftc.teamcode.utils.helpers.MathHelper;
import org.firstinspires.ftc.teamcode.wrappers.Chassis;

import java.util.ArrayList;

@TeleOp(name = "TeleOp")
public class Teleop extends BaseTeleop {
    public static final double JOYSTICK_DEADZONE = 0.025;

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
            if (a) chassis.roller.setPower(Chassis.ROLLER_POWER);
            else if (b) chassis.roller.setPower(-Chassis.ROLLER_POWER);
            else chassis.roller.setPower(0);
        }
        // lift arm
        if (b && path == null) {
            ArrayList<AutoPoint> armPoints = new ArrayList<>();
            ArrayList<AutoAction> armActions = new ArrayList<>();
            if (Chassis.HAS_ARM) armActions.add(new ArmAction(chassis, Chassis.ARM_POWER, Chassis.ARM_TURN_DEGREES));
            if (Chassis.HAS_PIVOT) armActions.add(new PivotAction(chassis, Chassis.PIVOT_POWER, Chassis.ARM_TURN_DEGREES));
            if (Chassis.HAS_ARM) armActions.add(new ArmAction(chassis, Chassis.ARM_POWER, Chassis.ARM_STRAIGHT_DEGREES));
            if (Chassis.HAS_FLAP) {
                if (Chassis.IS_FLAP_CR) {
                    armActions.add(new FlapAction(chassis, 1));
                    armActions.add(new WaitAction(chassis, 500));
                    armActions.add(new FlapAction(chassis, 0));
                    armActions.add(new WaitAction(chassis, 1000));
                    armActions.add(new FlapAction(chassis, -1));
                    armActions.add(new WaitAction(chassis, 500));
                    armActions.add(new FlapAction(chassis, 0));
                } else {
                    armActions.add(new FlapAction(chassis, 1));
                    armActions.add(new WaitAction(chassis, 1000));
                    armActions.add(new FlapAction(chassis, 0));
                }
            }
            if (Chassis.HAS_ARM) armActions.add(new ArmAction(chassis, -Chassis.ARM_POWER, Chassis.ARM_STRAIGHT_DEGREES));
            if (Chassis.HAS_PIVOT) armActions.add(new PivotAction(chassis, -Chassis.PIVOT_POWER, Chassis.ARM_TURN_DEGREES));
            if (Chassis.HAS_ARM) armActions.add(new ArmAction(chassis, -Chassis.ARM_POWER, Chassis.ARM_TURN_DEGREES));
            armPoints.add(new AutoPoint(new Point(0, 0), armActions));
            path = new AutoPath(chassis, armPoints);
        }
    }

    public void tick() {
        if (path != null) {
            path.tick();
            if (!path.active) path = null;
        }
    }
}
