package org.firstinspires.ftc.teamcode.opmodes.auto.helpers;

import org.firstinspires.ftc.teamcode.opmodes.auto.actions.ArmAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.actions.FlapAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.actions.PivotAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.actions.RollerAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.actions.WaitAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.classes.AutoAction;
import org.firstinspires.ftc.teamcode.wrappers.Chassis;

import java.util.ArrayList;

public class AutoPathHelper {
    public static void addArmUpMovement(Chassis chassis, ArrayList<AutoAction> armActions) {
        if (Chassis.HAS_ARM) armActions.add(new ArmAction(chassis, Chassis.ARM_POWER, Chassis.ARM_TURN_DEGREES));
        if (Chassis.HAS_PIVOT) armActions.add(new PivotAction(chassis, Chassis.PIVOT_POWER, Chassis.ARM_TURN_DEGREES));
        if (Chassis.HAS_ARM) armActions.add(new ArmAction(chassis, Chassis.ARM_POWER, Chassis.ARM_STRAIGHT_DEGREES));
    }

    public static void addArmDownMovement(Chassis chassis, ArrayList<AutoAction> armActions) {
        if (Chassis.HAS_ARM) armActions.add(new ArmAction(chassis, -Chassis.ARM_POWER, Chassis.ARM_TURN_DEGREES));
        if (Chassis.HAS_PIVOT) armActions.add(new PivotAction(chassis, -Chassis.PIVOT_POWER, Chassis.ARM_TURN_DEGREES));
        if (Chassis.HAS_ARM) armActions.add(new ArmAction(chassis, -Chassis.ARM_POWER, Chassis.ARM_STRAIGHT_DEGREES));
    }

    public static void addFlapOpenMovement(Chassis chassis, ArrayList<AutoAction> flapActions) {
        if (Chassis.HAS_FLAP) {
            if (Chassis.IS_FLAP_CR) {
                flapActions.add(new FlapAction(chassis, 1));
                flapActions.add(new WaitAction(chassis, Chassis.FLAP_OPEN_TIME));
                flapActions.add(new FlapAction(chassis, 0));
            } else {
                flapActions.add(new FlapAction(chassis, 1));
            }
        }
    }

    public static void addFlapCloseMovement(Chassis chassis, ArrayList<AutoAction> flapActions) {
        if (Chassis.HAS_FLAP) {
            if (Chassis.IS_FLAP_CR) {
                flapActions.add(new FlapAction(chassis, -1));
                flapActions.add(new WaitAction(chassis, Chassis.FLAP_OPEN_TIME));
                flapActions.add(new FlapAction(chassis, 0));
            } else {
                flapActions.add(new FlapAction(chassis, 0));
            }
        }
    }

    public static void addRollerForwardMovement(Chassis chassis, ArrayList<AutoAction> rollerActions) {
        if (Chassis.HAS_ROLLER) {
            rollerActions.add(new RollerAction(chassis, Chassis.ROLLER_POWER));
            rollerActions.add(new WaitAction(chassis, Chassis.ROLLER_WAIT_TIME));
            rollerActions.add(new RollerAction(chassis, 0));
        }
    }

    public static void addRollerBackwardMovement(Chassis chassis, ArrayList<AutoAction> rollerActions) {
        if (Chassis.HAS_ROLLER) {
            rollerActions.add(new RollerAction(chassis, -Chassis.ROLLER_POWER));
            rollerActions.add(new WaitAction(chassis, Chassis.ROLLER_WAIT_TIME));
            rollerActions.add(new RollerAction(chassis, 0));
        }
    }
}
