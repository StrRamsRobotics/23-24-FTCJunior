package org.firstinspires.ftc.teamcode.opmodes.auto.actions;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.opmodes.auto.classes.AutoAction;
import org.firstinspires.ftc.teamcode.opmodes.auto.pipelines.AprilTagDetectionPipeline;
import org.firstinspires.ftc.teamcode.opmodes.auto.pipelines.VisionPipeline;
import org.firstinspires.ftc.teamcode.wrappers.Chassis;
import org.firstinspires.ftc.teamcode.wrappers.Game;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.ArrayList;

public class AprilTagAction extends AutoAction {
    OpenCvCamera camera;
    AprilTagDetectionPipeline aprilTagDetectionPipeline;

    public double fx = 1042;
    public double fy = 781;
    public double cx = 800;
    public double cy = 600;

    public double tagsize = 0.166;

    public int numFramesWithoutDetection = 0;

    public static final float DECIMATION_HIGH = 3;
    public static final float DECIMATION_LOW = 2;
    public static final float THRESHOLD_HIGH_DECIMATION_RANGE_METERS = 1.0f;
    public static final int THRESHOLD_NUM_FRAMES_NO_DETECTION_BEFORE_LOW_DECIMATION = 4;

    public static final int LEFT_BLUE_ID = 1;
    public static final int CENTER_BLUE_ID = 2;
    public static final int RIGHT_BLUE_ID = 3;
    public static final int LEFT_RED_ID = 4;
    public static final int CENTER_RED_ID = 5;
    public static final int RIGHT_RED_ID = 6;

    public int team = 0;
    public int route = 0;
    public boolean cameraInitialized = false;

    public AprilTagAction(Chassis chassis, int team, int route) {
        super(chassis);
        this.team = team;
        this.route = route;
    }

    public AprilTagAction tick() {
        if (!cameraInitialized) {
            int cameraMonitorViewId = chassis.map.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", chassis.map.appContext.getPackageName());
            camera = OpenCvCameraFactory.getInstance().createWebcam(chassis.map.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
            aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);

            camera.setPipeline(aprilTagDetectionPipeline);
            camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
                @Override
                public void onOpened() {
                    camera.startStreaming(VisionPipeline.IMAGE_WIDTH, VisionPipeline.IMAGE_HEIGHT, OpenCvCameraRotation.UPRIGHT);
                }

                @Override
                public void onError(int errorCode) {

                }
            });
            cameraInitialized = true;
        }
        ArrayList<AprilTagDetection> detections = aprilTagDetectionPipeline.getDetectionsUpdate();

        if(detections != null) {
            if(detections.size() == 0) {
                numFramesWithoutDetection++;
                if(numFramesWithoutDetection >= THRESHOLD_NUM_FRAMES_NO_DETECTION_BEFORE_LOW_DECIMATION)
                {
                    aprilTagDetectionPipeline.setDecimation(DECIMATION_LOW);
                }
            }
            else {
                numFramesWithoutDetection = 0;
                if(detections.get(0).pose.z < THRESHOLD_HIGH_DECIMATION_RANGE_METERS) {
                    aprilTagDetectionPipeline.setDecimation(DECIMATION_HIGH);
                }
                for(AprilTagDetection detection : detections) {
                    if (
                            detection.id == LEFT_BLUE_ID && route == 0 && team == Game.BLUE_TEAM ||
                            detection.id == CENTER_BLUE_ID && route == 1 && team == Game.BLUE_TEAM ||
                            detection.id == RIGHT_BLUE_ID && route == 2 && team == Game.BLUE_TEAM ||
                            detection.id == LEFT_RED_ID && route == 0 && team == Game.RED_TEAM ||
                            detection.id == CENTER_RED_ID && route == 1 && team == Game.RED_TEAM ||
                            detection.id == RIGHT_RED_ID && route == 2 && team == Game.RED_TEAM
                    ) {
                        if (detection.pose.x >= 10) {

                            return this;
                        } else if (detection.pose.x <= -10) {

                            return this;
                        } else {

                            return null;
                        }
                    }
                }
            }
        }
        return null;
    }
}