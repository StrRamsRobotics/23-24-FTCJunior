package org.firstinspires.ftc.teamcode.opmodes.auto.sides.front.push;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.generic.classes.Point;
import org.firstinspires.ftc.teamcode.opmodes.auto.actions.AprilTagAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.actions.VisionAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.classes.AutoAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.classes.AutoPath;
import org.firstinspires.ftc.teamcode.opmodes.auto.classes.AutoPoint;
import org.firstinspires.ftc.teamcode.opmodes.auto.helpers.AutoPathHelper;
import org.firstinspires.ftc.teamcode.opmodes.base.BaseAuto;
import org.firstinspires.ftc.teamcode.wrappers.Game;

import java.util.ArrayList;

@Autonomous(name="leftBlueAuto2")
public class LeftBlueAuto2 extends BaseAuto {

    @Override
    public void runSetup() {
        visionAction = new VisionAction(chassis, true);
        visionAction.tick(); // to init camera stream before 30 seconds
    }

    @Override
    public void createPoints() {
        // based off of front of robot
        points.add(new AutoPoint(new Point(0, 2.5 * Game.TS), new ArrayList<>(), false));
        ArrayList<AutoAction> purpleActions = new ArrayList<>();
        ArrayList<AutoAction> yellowActions = new ArrayList<>();
        yellowActions.add(new AprilTagAction(chassis, Game.BLUE_TEAM, route));
        AutoPathHelper.addArmUpMovement(chassis, yellowActions);
//        AutoPathHelper.addFlapOpenMovement(chassis, yellowActions);
//        yellowActions.add(new WaitAction(chassis, Chassis.FLAP_WAIT_TIME));
        AutoPathHelper.addArmDownMovement(chassis, yellowActions);

        switch(route) {
            case 0:
//                AutoPathHelper.addRollerBackwardMovement(chassis, purpleActions);
                points.add(new AutoPoint(new Point(1.75 * Game.TS, 2 * Game.TS), purpleActions, false));
                points.add(new AutoPoint(new Point(1.25 * Game.TS, 2.25 * Game.TS), purpleActions, true));
                points.add(new AutoPoint(new Point(1.25 * Game.TS, 1 * Game.TS), purpleActions, false));
                points.add(new AutoPoint(new Point(1.25 * Game.TS, 0.9 * Game.TS), yellowActions, false));
//                points.add(new AutoPoint(new Point(0.5 * Game.TILE_SIZE, 1 * Game.TILE_SIZE), yellowActions, false));
                break;
            case 2:
//                purpleActions.add(new TurnAction(chassis, 1, -45)); // try removing this
//                AutoPathHelper.addRollerBackwardMovement(chassis, purpleActions);
                points.add(new AutoPoint(new Point(1.75 * Game.TS, 3 * Game.TS), purpleActions, false));
                points.add(new AutoPoint(new Point(1.25 * Game.TS, 2.75 * Game.TS), purpleActions, true));
                points.add(new AutoPoint(new Point(1.75 * Game.TS, 1 * Game.TS), purpleActions, false));
                points.add(new AutoPoint(new Point(1.75 * Game.TS, 0.9 * Game.TS), yellowActions, false));
//                points.add(new AutoPoint(new Point(0.5 * Game.TILE_SIZE, 1 * Game.TILE_SIZE), yellowActions, false));
                break;
            case 1:
            default:
//                AutoPathHelper.addRollerBackwardMovement(chassis, purpleActions);
                points.add(new AutoPoint(new Point(2 * Game.TS, 2.5 * Game.TS), purpleActions, false));
                points.add(new AutoPoint(new Point(1.5 * Game.TS, 2.5 * Game.TS), purpleActions, true));
                points.add(new AutoPoint(new Point(1.5 * Game.TS, 1 * Game.TS), purpleActions, false));
                points.add(new AutoPoint(new Point(1.5 * Game.TS, 0.9 * Game.TS), yellowActions, false));
//                points.add(new AutoPoint(new Point(0.5 * Game.TILE_SIZE, 1 * Game.TILE_SIZE), yellowActions, false));
                break;
        }
        points.add(new AutoPoint(new Point(0, Game.TS), new ArrayList<>(), false));
        path = new AutoPath(chassis, points, 90);
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