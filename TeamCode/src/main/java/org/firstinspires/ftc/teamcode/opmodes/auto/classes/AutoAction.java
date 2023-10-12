package org.firstinspires.ftc.teamcode.opmodes.auto.classes;

import org.firstinspires.ftc.teamcode.wrappers.Chassis;

public abstract class AutoAction {
    public Chassis chassis;

    public boolean active = true;

    public AutoAction (Chassis chassis) {
        this.chassis = chassis;
        this.chassis.logHelper.addData("AutoAction Initialized", this.getClass().getSimpleName());
        this.active = true;
    }

    public abstract void tick();
}
