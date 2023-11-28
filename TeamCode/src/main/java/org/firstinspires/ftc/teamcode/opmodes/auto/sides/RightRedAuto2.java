package org.firstinspires.ftc.teamcode.opmodes.auto.sides;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.generic.classes.Point;
import org.firstinspires.ftc.teamcode.opmodes.auto.actions.AprilTagAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.actions.TurnAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.actions.VisionAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.actions.WaitAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.classes.AutoAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.classes.AutoPath;
import org.firstinspires.ftc.teamcode.opmodes.auto.classes.AutoPoint;
import org.firstinspires.ftc.teamcode.opmodes.auto.helpers.AutoPathHelper;
import org.firstinspires.ftc.teamcode.opmodes.base.BaseAuto;
import org.firstinspires.ftc.teamcode.wrappers.Chassis;
import org.firstinspires.ftc.teamcode.wrappers.Game;

import java.util.ArrayList;

@Autonomous(name="rightRedAuto2")
public class RightRedAuto2 extends BaseAuto {
    @Override
    public void runSetup() {
        visionAction = new VisionAction(chassis, false);
        visionAction.tick(); // to init camera stream before 30 seconds
    }
    @Override
    public void createPoints() {
        // based off of front of robot
        points.add(new AutoPoint(new Point(6, 2.5 * Game.TILE_SIZE), new ArrayList<>(), -90, true));
        ArrayList<AutoAction> purpleActions = new ArrayList<>();
        ArrayList<AutoAction> yellowActions = new ArrayList<>();
        yellowActions.add(new AprilTagAction(chassis, Game.RED_TEAM, route));
        AutoPathHelper.addArmUpMovement(chassis, yellowActions);
        AutoPathHelper.addFlapOpenMovement(chassis, yellowActions);
        yellowActions.add(new WaitAction(chassis, Chassis.FLAP_WAIT_TIME));
        AutoPathHelper.addArmDownMovement(chassis, yellowActions);

        switch(route) {
            case 0:
                purpleActions.add(new TurnAction(chassis, 1, -45));
                AutoPathHelper.addRollerBackwardMovement(chassis, purpleActions);
                points.add(new AutoPoint(new Point(4.25 * Game.TILE_SIZE, 3 * Game.TILE_SIZE), purpleActions, false));
                points.add(new AutoPoint(new Point(4.75 * Game.TILE_SIZE, 2.75 * Game.TILE_SIZE), yellowActions, true));
                points.add(new AutoPoint(new Point(4.25 * Game.TILE_SIZE, 1 * Game.TILE_SIZE), yellowActions, false));
//                points.add(new AutoPoint(new Point(5.5 * Game.TILE_SIZE, 1 * Game.TILE_SIZE), yellowActions, true));
                break;
            case 2:
                purpleActions.add(new TurnAction(chassis, 1, 45));
                AutoPathHelper.addRollerBackwardMovement(chassis, purpleActions);
                points.add(new AutoPoint(new Point(4.25 * Game.TILE_SIZE, 2 * Game.TILE_SIZE), purpleActions, false));
                points.add(new AutoPoint(new Point(4.75 * Game.TILE_SIZE, 2.25 * Game.TILE_SIZE), yellowActions, true));
                points.add(new AutoPoint(new Point(4.75 * Game.TILE_SIZE, 1 * Game.TILE_SIZE), yellowActions, false));
//                points.add(new AutoPoint(new Point(5.5 * Game.TILE_SIZE, 1 * Game.TILE_SIZE), yellowActions, true));
                break;
            case 1:
            default:
                AutoPathHelper.addRollerBackwardMovement(chassis, purpleActions);
                points.add(new AutoPoint(new Point(4 * Game.TILE_SIZE, 2.5 * Game.TILE_SIZE), purpleActions, false));
                points.add(new AutoPoint(new Point(4.5 * Game.TILE_SIZE, 2.5 * Game.TILE_SIZE), yellowActions, true));
                points.add(new AutoPoint(new Point(4.5 * Game.TILE_SIZE, 1 * Game.TILE_SIZE), yellowActions, false));
//                points.add(new AutoPoint(new Point(5.5 * Game.TILE_SIZE, 1 * Game.TILE_SIZE), yellowActions, true));
                break;
        }
        points.add(new AutoPoint(new Point(5.5 * Game.TILE_SIZE, Game.TILE_SIZE), new ArrayList<>(), false));
        path = new AutoPath(chassis, points, true);
    }

    @Override
    public void runLoop() {
        if (route == -1) {
            visionAction.tick();
            if (!visionAction.active) {
                route = visionAction.route;
            }
            chassis.logHelper.update();
        }
        else {
            if (points.size() == 0) {
                createPoints();
            }
            chassis.logHelper.addData("Route", route);
            if (path.active) {
                path.tick();
            }
        }
    }
}