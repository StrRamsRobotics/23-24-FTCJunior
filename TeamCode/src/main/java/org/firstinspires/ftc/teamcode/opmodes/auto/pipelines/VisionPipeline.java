package org.firstinspires.ftc.teamcode.opmodes.auto.pipelines;

import org.firstinspires.ftc.teamcode.utils.classes.IndexValue;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

public class VisionPipeline extends OpenCvPipeline {
    public static int IMAGE_WIDTH = 1600;
    public static int IMAGE_HEIGHT = 1200;
    public static int MINIMUM_SIZE = 20;
    public static int LEFT = (int) ((1.0/3.0) * IMAGE_WIDTH);
    public static int RIGHT = (int) ((2.0/3.0) * IMAGE_WIDTH);

    public static final int LEFT_PATH = 0;
    public static final int CENTER_PATH = 1;
    public static final int RIGHT_PATH = 2;
    public int route = -1;

    // Red

    public Scalar
        lowerRed = new Scalar(0, 100, 100),
        upperRed = new Scalar(10, 255, 255),
        lowerRed2 = new Scalar(170, 100, 100),
        upperRed2 = new Scalar(180, 255, 255);

    // Blue

    public Scalar
        lowerBlue = new Scalar(110, 100, 100),
        upperBlue = new Scalar(130, 255, 255);

    public Mat
        red = new Mat(),
        red2 = new Mat(),
        blue = new Mat();

    public Mat
        mask = new Mat(),
        hierarchy = new Mat();

    public List<MatOfPoint> contours = new ArrayList<>();

    public VisionPipeline() {
        super();
    }

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, input, Imgproc.COLOR_RGB2HSV);
        Core.inRange(input, lowerRed, upperRed, red);
        Core.inRange(input, lowerRed2, upperRed2, red2);
        Core.inRange(input, lowerBlue, upperBlue, blue);

        Core.bitwise_or(red, red2, mask);
        Core.bitwise_or(blue, mask, mask);

        contours.clear();

        Imgproc.findContours(mask, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
        if (contours.size() > 0){
            IndexValue maxArea = max(contours);
            if (maxArea.value < MINIMUM_SIZE) {
                route = -1;
                return input;
            }
            Rect rect = Imgproc.boundingRect(contours.get(maxArea.index));
            Point center = new Point(rect.x + rect.width / 2.0, rect.y + rect.height / 2.0);
            Imgproc.rectangle(input, rect, new Scalar(255, 0, 0), 10);
            Imgproc.circle(input, center, 10, new Scalar(0, 0, 255), 10);
            if (center.x <= LEFT) {
                route = 0;
            }
            else if (center.x >= RIGHT) {
                route = 2;
            }
            else {
                route = 1;
            }
        }
        Imgproc.cvtColor(input, input, Imgproc.COLOR_HSV2RGB);
        return input;
    }

    public IndexValue max(List<MatOfPoint> contours) {
        double maxArea = 0;
        int maxAreaIdx = 0;
        for (int idx = 0; idx < contours.size(); idx++) {
            Mat contour = contours.get(idx);
            double contourArea = Imgproc.contourArea(contour);
            Rect rect = Imgproc.boundingRect(contour);
            if (contourArea > maxArea && rect.height<2.5*rect.width && rect.height>rect.width) {
                maxArea = contourArea;
                maxAreaIdx = idx;
            }
        }
        return new IndexValue(maxAreaIdx, maxArea);
    }
}
