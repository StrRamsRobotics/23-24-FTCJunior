package org.firstinspires.ftc.teamcode.opmodes.auto.actions;

import org.firstinspires.ftc.teamcode.opmodes.auto.classes.AutoAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.pipelines.VisionPipeline;
import org.firstinspires.ftc.teamcode.wrappers.Chassis;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;

public class VisionAction extends AutoAction {
    public boolean isCameraInitialized = false;
    public int route;

    public VisionAction(Chassis chassis) {
        super(chassis);
        route = -1;
        vision = new VisionPipeline(chassis);
    }

    public VisionAction tick() {
        if (!isCameraInitialized) {
            chassis.telemetry.addData("Running", "VisionAction");
            try {
                chassis.camera.setPipeline(vision);
                chassis.camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
                    @Override
                    public void onOpened() {
                        chassis.camera.startStreaming(VisionPipeline.IMAGE_WIDTH, VisionPipeline.IMAGE_HEIGHT, OpenCvCameraRotation.UPRIGHT);
                    }

                    @Override
                    public void onError(int errorCode) {
                    }
                });
            } catch (Exception e) {}
        }
        route = vision.route;
        if (route != -1) {
            chassis.camera.closeCameraDeviceAsync(() -> {
            });
        }
    }
}
