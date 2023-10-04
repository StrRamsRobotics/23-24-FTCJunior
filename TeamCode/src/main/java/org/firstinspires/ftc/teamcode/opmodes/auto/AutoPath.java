package org.firstinspires.ftc.teamcode.opmodes.auto;

import org.firstinspires.ftc.teamcode.opmodes.auto.actions.MoveAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.actions.TurnAction;
import org.firstinspires.ftc.teamcode.wrappers.Chassis;

import java.util.ArrayList;

public class AutoPath {
    public Chassis chassis;

    public AutoPoint currentPoint;
    public AutoLine currentLine;
    public AutoPoint activePoint;

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
                        .addAutoAction(new TurnAction(chassis, 1, line))
                        .addAutoAction(new MoveAction(chassis, 1, line));
            }
        }
        currentPoint = autoPoints.get(0);
        currentLine = lines.get(0);
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
        double distance = (currentTime - prevTime) * Chassis.DISTANCE_PER_SECOND / 1000.0;
        if (activePoint != null) {
            activePoint = activePoint.tick();
        }
        else {
            currentPoint = currentLine.getNextPoint(currentPoint, distance);
            for (AutoPoint autoPoint : autoPoints) {
                if (autoPoint.approximatelyEquals(currentPoint)) {
                    activePoint = autoPoint;
                    break;
                }
            }
        }
        return this;
    }
}
