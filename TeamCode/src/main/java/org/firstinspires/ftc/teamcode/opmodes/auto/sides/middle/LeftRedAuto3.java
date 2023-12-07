package org.firstinspires.ftc.teamcode.opmodes.auto.sides.middle;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.generic.classes.Point;
import org.firstinspires.ftc.teamcode.opmodes.auto.actions.AprilTagAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.actions.LauncherMotorAction;
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

@Autonomous(name="leftRedAuto3")
public class LeftRedAuto3 extends BaseAuto {
    @Override
    public void runSetup() {
        visionAction = new VisionAction(chassis, false);
        visionAction.tick(); // to init camera stream before 30 seconds
    }
    @Override
    public void createPoints() {
        // based off of front of robot
        ArrayList<AutoAction> initActions = new ArrayList<>();
        initActions.add(new LauncherMotorAction(chassis, -Chassis.LAUNCHER_MOTOR_POWER));
        initActions.add(new WaitAction(chassis, 1000));
        initActions.add(new LauncherMotorAction(chassis, 0));
        //points.add(new AutoPoint(new Point(5.5 * Game.TILE_SIZE, 4.5 * Game.TILE_SIZE), initActions, false));
        points.add(new AutoPoint(new Point(6 - 0.5 * Game.TS, 4.5 * Game.TS), initActions, false));
        ArrayList<AutoAction> purpleActions = new ArrayList<>();
        ArrayList<AutoAction> yellowActions = new ArrayList<>();
//        yellowActions.add(new AprilTagAction(chassis, Game.RED_TEAM, route));
//        AutoPathHelper.addArmUpMovement(chassis, yellowActions);
//        AutoPathHelper.addFlapOpenMovement(chassis, yellowActions);
//        yellowActions.add(new WaitAction(chassis, Chassis.FLAP_WAIT_TIME));
//        AutoPathHelper.addArmDownMovement(chassis, yellowActions);

        switch(route) {
            case 0:
                /*
                points.add(new AutoPoint(new Point(4.75 * Game.TILE_SIZE, 4.75 * Game.TILE_SIZE), purpleActions, true));
                points.add(new AutoPoint(new Point(5.25 * Game.TILE_SIZE, 4.5 * Game.TILE_SIZE), new ArrayList<>(), false));
                points.add(new AutoPoint(new Point(3.5 * Game.TILE_SIZE, 4.5 * Game.TILE_SIZE), new ArrayList<>(), false));
                points.add(new AutoPoint(new Point(3.5 * Game.TILE_SIZE, 2 * Game.TILE_SIZE), new ArrayList<>(), false));
                points.add(new AutoPoint(new Point(4.25 * Game.TILE_SIZE, 1.25 * Game.TILE_SIZE), new ArrayList<>(), false));
                points.add(new AutoPoint(new Point(4.25 * Game.TILE_SIZE, 1.2 * Game.TILE_SIZE), yellowActions, false));
                break;
                 */
                points.add(new AutoPoint(new Point(6 - 1.25 * Game.TS, 4.75 * Game.TS), purpleActions, true));
                points.add(new AutoPoint(new Point(6 - 0.75 * Game.TS, 4.5 * Game.TS), new ArrayList<>(), false));
                points.add(new AutoPoint(new Point(6 - 2.5 * Game.TS, 4.5 * Game.TS), new ArrayList<>(), false));
                points.add(new AutoPoint(new Point(6 - 2.5 * Game.TS, 2 * Game.TS), new ArrayList<>(), false));
                points.add(new AutoPoint(new Point(6 - 1.75 * Game.TS, 1.25 * Game.TS), new ArrayList<>(), false));
                points.add(new AutoPoint(new Point(6 - 1.75 * Game.TS, 1.2 * Game.TS), yellowActions, false));
                break;
            case 2:
                /*
                points.add(new AutoPoint(new Point(4.75 * Game.TILE_SIZE, 4.25 * Game.TILE_SIZE), purpleActions, true));
                points.add(new AutoPoint(new Point(5.25 * Game.TILE_SIZE, 4.5 * Game.TILE_SIZE), new ArrayList<>(), false));
                points.add(new AutoPoint(new Point(3.5 * Game.TILE_SIZE, 4.5 * Game.TILE_SIZE), new ArrayList<>(), false));
                points.add(new AutoPoint(new Point(3.5 * Game.TILE_SIZE, 2 * Game.TILE_SIZE), new ArrayList<>(), false));
                points.add(new AutoPoint(new Point(4.25 * Game.TILE_SIZE, 1.25 * Game.TILE_SIZE), new ArrayList<>(), false));
                points.add(new AutoPoint(new Point(4.25 * Game.TILE_SIZE, 1.2 * Game.TILE_SIZE), yellowActions, false));
                break;
                 */
                points.add(new AutoPoint(new Point(6 - 1.25 * Game.TS, 4.25 * Game.TS), purpleActions, true));
                points.add(new AutoPoint(new Point(6 - 0.75 * Game.TS, 4.5 * Game.TS), new ArrayList<>(), false));
                points.add(new AutoPoint(new Point(6 - 2.5 * Game.TS, 4.5 * Game.TS), new ArrayList<>(), false));
                points.add(new AutoPoint(new Point(6 - 2.5 * Game.TS, 2 * Game.TS), new ArrayList<>(), false));
                points.add(new AutoPoint(new Point(6 - 1.25 * Game.TS, 1.25 * Game.TS), new ArrayList<>(), false));
                points.add(new AutoPoint(new Point(6 - 1.25 * Game.TS, 1.2 * Game.TS), yellowActions, false));
                break;
            case 1:
            default:
                /*
                points.add(new AutoPoint(new Point(4.5 * Game.TILE_SIZE, 4.5 * Game.TILE_SIZE), purpleActions, true));
                points.add(new AutoPoint(new Point(5.25 * Game.TILE_SIZE, 4.5 * Game.TILE_SIZE), new ArrayList<>(), false));
                points.add(new AutoPoint(new Point(3.5 * Game.TILE_SIZE, 5 * Game.TILE_SIZE), new ArrayList<>(), false));
                points.add(new AutoPoint(new Point(3.5 * Game.TILE_SIZE, 2 * Game.TILE_SIZE), new ArrayList<>(), false));
                points.add(new AutoPoint(new Point(4.5 * Game.TILE_SIZE, 1.25 * Game.TILE_SIZE), new ArrayList<>(), false));
                points.add(new AutoPoint(new Point(4.5 * Game.TILE_SIZE, 1.2 * Game.TILE_SIZE), yellowActions, false));
                break;
                 */
                points.add(new AutoPoint(new Point(6 - 1.5 * Game.TS, 4.5 * Game.TS), purpleActions, true));
                points.add(new AutoPoint(new Point(6 - 1.25 * Game.TS, 4.5 * Game.TS), new ArrayList<>(), false));
                points.add(new AutoPoint(new Point(6 - 2.5 * Game.TS, 5 * Game.TS), new ArrayList<>(), false));
                points.add(new AutoPoint(new Point(6 - 2.5 * Game.TS, 2 * Game.TS), new ArrayList<>(), false));
                points.add(new AutoPoint(new Point(6 - 1.5 * Game.TS, 1.25 * Game.TS), new ArrayList<>(), false));
                points.add(new AutoPoint(new Point(6 - 1.5 * Game.TS, 1.2 * Game.TS), yellowActions, false));
                break;
        }
        //points.add(new AutoPoint(new Point(5.5 * Game.TILE_SIZE, Game.TILE_SIZE), new ArrayList<>(), false));
        points.add(new AutoPoint(new Point(6 - 0.5, Game.TS), new ArrayList<>(), false));
        path = new AutoPath(chassis, points, -90);
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