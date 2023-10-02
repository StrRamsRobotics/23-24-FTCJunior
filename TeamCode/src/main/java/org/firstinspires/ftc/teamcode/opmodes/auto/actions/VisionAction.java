package org.firstinspires.ftc.teamcode.opmodes.auto.actions;

import org.firstinspires.ftc.teamcode.opmodes.auto.AutoAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.Vision;
import org.firstinspires.ftc.teamcode.wrappers.Chassis;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;

public class VisionAction extends AutoAction {
    public int route;

    public VisionAction(Chassis chassis) {
        super(chassis);
        route = -1;
    }

    public VisionAction tick() {
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
                    // do absolutely nothing
                }
            });
            while (vision.route == -1) {
                // do absolutely nothing
            }
            route = vision.route;
            chassis.camera.closeCameraDeviceAsync(() -> {
                // well also do nothing here
            });
        } catch (Exception e) {}
        return null;
    }
}
