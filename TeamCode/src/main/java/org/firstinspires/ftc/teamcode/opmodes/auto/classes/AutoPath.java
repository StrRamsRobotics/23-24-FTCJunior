package org.firstinspires.ftc.teamcode.opmodes.auto.classes;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.teamcode.opmodes.auto.actions.MoveAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.actions.TurnAction;
import org.firstinspires.ftc.teamcode.generic.helpers.MathHelper;
import org.firstinspires.ftc.teamcode.opmodes.auto.actions.WaitAction;
import org.firstinspires.ftc.teamcode.wrappers.Chassis;

import java.util.ArrayList;

public class AutoPath {
    public static final double WAIT_TIME = 1000;
    public Chassis chassis;

    public AutoPoint currentPoint;
    public AutoLine currentLine;
    public AutoPoint activePoint;
    public AutoPoint nextPoint;
    public double activeDistanceToNext;
    public long prevTime;
    public ArrayList<AutoPoint> autoPoints;
    public ArrayList<AutoLine> lines;
    public ArrayList<Double> angles;
    public double currentHeading = 0;
    public boolean active = true;
    public double previousTime = 0;


    public AutoPath(Chassis chassis, ArrayList<AutoPoint> autoPoints, double currentHeading) {
        this.chassis = chassis;
        this.autoPoints = autoPoints;
        this.currentHeading = currentHeading;

        this.lines = new ArrayList<>();
        this.angles = new ArrayList<>();
        if (autoPoints.size() > 0) {
            currentPoint = autoPoints.get(0);
            activePoint = currentPoint;
            for (int i = 0; i < autoPoints.size() - 1; i++) {
                AutoPoint autoPoint = autoPoints.get(i);
                AutoLine line = getConnectingLine(autoPoint);
                lines.add(line);
                autoPoint
                        .addAutoAction(new TurnAction(chassis, Chassis.MOVE_POWER, MathHelper.toHeading(line.getHeading() - currentHeading)))
                        .addAutoAction(new MoveAction(chassis, Chassis.MOVE_POWER, autoPoint.isReverse));
                angles.add(MathHelper.toHeading(line.getHeading() - currentHeading));
                currentHeading = line.getHeading();
            }
        }
        if (autoPoints.size() > 1) {
            nextPoint = autoPoints.get(1);
            activeDistanceToNext = nextPoint.distanceTo(currentPoint);
        }
        if (lines.size() > 0) currentLine = lines.get(0);
        prevTime = System.currentTimeMillis();
        active = true;
    }

    public AutoLine getConnectingLine(AutoPoint autoPoint) {
        if (autoPoints.get(autoPoints.indexOf(autoPoint) + 1) != null) {
            return new AutoLine(autoPoint, autoPoints.get(autoPoints.indexOf(autoPoint) + 1));
        }
        else {
            return null;
        }
    }

    public void tick() {
        long currentTime = System.currentTimeMillis();
        double stepDistance = (currentTime - prevTime) / 1000.0 * Chassis.MOVE_DISTANCE_PER_SECOND;

        if (activePoint != null && currentPoint != null) {
            if (previousTime == 0 || (System.currentTimeMillis() - previousTime > 1000)) {
                chassis.logHelper.addData("Number of points", autoPoints.size());
                chassis.logHelper.addData("Active point index", autoPoints.indexOf(activePoint));
                chassis.logHelper.addData("Active point x", activePoint.x);
                chassis.logHelper.addData("Active point y", activePoint.y);
                chassis.logHelper.addData("Line heading", currentLine.getHeading());
                chassis.logHelper.addData("Active point distance to current", activePoint.distanceTo(currentPoint));
                chassis.logHelper.addData("Active point distance to next", activeDistanceToNext);
                activePoint.tick();
                chassis.logHelper.addData("Active point action index", activePoint.autoActions.indexOf(activePoint.currentAutoAction));
                if (activePoint.currentAutoAction != null) {
                    chassis.logHelper.addData("Active point action active", activePoint.currentAutoAction.active);
                }
                chassis.logHelper.addData("Active point active", activePoint.active);
                chassis.logHelper.addData("Angle at point", angles.get(autoPoints.indexOf(activePoint)));
                logAngles();
                if (!activePoint.active) {
                    currentPoint = currentLine.getNextPoint(currentPoint, stepDistance);
                    double currentDistanceToNext = currentPoint.distanceTo(activePoint);
                    chassis.logHelper.addData("Slope", currentLine.slope);
                    chassis.logHelper.addData("Step distance", stepDistance);
                    chassis.logHelper.addData("Current point x", currentPoint.x);
                    chassis.logHelper.addData("Current point y", currentPoint.y);
                    if (currentDistanceToNext > activeDistanceToNext) {
                        chassis.logHelper.addData("Next index", autoPoints.indexOf(nextPoint) + 1);
                        if (autoPoints.indexOf(nextPoint) + 1 < autoPoints.size()) {
                            activePoint = nextPoint;
                            nextPoint = autoPoints.get(autoPoints.indexOf(activePoint) + 1);
                            activeDistanceToNext = activePoint.distanceTo(nextPoint);
                            currentPoint = activePoint;
                            currentLine = lines.get(autoPoints.indexOf(activePoint));
                            previousTime = System.currentTimeMillis();
                        } else {
                            stopPath();
                        }
                    }
                }
            }
            else {
                chassis.fr.setPower(0);
                chassis.fl.setPower(0);
                if (!Chassis.TWO_WHEELED) {
                    chassis.br.setPower(0);
                    chassis.bl.setPower(0);
                }
            }
        }
        else {
            stopPath();
        }
        prevTime = currentTime;
        chassis.logHelper.update();
    }

    public void logAngles() {
        String anglesString = "";
        for (int i = 0; i < angles.size(); i++) {
            anglesString += angles.get(i) + ", ";
        }
        chassis.logHelper.addData("Angles", anglesString);

        String lineAnglesString = "";
        for (int i = 0; i < lines.size(); i++) {
            lineAnglesString += lines.get(i).getHeading() + ", ";
        }
        chassis.logHelper.addData("Line angles", lineAnglesString);
    }

    public void stopPath() {
        active = false;
        chassis.fr.setPower(0);
        chassis.fl.setPower(0);
        if (!Chassis.TWO_WHEELED) {
            chassis.br.setPower(0);
            chassis.bl.setPower(0);
        }
    }

//    public void checkAccuracy() {
//        Position IMUPosition = chassis.imu.getPosition().toUnit(DistanceUnit.INCH);
//        position = new Position(DistanceUnit.INCH, position.x + IMUPosition.x, position.y + IMUPosition.y, 0, 0);
//        chassis.logHelper.addData("IMU X", IMUPosition.x);
//        chassis.logHelper.addData("IMU Y", IMUPosition.y);
//        chassis.logHelper.addData("IMU Fixed X", position.x);
//        chassis.logHelper.addData("IMU Fixed Y", position.y);
//        chassis.logHelper.addData("IMU Heading", chassis.getHeading());
//        chassis.logHelper.addData("Error X", MathHelper.percentError(position.x, currentPoint.x));
//        chassis.logHelper.addData("Error Y", MathHelper.percentError(position.y, currentPoint.y));
//        chassis.logHelper.addData("Total error", MathHelper.percentError(position.x, currentPoint.x) + MathHelper.percentError(position.y, currentPoint.y));
//        chassis.logHelper.addData("Error heading", MathHelper.percentError(chassis.getHeading(), currentPoint.heading));
//    }
}
