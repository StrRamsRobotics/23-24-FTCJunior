package org.firstinspires.ftc.teamcode.opmodes.auto.actions;

import org.firstinspires.ftc.teamcode.opmodes.auto.classes.AutoAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.pipelines.VisionPipeline;
import org.firstinspires.ftc.teamcode.wrappers.Chassis;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;

public class VisionAction extends AutoAction {
    public VisionPipeline visionPipeline;
    public int counter = 0;
    public boolean isCameraInitialized = false;
    public int route;

    public VisionAction(Chassis chassis) {
        super(chassis);
        route = -1;
        active = true;
    }

    public VisionAction tick() {
        chassis.telemetry.addData("Running", "VisionAction");
        if (!isCameraInitialized) {
            try {
                visionPipeline = new VisionPipeline();
                chassis.camera.setPipeline(visionPipeline);
                chassis.camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
                    @Override
                    public void onOpened() {
                        chassis.camera.startStreaming(VisionPipeline.IMAGE_WIDTH, VisionPipeline.IMAGE_HEIGHT, OpenCvCameraRotation.UPRIGHT);
                        chassis.ftcDashboard.startCameraStream(chassis.camera, 15);
                    }

                    @Override
                    public void onError(int errorCode) {
                    }
                });
            } catch (Exception e) {}
            isCameraInitialized = true;
        }
        route = visionPipeline.route;
        chassis.telemetry.addData("COunter", counter++);
        chassis.telemetry.addData("VisionAction Route", route);
        if (visionPipeline.center != null) {
            chassis.telemetry.addData("Center X", visionPipeline.center.x);

        }
        if (route != -1) {
            chassis.camera.closeCameraDeviceAsync(() -> {
            });
            active = false;
            return null;
        }
        else {
            return this;
        }
    }
}
