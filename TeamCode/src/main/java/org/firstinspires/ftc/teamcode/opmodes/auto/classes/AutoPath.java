package org.firstinspires.ftc.teamcode.opmodes.auto.classes;

import org.firstinspires.ftc.teamcode.opmodes.auto.actions.MoveAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.actions.TurnAction;
import org.firstinspires.ftc.teamcode.wrappers.Chassis;

import java.util.ArrayList;

public class AutoPath {
    public Chassis chassis;

    public AutoPoint currentPoint;
    public AutoLine currentLine;
    public AutoPoint activePoint;
    public AutoPoint nextPoint;
    public double activeDistanceToNext;

    public long prevTime;

    public ArrayList<AutoPoint> autoPoints;
    public ArrayList<AutoLine> lines;

    public AutoPath(Chassis chassis, ArrayList<AutoPoint> autoPoints) {
        this.chassis = chassis;
        this.autoPoints = autoPoints;
        this.lines = new ArrayList<>();
        for (AutoPoint autoPoint : autoPoints) {
            if (autoPoints.indexOf(autoPoint) != autoPoints.size() - 1) {
                AutoLine line = getConnectingLine(autoPoint);
                if (autoPoint.heading == Double.MAX_VALUE) {
                    autoPoint.heading = line.getHeading();
                }
                lines.add(line);
                autoPoint
                        .addAutoAction(new MoveAction(chassis, 0, line))
                        .addAutoAction(new TurnAction(chassis, Chassis.MOVE_POWER, line))
                        .addAutoAction(new MoveAction(chassis, Chassis.MOVE_POWER, line));
            }
        }
        if (autoPoints.size() > 0) {
            currentPoint = autoPoints.get(0);
            activePoint = currentPoint;
        }
        if (autoPoints.size() > 1) {
            nextPoint = autoPoints.get(1);
            activeDistanceToNext = nextPoint.distanceTo(currentPoint);
        }
        if (lines.size() > 0) currentLine = lines.get(0);
        prevTime = System.currentTimeMillis();
    }

    public AutoLine getConnectingLine(AutoPoint autoPoint) {
        if (autoPoints.get(autoPoints.indexOf(autoPoint) + 1) != null) {
            return new AutoLine(autoPoint, autoPoints.get(autoPoints.indexOf(autoPoint) + 1));
        }
        else {
            return null;
        }
    }

    public AutoPath tick() {
        long currentTime = System.currentTimeMillis();
        double stepDistance = (currentTime - prevTime) * Chassis.MOVE_DISTANCE_PER_SECOND / 1000.0;
        boolean activePointRunning = true;
        if (activePointRunning && activePoint != null) {
            chassis.telemetry.addData("Active point x", activePoint.x);
            chassis.telemetry.addData("Active point y", activePoint.y);
            chassis.telemetry.addData("Active point heading", activePoint.heading);
            chassis.telemetry.addData("Active point distance to current", activePoint.distanceTo(currentPoint));
            chassis.telemetry.addData("Active point distance to next", activeDistanceToNext);
            AutoPoint point = activePoint.tick();
            if (point == null) {
                activePointRunning = false;
            }
            else {
                activePoint = point;
            }
            chassis.telemetry.addData("Active point running", activePointRunning);
        }
        if (!activePointRunning) {
            currentPoint = currentLine.getNextPoint(currentPoint, stepDistance);
            chassis.telemetry.addData("Slope", currentLine.slope);
            chassis.telemetry.addData("Step distance", stepDistance);
            chassis.telemetry.addData("Current point x", currentPoint.x);
            chassis.telemetry.addData("Current point y", currentPoint.y);
            if (
                    autoPoints.indexOf(activePoint) + 1 <= autoPoints.size() &&
                    activePoint.distanceTo(currentPoint) > activeDistanceToNext
            ) {
                activeDistanceToNext = currentPoint.distanceTo(nextPoint);
                activePoint = nextPoint;
                chassis.telemetry.addData("New active point", autoPoints.indexOf(activePoint));
                chassis.telemetry.addData("Active point x", activePoint.x);
                chassis.telemetry.addData("Active point y", activePoint.y);
                nextPoint = autoPoints.get(autoPoints.indexOf(activePoint) + 1);
            }
        }
        prevTime = currentTime;
        chassis.telemetry.update();
        return this;
    }
}
