package org.firstinspires.ftc.teamcode.opmodes.auto.actions;

import org.firstinspires.ftc.teamcode.opmodes.auto.classes.AutoAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.classes.AutoLine;
import org.firstinspires.ftc.teamcode.wrappers.Chassis;

public class MoveAction extends AutoAction {
    public double power;
    public AutoLine line;

    public MoveAction (Chassis chassis, double power, AutoLine line) {
        super(chassis);
        this.line = line;
        if (this.line.point1.isReverse) {
            this.power = -power;
        }
        else {
            this.power = power;
        }
    }

    public MoveAction (Chassis chassis, double power, boolean reverse) {
        super(chassis);
        if (reverse) {
            this.power = -power;
        }
        else {
            this.power = power;
        }
    }

    public MoveAction (Chassis chassis, double power) {
        super(chassis);
        this.power = power;
    }

    public MoveAction tick() {
        chassis.telemetry.addData("Running", "MoveAction");
        chassis.telemetry.addData("Power", power);
        chassis.fr.setPower(power);
        chassis.fl.setPower(power);
        if (!Chassis.TWO_WHEELED) {
            chassis.br.setPower(power);
            chassis.bl.setPower(power);
        }
        return null;
    }
}
