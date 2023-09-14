package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.wrappers.TankChassis;

public abstract class BaseAuto extends LinearOpMode {
    public TankChassis chassis;

    public void move(double fr, double fl, double br, double bl) {
        chassis.fr.setPower(fr);
        chassis.fl.setPower(fl);
        chassis.br.setPower(br);
        chassis.bl.setPower(bl);
    }
}
