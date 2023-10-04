package org.firstinspires.ftc.teamcode.opmodes.auto.sides;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmodes.auto.classes.AutoAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.classes.AutoPath;
import org.firstinspires.ftc.teamcode.opmodes.auto.classes.AutoPoint;
import org.firstinspires.ftc.teamcode.opmodes.auto.actions.AprilTagAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.actions.ArmAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.actions.FlapAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.actions.RollerAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.actions.TurnAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.actions.WaitAction;
import org.firstinspires.ftc.teamcode.opmodes.base.BaseAuto;
import org.firstinspires.ftc.teamcode.utils.classes.Point;
import org.firstinspires.ftc.teamcode.wrappers.Chassis;
import org.firstinspires.ftc.teamcode.wrappers.Game;

import java.util.ArrayList;

@Autonomous(name="rightRedAuto")
public class RightRedAuto extends BaseAuto {
    public ArrayList<AutoPoint> points = new ArrayList<>();
    public AutoPath path;

    @Override
    public void runSetup() {
        // based off of front of robot
        points.add(new AutoPoint(new Point(6, 2.5 * Game.TILE_SIZE), new ArrayList<>(), -90, true));
        ArrayList<AutoAction> purpleActions = new ArrayList<>();
        ArrayList<AutoAction> yellowActions = new ArrayList<>();
        yellowActions.add(new AprilTagAction(chassis, Game.RED_TEAM, route));
        if (Chassis.HAS_ARM) yellowActions.add(new ArmAction(chassis, 1, 120));
        if (Chassis.HAS_FLAP) yellowActions.add(new FlapAction(chassis, 1));
        yellowActions.add(new WaitAction(1000));
        if (Chassis.HAS_FLAP) yellowActions.add(new FlapAction(chassis, 0));
        if (Chassis.HAS_ARM) yellowActions.add(new ArmAction(chassis, 1, -120));
        switch(route) {
            case 0:
                if (Chassis.HAS_ROLLER)purpleActions.add(new RollerAction(chassis, -1));
                purpleActions.add(new WaitAction(1000));
                if (Chassis.HAS_ROLLER)purpleActions.add(new RollerAction(chassis, 0));
                points.add(new AutoPoint(new Point(4.5 * Game.TILE_SIZE, 1.75 * Game.TILE_SIZE), purpleActions, true));
                points.add(new AutoPoint(new Point(4.75 * Game.TILE_SIZE, 1.5 * Game.TILE_SIZE), yellowActions, true));
                break;
            case 2:
                purpleActions.add(new TurnAction(chassis, 1, -45));
                if (Chassis.HAS_ROLLER) purpleActions.add(new RollerAction(chassis, -1));
                purpleActions.add(new WaitAction(1000));
                if (Chassis.HAS_ROLLER) purpleActions.add(new RollerAction(chassis, 0));
                points.add(new AutoPoint(new Point(4 * Game.TILE_SIZE, 2.5 * Game.TILE_SIZE), purpleActions, true));
                points.add(new AutoPoint(new Point(4.25 * Game.TILE_SIZE, 1.5 * Game.TILE_SIZE), yellowActions, true));
                break;
            case 1:
            default:
                if (Chassis.HAS_ROLLER) purpleActions.add(new RollerAction(chassis, -1));
                purpleActions.add(new WaitAction(1000));
                if (Chassis.HAS_ROLLER) purpleActions.add(new RollerAction(chassis, 0));
                points.add(new AutoPoint(new Point(4 * Game.TILE_SIZE, 2.5 * Game.TILE_SIZE), purpleActions, true));
                points.add(new AutoPoint(new Point(4.5 * Game.TILE_SIZE, 1.5 * Game.TILE_SIZE), yellowActions, true));
                break;
        }
        path = new AutoPath(chassis, points);
    }

    @Override
    public void runLoop() {
        path.tick();
    }
}