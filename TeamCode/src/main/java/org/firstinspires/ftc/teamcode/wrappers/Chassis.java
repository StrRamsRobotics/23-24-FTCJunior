package org.firstinspires.ftc.teamcode.wrappers;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.CRServoImplEx;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.generic.helpers.LogHelper;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;

public class Chassis {
    public static String IMU_NAME = "imu";
    public static String FR_NAME = "fr";
    public static String FL_NAME = "fl";
    public static String BR_NAME = "br";
    public static String BL_NAME = "bl";
    public static String ARM_NAME = "arm";
    public static String PIVOT_NAME = "pivot";
    public static String ROLLER_NAME = "roller";
    public static String FLAP_NAME = "flap";
    public static String CAMERA_NAME = "camera";
    public static String LAUNCHER_NAME = "launcher";
    public static String HANG_NAME = "hang";

//    public static final boolean TANK_DRIVE = false; // useless now because theres two opmodes
    public static final boolean TWO_WHEELED = true;
    public static final boolean HAS_CHASSIS_ENCODERS = false;

    public static final boolean HAS_ARM = false;
    public static final boolean HAS_PIVOT = false;
    public static final boolean HAS_ROLLER = false;
    public static final boolean HAS_FLAP = false;
    public static final boolean HAS_LAUNCHER = false;

    public static final boolean IS_FLAP_CR = false;

    public static final boolean HAS_HANG = false;
            ;
    public static final double MOVE_POWER = 0.5;
    public static final double VOLTAGE_Y_INTERCEPT = 1.25;
    public static final double VOLTAGE_SLOPE = -0.06;

    public static final double TELEOP_MOVE_POWER = 0.5;
    public static final double SLOW_MOVE_POWER = 0.25; // only apriltag, deprecated
    public static final double ARM_POWER = 0.75;
    public static final double PIVOT_POWER = 1;
    public static final double ROLLER_POWER = 1;
    public static final double LAUNCHER_MOTOR_POWER = -0.1;

    public static final double ROBOT_WIDTH = 18; // inches
    public static final double HRW = ROBOT_WIDTH / 2; // inches
    public static final double RWI = ROBOT_WIDTH / 2; // inches, robot width until intake / shovel front, will need to adjust based on build
    public static final double ROBOT_LENGTH = 18; // inches
    public static final double HRL = ROBOT_LENGTH / 2; // inches
    public static final double INTERMEDIATE_BACKBOARD = 0.25  * Game.TS;
    public static final double MOVE_DISTANCE_PER_SECOND = 45 * MOVE_POWER; // inches
//    public static final double TURN_DISTANCE_PER_SECOND = 60 * MOVE_POWER; // inches

    public static final double TURN_ANGLE_PER_SECOND = 177 * MOVE_POWER; // degrees
    public static final double HEX_ANGLE_PER_SECOND = 180 * ARM_POWER; // degrees
    public static final int CORE_HEX_TICKS_PER_REV = 288;
    public static final int ROLLER_RADIUS = 2; // inches

    public static final int ARM_STRAIGHT_DEGREES = 120;
    public static final int ARM_TURN_DEGREES = 30;
    public static final int FLAP_OPEN_TIME = 500;
    public static final int FLAP_WAIT_TIME = 1000;
    public static final int ROLLER_WAIT_TIME = 1000;
    public static final int PATH_WAIT_TIME = 800;

    public DcMotorEx fr, fl, br, bl, hang1, hang2;
    public DcMotorEx arm1, arm2, pivot, roller;
    public Servo flap, launcher;
    public CRServoImplEx flapCR;
    public BNO055IMU imu;
    public OpenCvCamera camera;
    public HardwareMap hardwareMap;
    public VoltageSensor voltageSensor;
    public Telemetry telemetry;
    public FtcDashboard ftcDashboard = FtcDashboard.getInstance();
    public LogHelper logHelper = new LogHelper(this);

    public Chassis(HardwareMap hardwareMap, Telemetry telemetry) {
        initializeUtils(hardwareMap, telemetry);
        initializeIMU();
        initializeMotors();
        initializeCamera();
    }

    public void initializeUtils(HardwareMap hardwareMap, Telemetry telemetry) {
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
        this.voltageSensor = hardwareMap.voltageSensor.iterator().next();
    }

    public void initializeIMU() {
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        imu = hardwareMap.get(BNO055IMU.class, IMU_NAME);
        imu.initialize(parameters);
    }

    public void initializeMotors() {
        fr = hardwareMap.get(DcMotorEx.class, FR_NAME);
//        fr.setDirection(DcMotorEx.Direction.REVERSE);
        fr.setDirection(DcMotorEx.Direction.FORWARD); // this is correct
        fl = hardwareMap.get(DcMotorEx.class, FL_NAME);
        fl.setDirection(DcMotorEx.Direction.REVERSE); // this is correct
        if (!TWO_WHEELED) {
            br = hardwareMap.get(DcMotorEx.class, BR_NAME);
//            br.setDirection(DcMotorEx.Direction.REVERSE);
            br.setDirection(DcMotorEx.Direction.FORWARD); // this is correct
            bl = hardwareMap.get(DcMotorEx.class, BL_NAME);
//            bl.setDirection(DcMotorEx.Direction.FORWARD);
            bl.setDirection(DcMotorEx.Direction.REVERSE); // this is correct
        }

        if (HAS_ARM) {
            arm1 = hardwareMap.get(DcMotorEx.class, ARM_NAME + "1");
            arm2 = hardwareMap.get(DcMotorEx.class, ARM_NAME + "2");
            arm1.setDirection(DcMotorEx.Direction.FORWARD);
            arm2.setDirection(DcMotorEx.Direction.REVERSE);
        }
        if (HAS_PIVOT) {
            pivot = hardwareMap.get(DcMotorEx.class, PIVOT_NAME);
            pivot.setDirection(DcMotorEx.Direction.FORWARD);
        }
        if (HAS_ROLLER) {
            roller = hardwareMap.get(DcMotorEx.class, ROLLER_NAME);
            roller.setDirection(DcMotorEx.Direction.FORWARD);
        }
        if (HAS_FLAP) {
            if (IS_FLAP_CR) {
                flapCR = hardwareMap.get(CRServoImplEx.class, FLAP_NAME);
                flapCR.setDirection(CRServoImplEx.Direction.FORWARD);
                flapCR.setPwmEnable();
            }
            else {
                flap = hardwareMap.get(Servo.class, FLAP_NAME);
                flap.setDirection(Servo.Direction.FORWARD);
            }
        }

        if (HAS_CHASSIS_ENCODERS) {
            fr.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
            fl.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

            if (!TWO_WHEELED) {
                br.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
                bl.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
            }

            fr.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
            fl.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

            if (!TWO_WHEELED) {
                br.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
                bl.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
            }
        }

        if (HAS_HANG) {
            hang1 = hardwareMap.get(DcMotorEx.class, HANG_NAME + "1");
            hang2 = hardwareMap.get(DcMotorEx.class, HANG_NAME + "2");
            hang1.setDirection(DcMotorEx.Direction.FORWARD);
            hang2.setDirection(DcMotorEx.Direction.REVERSE);
//            hang1.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
//            hang2.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
//            hang1.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
//            hang2.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        }
        if (HAS_LAUNCHER) {
            launcher = hardwareMap.get(Servo.class, LAUNCHER_NAME);
            launcher.setDirection(Servo.Direction.REVERSE);
        }
    }

    public void initializeCamera() {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, CAMERA_NAME), cameraMonitorViewId);
    }

    public double getHeading() {
        Orientation orientation = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double angle_value = -AngleUnit.DEGREES.fromUnit(orientation.angleUnit, orientation.firstAngle);
        if (Math.abs(angle_value)%360>180) {
            return Math.signum(angle_value)*(Math.abs(angle_value)%360-360);
        } else {
            return Math.signum(angle_value)*(Math.abs(angle_value)%360);
        }
    }

    public double getAngle() {
        Orientation orientation = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double angle_value = -AngleUnit.DEGREES.fromUnit(orientation.angleUnit, orientation.firstAngle);
        if (angle_value < 0) {
            angle_value += 360;
        }
        return angle_value;
    }
}
