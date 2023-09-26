package org.firstinspires.ftc.teamcode.opmodes.auto;

import org.firstinspires.ftc.teamcode.wrappers.Chassis;
import org.firstinspires.ftc.teamcode.wrappers.Game;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;

public class MainAuto {
    public static final int LEFT_BLUE = 0;
    public static final int RIGHT_BLUE = 1;
    public static final int LEFT_RED = 2;
    public static final int RIGHT_RED = 3;

    public int mode = LEFT_BLUE;
    public boolean isExecutingPurplePath = false;
    public boolean isExecutingYellowPath = false;
    public boolean isExecutingBackstagePath = false;

    public Chassis chassis;
    public Vision vision;

    public void runSetup(int mode, Chassis chassis) {
        this.mode = mode;
        this.chassis = chassis;
        try {
            vision = new Vision();
            this.chassis.camera.setPipeline(vision);
            this.chassis.camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
                @Override
                public void onOpened() {
                    MainAuto.this.chassis.camera.startStreaming(Vision.IMAGE_WIDTH, Vision.IMAGE_HEIGHT, OpenCvCameraRotation.UPRIGHT);
                }

                @Override
                public void onError(int errorCode) {
                }
            });
            while (vision.route == -1) {
                // do absolutely nothing
            }
            this.chassis.camera.closeCameraDeviceAsync(() -> {
                // well also do nothing here
            });
            isExecutingPurplePath = true;
        } catch (Exception e) {
            // Do nothing
        }
    }

    public void runLoop() {
        if (isExecutingPurplePath) {
            executePurplePath();
        } else if (isExecutingYellowPath) {
            executeYellowPath();
        } else if (isExecutingBackstagePath) {
            executeBackstagePath();
        }
    }

    public void executePurplePath() {
        switch (vision.route) {
            case Vision.LEFT_PATH:
                chassis.moveChassis(1, Game.TILE_SIZE);
                chassis.turnChassis(-1, 45);
                break;
            case Vision.CENTER_PATH:
                chassis.moveChassis(1, Game.TILE_SIZE);
                break;
            case Vision.RIGHT_PATH:
                chassis.moveChassis(1, Game.TILE_SIZE);
                chassis.turnChassis(1, 45);
                break;
        }
        chassis.turnPivot(1, 90);
        // back drive roller
        chassis.turnRoller(-0.25, 1);

        isExecutingPurplePath = false;
        isExecutingYellowPath = true;
    }

    public void executeYellowPath() {
        switch (mode) {
            case LEFT_BLUE:
                switch (vision.route) {
                    case Vision.LEFT_PATH:
                        chassis.turnChassis(1, 135);
                        break;
                    case Vision.CENTER_PATH:
                        chassis.turnChassis(1, 90);
                        break;
                    case Vision.RIGHT_PATH:
                        chassis.turnChassis(1, 45);
                        break;
                }
                chassis.moveChassis(1, Game.TILE_SIZE);
                break;
            case RIGHT_BLUE:
                switch (vision.route) {
                    case Vision.LEFT_PATH:
                        chassis.turnChassis(1, 135);
                        break;
                    case Vision.CENTER_PATH:
                        chassis.turnChassis(1, 90);
                        break;
                    case Vision.RIGHT_PATH:
                        chassis.turnChassis(1, 45);
                        break;
                }
                chassis.moveChassis(3, Game.TILE_SIZE);
                break;
            case LEFT_RED:
                switch (vision.route) {
                    case Vision.LEFT_PATH:
                        chassis.turnChassis(-1, 45);
                        break;
                    case Vision.CENTER_PATH:
                        chassis.turnChassis(-1, 90);
                        break;
                    case Vision.RIGHT_PATH:
                        chassis.turnChassis(-1, 135);
                        break;
                }
                chassis.moveChassis(3, Game.TILE_SIZE);
                break;
            case RIGHT_RED:
                switch (vision.route) {
                    case Vision.LEFT_PATH:
                        chassis.turnChassis(-1, 45);
                        break;
                    case Vision.CENTER_PATH:
                        chassis.turnChassis(-1, 90);
                        break;
                    case Vision.RIGHT_PATH:
                        chassis.turnChassis(-1, 135);
                        break;
                }
                chassis.moveChassis(1, Game.TILE_SIZE);
                break;
        }
        chassis.turnArm(1, 120);
        chassis.turnFlap(0.5, 3);
        chassis.turnArm(-1, 120);
        isExecutingYellowPath = false;
        isExecutingBackstagePath = true;
    }

    public void executeBackstagePath() {
        switch (mode) {
            case LEFT_BLUE:
            case RIGHT_BLUE:
                chassis.turnChassis(-1, 90);
                break;
            case LEFT_RED:
            case RIGHT_RED:
                chassis.turnChassis(1, 90);
                break;
        }
        chassis.moveChassis(-1, Game.TILE_SIZE);
        isExecutingBackstagePath = false;
    }
}
