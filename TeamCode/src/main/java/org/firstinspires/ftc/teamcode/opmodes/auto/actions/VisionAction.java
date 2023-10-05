package org.firstinspires.ftc.teamcode.opmodes.auto.actions;

import com.acmerobotics.dashboard.FtcDashboard;

import org.firstinspires.ftc.teamcode.opmodes.auto.classes.AutoAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.pipelines.VisionPipeline;
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
        chassis.telemetry.addData("Running", "VisionAction");
        try {
            VisionPipeline vision = new VisionPipeline(chassis);
            chassis.camera.setPipeline(vision);
            chassis.camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
                @Override
                public void onOpened() {
                    chassis.camera.startStreaming(VisionPipeline.IMAGE_WIDTH, VisionPipeline.IMAGE_HEIGHT, OpenCvCameraRotation.UPRIGHT);
                    FtcDashboard ftcDashboard = FtcDashboard.getInstance();
                    ftcDashboard.startCameraStream(chassis.camera, 30);
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
            chassis.telemetry.addData("Finalized route", route);
            chassis.telemetry.update();
            chassis.camera.closeCameraDeviceAsync(() -> {
                // well also do nothing here
            });
            return null;
        } catch (Exception e) {}
        return null;
    }
}
