package org.firstinspires.ftc.teamcode.opmodes.auto.sides;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmodes.auto.AutoAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.AutoPath;
import org.firstinspires.ftc.teamcode.opmodes.auto.AutoPoint;
import org.firstinspires.ftc.teamcode.opmodes.auto.actions.AprilTagAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.actions.ArmAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.actions.FlapAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.actions.MoveAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.actions.RollerAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.actions.TurnAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.actions.VisionAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.actions.WaitAction;
import org.firstinspires.ftc.teamcode.opmodes.base.BaseAuto;
import org.firstinspires.ftc.teamcode.utils.classes.Point;
import org.firstinspires.ftc.teamcode.wrappers.Game;

import java.util.ArrayList;

@Autonomous(name="leftBlueAuto")
public class LeftBlueAuto extends BaseAuto {
    public ArrayList<AutoPoint> points = new ArrayList<>();
    public AutoPath path;

    @Override
    public void runSetup() {
        super.runSetup();
        // based off of front of robot
        points.add(new AutoPoint(new Point(0, 2.5 * Game.TILE_SIZE), new ArrayList<>(), 90, true));
        ArrayList<AutoAction> purpleActions = new ArrayList<>();
        ArrayList<AutoAction> yellowActions = new ArrayList<>();
        // yellowActions.add(new AprilTagAction(chassis));
        // yellowActions.add(new ArmAction(chassis, 1, 120));
        // yellowActions.add(new FlapAction(chassis, 1));
        // yellowActions.add(new WaitAction(1000));
        // yellowActions.add(new FlapAction(chassis, 0));
        // yellowActions.add(new ArmAction(chassis, 1, -120));
        switch(route) {
            case 0:
                // purpleActions.add(new RollerAction(chassis, -1));
                purpleActions.add(new WaitAction(1000));
                // purpleActions.add(new RollerAction(chassis, 0));
                points.add(new AutoPoint(new Point(1.5 * Game.TILE_SIZE, 1.75 * Game.TILE_SIZE), purpleActions, true));
                points.add(new AutoPoint(new Point(1.25 * Game.TILE_SIZE, 1.5 * Game.TILE_SIZE), yellowActions, true));
                break;
            case 2:
                // purpleActions.add(new TurnAction(chassis, 1, -45));
                // purpleActions.add(new RollerAction(chassis, -1));
                purpleActions.add(new WaitAction(1000));
                // purpleActions.add(new RollerAction(chassis, 0));
                points.add(new AutoPoint(new Point(2 * Game.TILE_SIZE, 2.5 * Game.TILE_SIZE), purpleActions, true));
                points.add(new AutoPoint(new Point(1.75 * Game.TILE_SIZE, 1.5 * Game.TILE_SIZE), yellowActions, true));
                break;
            case 1:
            default:
                // purpleActions.add(new RollerAction(chassis, -1));
                purpleActions.add(new WaitAction(1000));
                // purpleActions.add(new RollerAction(chassis, 0));
                points.add(new AutoPoint(new Point(2 * Game.TILE_SIZE, 2.5 * Game.TILE_SIZE), purpleActions, true));
                points.add(new AutoPoint(new Point(1.5 * Game.TILE_SIZE, 1.5 * Game.TILE_SIZE), yellowActions, true));
                break;
        }
        path = new AutoPath(chassis, points);
    }

    @Override
    public void runLoop() {
        path.tick();
    }
}