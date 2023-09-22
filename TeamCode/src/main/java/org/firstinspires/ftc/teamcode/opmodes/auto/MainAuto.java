package org.firstinspires.ftc.teamcode.opmodes.auto;

import org.firstinspires.ftc.teamcode.opmodes.base.BaseAuto;
import org.firstinspires.ftc.teamcode.wrappers.Chassis;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;

public class MainAuto {
    public static final int LEFT_BLUE = 0;
    public static final int RIGHT_BLUE = 1;
    public static final int LEFT_RED = 2;
    public static final int RIGHT_RED = 3;

    public int MODE = LEFT_BLUE;
    public Chassis chassis;

    public void runSetup(int mode, Chassis chassis) {
        MODE = mode;
        switch (MODE) {
            case LEFT_BLUE:
                break;
            case RIGHT_BLUE:
                break;
            case LEFT_RED:
                break;
            case RIGHT_RED:
                break;
        }
    }

    public void runLoop() {
        try {
            Vision vision = new Vision();
            chassis.camera.setPipeline(vision);
            chassis.camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
                @Override
                public void onOpened() {
                    chassis.camera.startStreaming(Vision.IMAGE_WIDTH, Vision.IMAGE_HEIGHT, OpenCvCameraRotation.UPRIGHT);
                }

                @Override
                public void onError(int errorCode) {
                }
            });
            while (vision.ROUTE == -1) {
                // do absolutely nothing
            }
            chassis.camera.closeCameraDeviceAsync(() -> {
                // well also do nothing here
            });
            switch (MODE) {
                case LEFT_BLUE:
                    break;
                case RIGHT_BLUE:
                    break;
                case LEFT_RED:
                    break;
                case RIGHT_RED:
                    break;
            }
        }
        catch (Exception e) {
            // Do nothing
        }
    }
}
