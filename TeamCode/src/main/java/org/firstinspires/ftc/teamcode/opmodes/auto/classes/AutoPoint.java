package org.firstinspires.ftc.teamcode.opmodes.auto.classes;

import org.firstinspires.ftc.teamcode.opmodes.auto.actions.TurnAction;
import org.firstinspires.ftc.teamcode.generic.classes.Point;
import org.firstinspires.ftc.teamcode.generic.helpers.MathHelper;

import java.util.ArrayList;

public class AutoPoint extends Point {
    public ArrayList<AutoAction> autoActions = new ArrayList<>();
    public AutoAction currentAutoAction;
    public boolean isReverse = false;
    public boolean active = true;

//    public AutoPoint (Point point, ArrayList<AutoAction> autoActions, double heading, boolean isReverse) {
//        super(point);
//        setAutoActions(autoActions);
//        this.heading = heading;
//        this.isReverse = isReverse;
//    }

//    public AutoPoint (Point point, ArrayList<AutoAction> autoActions, double heading) {
//        super(point);
//        setAutoActions(autoActions);
//        this.heading = heading;
//    }

    public AutoPoint (Point point, ArrayList<AutoAction> autoActions, boolean isReverse) {
        super(point);
        setAutoActions(autoActions);
        this.isReverse = isReverse;
    }

    public AutoPoint (Point point, ArrayList<AutoAction> autoActions) {
        super(point);
        setAutoActions(autoActions);
    }

    public AutoPoint addAutoAction(AutoAction autoAction) {
        autoActions.add(autoAction);
        setAutoActions(autoActions);
        return this;
    }

    public AutoPoint addAutoAction(int loc, AutoAction autoAction) {
        autoActions.add(loc, autoAction);
        setAutoActions(autoActions);
        return this;
    }

    public void setAutoActions(ArrayList<AutoAction> autoActions) {
        this.autoActions = autoActions;
        if (autoActions.size() > 0) this.currentAutoAction = autoActions.get(0);
//        for (AutoAction autoAction : autoActions) {
//            if (autoAction instanceof TurnAction) {
//                double angle = ((TurnAction) autoAction).angle;
//                this.heading = MathHelper.toHeading(this.heading + angle);
//            }
//        }
        this.active = true;
    }

    public void tick() {
        if (autoActions.size() > 0) {
            if (currentAutoAction != null) {
                currentAutoAction.tick();
                if (!currentAutoAction.active) {
                    if (autoActions.indexOf(currentAutoAction) + 1 < autoActions.size()) {
                        currentAutoAction = autoActions.get(autoActions.indexOf(currentAutoAction) + 1);
                    }
                    else {
                        currentAutoAction = null;
                        active = false;
                    }
                }
            }
        }
        else {
            active = false;
        }
    }
}
