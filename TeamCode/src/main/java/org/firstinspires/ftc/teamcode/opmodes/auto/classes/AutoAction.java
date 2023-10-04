package org.firstinspires.ftc.teamcode.opmodes.auto.classes;

import org.firstinspires.ftc.teamcode.wrappers.Chassis;

public abstract class AutoAction {
    public Chassis chassis;

    public boolean active;

    public AutoAction (Chassis chassis) {
        this.chassis = chassis;
    }

    public abstract AutoAction tick();
}
