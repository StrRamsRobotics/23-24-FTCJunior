package org.firstinspires.ftc.teamcode.wrappers;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
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

    public static final boolean TANK_DRIVE = false;
    public static final boolean TWO_WHEELED = true;

    public static final double POWER_DISTANCE_MULTIPLIER = 2.0;
    public static final double POWER_ANGLE_MULTIPLIER = 2.0;
    public static final int ROBOT_WIDTH = 18;

    public static final int TICKS_PER_ROTATION = 288; // or 4

    public DcMotorEx fr, fl, br, bl;
    public DcMotorEx arm, pivot, roller;
    public Servo flap;
    public BNO055IMU imu;
    public OpenCvCamera camera;
    public HardwareMap map;

    public Chassis(HardwareMap map) {
        this.map = map;
        initializeIMU();
        initializeMotors();
        initializeCamera();
    }

    public void initializeIMU() {
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        imu = map.get(BNO055IMU.class, IMU_NAME);
        imu.initialize(parameters);
    }

    public void initializeMotors() {
        fr = map.get(DcMotorEx.class, FR_NAME);
        fl = map.get(DcMotorEx.class, FL_NAME);
        if (!TWO_WHEELED) {
            br = map.get(DcMotorEx.class, BR_NAME);
            bl = map.get(DcMotorEx.class, BL_NAME);
        }
        arm = map.get(DcMotorEx.class, ARM_NAME);
        pivot = map.get(DcMotorEx.class, PIVOT_NAME);
        roller = map.get(DcMotorEx.class, ROLLER_NAME);
        flap = map.get(Servo.class, FLAP_NAME);

        fr.setDirection(DcMotorSimple.Direction.REVERSE);
        if (!TWO_WHEELED) {
            br.setDirection(DcMotorSimple.Direction.REVERSE);
        }

        arm.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        pivot.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        roller.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        pivot.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        roller.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

//        fr.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
//        fl.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
//
//        if (!TWO_WHEELED) {
//            br.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
//            bl.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
//        }
//
//        fr.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
//        fl.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
//
//        if (!TWO_WHEELED) {
//            br.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
//            bl.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
//        }
    }

    public void initializeCamera() {
        int cameraMonitorViewId = map.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", map.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(map.get(WebcamName.class, CAMERA_NAME), cameraMonitorViewId);
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

    public void moveChassis(double power, int distance) {
        // encoders are not used. must use time

        fr.setPower(power);
        fl.setPower(power);

        if (!TWO_WHEELED) {
            br.setPower(power);
            bl.setPower(power);
        }

        try {
            Thread.sleep((long) (distance / (Math.abs(power) * POWER_DISTANCE_MULTIPLIER) * 1000));

            fr.setPower(0);
            fl.setPower(0);

            if (!TWO_WHEELED) {
                br.setPower(0);
                bl.setPower(0);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void turnChassis(double power, int angle) {
        // encoders are not used. must use time

        fr.setPower(power);
        fl.setPower(-power);

        if (!TWO_WHEELED) {
            br.setPower(power);
            bl.setPower(-power);
        }

        int distance = (int) (Math.PI * ROBOT_WIDTH * angle / 360);

        try {
            Thread.sleep((long) (distance / (Math.abs(power) * POWER_DISTANCE_MULTIPLIER) * 1000));

            fr.setPower(0);
            fl.setPower(0);

            if (!TWO_WHEELED) {
                br.setPower(0);
                bl.setPower(0);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    public void turnArm(double power, int angle) {
//        arm.setPower(power);
//
//        try {
//            Thread.sleep((long) (angle / (Math.abs(power) * POWER_ANGLE_MULTIPLIER) * 1000));
//
//            arm.setPower(0);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

    public void turnArm(double power, int angle) {
        arm.setTargetPosition(angle / 360 * TICKS_PER_ROTATION);
        arm.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        arm.setPower(power);
        while (arm.isBusy()) {}
        arm.setPower(0);
        arm.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
    }


    public void turnPivot(double power, int angle) {
        pivot.setPower(power);

        try {
            Thread.sleep((long) (angle / (Math.abs(power) * POWER_ANGLE_MULTIPLIER) * 1000));

            pivot.setPower(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void turnRoller(double power, double seconds) {
        roller.setPower(power);

        try {
            Thread.sleep((long) (seconds * 1000));

            roller.setPower(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void turnFlap(double position, double seconds) {
        flap.setPosition(position);

        try {
            Thread.sleep((long) (seconds * 1000));

            flap.setPosition(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
