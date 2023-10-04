package org.firstinspires.ftc.teamcode.opmodes.base;

import org.firstinspires.ftc.teamcode.opmodes.auto.classes.AutoPath;
import org.firstinspires.ftc.teamcode.opmodes.auto.actions.VisionAction;

public abstract class BaseAuto extends BaseOpmode {
    public int route = -1;
    public AutoPath autoPath;

    public void runSetup() {
        VisionAction visionAction = new VisionAction(chassis);
        visionAction.tick();
        route = visionAction.route;
    }
}
