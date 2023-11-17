package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static final int TILE_SIZE = 70/3;
    public static final int FIELD_WIDTH = 6*TILE_SIZE;
    public static final int FIELD_HEIGHT = 6*TILE_SIZE;
    public static final int FIELD_HALF_WIDTH = FIELD_WIDTH/2;
    public static final int FIELD_HALF_HEIGHT = FIELD_HEIGHT/2;
    public static final int PATH = 0;
    public static final int TYPE = 4;


    public static void main(String[] args) {
        // Declare a MeepMeep instance
        // With a field size of 800 pixels
        MeepMeep meepMeep = new MeepMeep(600).setAxesInterval(70/3);

        RoadRunnerBotEntity myBot = null;

        switch(TYPE) {
            case 1: // LB
                switch (PATH) {
                    case 0:
                        myBot = new DefaultBotBuilder(meepMeep)
                                // Required: Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                                // Option: Set theme. Default = ColorSchemeRedDark()
                                .setColorScheme(new ColorSchemeRedDark())
                                .followTrajectorySequence(drive ->
                                        drive.trajectorySequenceBuilder(new Pose2d(FIELD_HALF_WIDTH - 2.5 * TILE_SIZE, FIELD_HALF_WIDTH - 0, Math.toRadians(-90)))
                                                .turn(Math.toRadians(-153.434948822922))
                                                .lineTo(new Vector2d(FIELD_HALF_WIDTH - 1.75 * TILE_SIZE, FIELD_HALF_WIDTH - 1.5 * TILE_SIZE))
                                                .turn(Math.toRadians(81.869897645844))
                                                .lineTo(new Vector2d(FIELD_HALF_WIDTH - 1 * TILE_SIZE, FIELD_HALF_WIDTH - 1.25 * TILE_SIZE))

                                                .build()
                                );
                        break;
                    case 1:
                        myBot = new DefaultBotBuilder(meepMeep)
                                // Required: Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                                // Option: Set theme. Default = ColorSchemeRedDark()
                                .setColorScheme(new ColorSchemeRedDark())
                                .followTrajectorySequence(drive ->
                                        // (2, 2.5) -> (-2.5, -2)
                                        drive.trajectorySequenceBuilder(new Pose2d(FIELD_HALF_WIDTH - 2.5 * TILE_SIZE, FIELD_HALF_WIDTH - 0, Math.toRadians(-90)))
                                                .turn(Math.toRadians(-180))
                                                .lineTo(new Vector2d(FIELD_HALF_WIDTH - 2.5 * TILE_SIZE, FIELD_HALF_WIDTH - 2 * TILE_SIZE))
                                                .turn(Math.toRadians(108.43494882292201))
                                                .lineTo(new Vector2d(FIELD_HALF_WIDTH - 1 * TILE_SIZE, FIELD_HALF_WIDTH - 1.5 * TILE_SIZE))

                                                .build()
                                );
                        break;
                    case 2:
                        myBot = new DefaultBotBuilder(meepMeep)
                                // Required: Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                                // Option: Set theme. Default = ColorSchemeRedDark()
                                .setColorScheme(new ColorSchemeRedDark())
                                .followTrajectorySequence(drive ->
                                        drive.trajectorySequenceBuilder(new Pose2d(FIELD_HALF_WIDTH - 2.5 * TILE_SIZE, FIELD_HALF_WIDTH - 0, Math.toRadians(-90)))
                                                .turn(Math.toRadians(-45))
                                                .turn(Math.toRadians(-149.03624346792648))
                                                .lineTo(new Vector2d(FIELD_HALF_WIDTH - 3 * TILE_SIZE, FIELD_HALF_WIDTH - 2 * TILE_SIZE))
                                                .turn(Math.toRadians(111.16125981682828))
                                                .lineTo(new Vector2d(FIELD_HALF_WIDTH - 1 * TILE_SIZE, FIELD_HALF_WIDTH - 1.75 * TILE_SIZE))

                                                .build()
                                );
                        break;
                }
                break;
            case 2: // RB
                switch (PATH) {
                    case 0:
                        myBot = new DefaultBotBuilder(meepMeep)
                                // Required: Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                                // Option: Set theme. Default = ColorSchemeRedDark()
                                .setColorScheme(new ColorSchemeRedDark())
                                .followTrajectorySequence(drive ->
                                        drive.trajectorySequenceBuilder(new Pose2d(FIELD_HALF_WIDTH - 4.5 * TILE_SIZE, FIELD_HALF_WIDTH - 0 * TILE_SIZE, 0))
                                                .turn(Math.toRadians(-45))
                                                .turn(Math.toRadians(-225))
                                                .lineTo(new Vector2d(FIELD_HALF_WIDTH - 4.5 * TILE_SIZE, FIELD_HALF_WIDTH - 2 * TILE_SIZE))
                                                .lineTo(new Vector2d(FIELD_HALF_WIDTH - 4.5 * TILE_SIZE, FIELD_HALF_WIDTH - 2.5 * TILE_SIZE))
                                                .turn(Math.toRadians(90))
                                                .lineTo(new Vector2d(FIELD_HALF_WIDTH - 2 * TILE_SIZE, FIELD_HALF_WIDTH - 2.5 * TILE_SIZE))
                                                .turn(Math.toRadians(51.34019174590992))
                                                .lineTo(new Vector2d(FIELD_HALF_WIDTH - 1 * TILE_SIZE, FIELD_HALF_WIDTH - 1.25 * TILE_SIZE))
                                                .turn(Math.toRadians(38.65980825409008))
                                                .lineTo(new Vector2d(FIELD_HALF_WIDTH - 1 * TILE_SIZE, FIELD_HALF_WIDTH - 0.5 * TILE_SIZE))

                                                .build()
                                );
                        break;
                    case 1:
                        myBot = new DefaultBotBuilder(meepMeep)
                                // Required: Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                                // Option: Set theme. Default = ColorSchemeRedDark()
                                .setColorScheme(new ColorSchemeRedDark())
                                .followTrajectorySequence(drive ->
                                        drive.trajectorySequenceBuilder(new Pose2d(FIELD_HALF_WIDTH - 4.5 * TILE_SIZE, FIELD_HALF_WIDTH - 0 * TILE_SIZE, 0))
                                                .turn(Math.toRadians(90))
                                                .lineTo(new Vector2d(FIELD_HALF_WIDTH - 4.5 * TILE_SIZE, FIELD_HALF_WIDTH - 2 * TILE_SIZE))
                                                .lineTo(new Vector2d(FIELD_HALF_WIDTH - 4.5 * TILE_SIZE, FIELD_HALF_WIDTH - 2.5 * TILE_SIZE))
                                                .turn(Math.toRadians(90))
                                                .lineTo(new Vector2d(FIELD_HALF_WIDTH - 2 * TILE_SIZE, FIELD_HALF_WIDTH - 2.5 * TILE_SIZE))
                                                .turn(Math.toRadians(45))
                                                .lineTo(new Vector2d(FIELD_HALF_WIDTH - 1 * TILE_SIZE, FIELD_HALF_WIDTH - 1.5 * TILE_SIZE))
                                                .turn(Math.toRadians(45))
                                                .lineTo(new Vector2d(FIELD_HALF_WIDTH - 1 * TILE_SIZE, FIELD_HALF_WIDTH - 0.5 * TILE_SIZE))

                                                .build()
                                );
                        break;
                    case 2:
                        myBot = new DefaultBotBuilder(meepMeep)
                                // Required: Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                                // Option: Set theme. Default = ColorSchemeRedDark()
                                .setColorScheme(new ColorSchemeRedDark())
                                .followTrajectorySequence(drive ->
                                        drive.trajectorySequenceBuilder(new Pose2d(FIELD_HALF_WIDTH - 4.5 * TILE_SIZE, FIELD_HALF_WIDTH - 0 * TILE_SIZE, Math.toRadians(-90)))
                                                .turn(Math.toRadians(-45))
                                                .turn(Math.toRadians(-135.0))
                                                .lineTo(new Vector2d(FIELD_HALF_WIDTH - 4.5 * TILE_SIZE, FIELD_HALF_WIDTH - 2 * TILE_SIZE))
                                                .lineTo(new Vector2d(FIELD_HALF_WIDTH - 4.5 * TILE_SIZE, FIELD_HALF_WIDTH - 2.5 * TILE_SIZE))
                                                .turn(Math.toRadians(90))
                                                .lineTo(new Vector2d(FIELD_HALF_WIDTH - 2 * TILE_SIZE, FIELD_HALF_WIDTH - 2.5 * TILE_SIZE))
                                                .turn(Math.toRadians(36.86989764584402))
                                                .lineTo(new Vector2d(FIELD_HALF_WIDTH - 1 * TILE_SIZE, FIELD_HALF_WIDTH - 1.75 * TILE_SIZE))
                                                .turn(Math.toRadians(53.13010235415598))
                                                .lineTo(new Vector2d(FIELD_HALF_WIDTH - 1 * TILE_SIZE, FIELD_HALF_WIDTH - 0.5 * TILE_SIZE))

                                                .build()
                                );
                        break;
                }
                break;
            case 3: // LR
                switch (PATH) {
                    case 0:
                        myBot = new DefaultBotBuilder(meepMeep)
                                // Required: Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                                // Option: Set theme. Default = ColorSchemeRedDark()
                                .setColorScheme(new ColorSchemeRedDark())
                                .followTrajectorySequence(drive ->
                                        drive.trajectorySequenceBuilder(new Pose2d(FIELD_HALF_WIDTH - 4.5 * TILE_SIZE, FIELD_HALF_WIDTH - 0 * TILE_SIZE, Math.toRadians(-90)))
                                                .turn(Math.toRadians(45))
                                                .turn(Math.toRadians(-225.0))
                                                .lineTo(new Vector2d(FIELD_HALF_WIDTH - 4.5 * TILE_SIZE, FIELD_HALF_WIDTH - 2 * TILE_SIZE))
                                                .lineTo(new Vector2d(FIELD_HALF_WIDTH - 4.5 * TILE_SIZE, FIELD_HALF_WIDTH - 2.5 * TILE_SIZE))
                                                .turn(Math.toRadians(90))
                                                .lineTo(new Vector2d(FIELD_HALF_WIDTH - 2 * TILE_SIZE, FIELD_HALF_WIDTH - 2.5 * TILE_SIZE))
                                                .turn(Math.toRadians(51.34019174590992))
                                                .lineTo(new Vector2d(FIELD_HALF_WIDTH - 1 * TILE_SIZE, FIELD_HALF_WIDTH - 1.25 * TILE_SIZE))
                                                .turn(Math.toRadians(38.65980825409008))
                                                .lineTo(new Vector2d(FIELD_HALF_WIDTH - 1 * TILE_SIZE, FIELD_HALF_WIDTH - 0.5 * TILE_SIZE))

                                                .build()
                                );
                        break;
                    case 1:
                        myBot = new DefaultBotBuilder(meepMeep)
                                // Required: Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                                // Option: Set theme. Default = ColorSchemeRedDark()
                                .setColorScheme(new ColorSchemeRedDark())
                                .followTrajectorySequence(drive ->
                                        drive.trajectorySequenceBuilder(new Pose2d(FIELD_HALF_WIDTH - 4.5 * TILE_SIZE, FIELD_HALF_WIDTH - 0 * TILE_SIZE, 0))
                                                .lineTo(new Vector2d(FIELD_HALF_WIDTH - 4.5 * TILE_SIZE, FIELD_HALF_WIDTH - 2 * TILE_SIZE))
                                                .lineTo(new Vector2d(FIELD_HALF_WIDTH - 4.5 * TILE_SIZE, FIELD_HALF_WIDTH - 2.5 * TILE_SIZE))
                                                .lineTo(new Vector2d(FIELD_HALF_WIDTH - 2 * TILE_SIZE, FIELD_HALF_WIDTH - 2.5 * TILE_SIZE))
                                                .lineTo(new Vector2d(FIELD_HALF_WIDTH - 1 * TILE_SIZE, FIELD_HALF_WIDTH - 1.5 * TILE_SIZE))
                                                .lineTo(new Vector2d(FIELD_HALF_WIDTH - 1 * TILE_SIZE, FIELD_HALF_WIDTH - 0.5 * TILE_SIZE))

                                                .build()
                                );
                        break;
                    case 2:
                        myBot = new DefaultBotBuilder(meepMeep)
                                // Required: Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                                // Option: Set theme. Default = ColorSchemeRedDark()
                                .setColorScheme(new ColorSchemeRedDark())
                                .followTrajectorySequence(drive ->
                                        drive.trajectorySequenceBuilder(new Pose2d(FIELD_HALF_WIDTH - 4.5 * TILE_SIZE, FIELD_HALF_WIDTH - 0 * TILE_SIZE, 0))
                                                .lineTo(new Vector2d(FIELD_HALF_WIDTH - 4.5 * TILE_SIZE, FIELD_HALF_WIDTH - 2 * TILE_SIZE))
                                                .lineTo(new Vector2d(FIELD_HALF_WIDTH - 4.5 * TILE_SIZE, FIELD_HALF_WIDTH - 2.5 * TILE_SIZE))
                                                .lineTo(new Vector2d(FIELD_HALF_WIDTH - 2 * TILE_SIZE, FIELD_HALF_WIDTH - 2.5 * TILE_SIZE))
                                                .lineTo(new Vector2d(FIELD_HALF_WIDTH - 1 * TILE_SIZE, FIELD_HALF_WIDTH - 1.75 * TILE_SIZE))
                                                .lineTo(new Vector2d(FIELD_HALF_WIDTH - 1 * TILE_SIZE, FIELD_HALF_WIDTH - 0.5 * TILE_SIZE))

                                                .build()
                                );
                        break;
                }
                break;
            case 4: // RR
                switch (PATH) {
                    case 0:
                        myBot = new DefaultBotBuilder(meepMeep)
                                // Required: Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                                // Option: Set theme. Default = ColorSchemeRedDark()
                                .setColorScheme(new ColorSchemeRedDark())
                                .followTrajectorySequence(drive ->
                                        drive.trajectorySequenceBuilder(new Pose2d(FIELD_HALF_WIDTH - 2.5 * TILE_SIZE, FIELD_HALF_WIDTH - 6 * TILE_SIZE, Math.toRadians(-90)))
                                                .turn(Math.toRadians(-26.565051177077994))
                                                .lineTo(new Vector2d(FIELD_HALF_WIDTH - 1.75 * TILE_SIZE, FIELD_HALF_WIDTH - 4.5 * TILE_SIZE))
                                                .turn(Math.toRadians(-81.86989764584402))
                                                .lineTo(new Vector2d(FIELD_HALF_WIDTH - 1 * TILE_SIZE, FIELD_HALF_WIDTH - 4.75 * TILE_SIZE))
                                                .turn(Math.toRadians(-71.56505117707798))
                                                .lineTo(new Vector2d(FIELD_HALF_WIDTH - 1 * TILE_SIZE, FIELD_HALF_WIDTH - 5.5 * TILE_SIZE))

                                                .build()
                                );
                        break;
                    case 1:
                        myBot = new DefaultBotBuilder(meepMeep)
                                // Required: Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                                // Option: Set theme. Default = ColorSchemeRedDark()
                                .setColorScheme(new ColorSchemeRedDark())
                                .followTrajectorySequence(drive ->
                                        // (2, 2.5) -> (-2.5, -2)
                                        drive.trajectorySequenceBuilder(new Pose2d(FIELD_HALF_WIDTH - 2.5 * TILE_SIZE, FIELD_HALF_WIDTH - 6 * TILE_SIZE, Math.toRadians(-90)))
                                                .lineTo(new Vector2d(FIELD_HALF_WIDTH - 2.5 * TILE_SIZE, FIELD_HALF_WIDTH - 4 * TILE_SIZE))
                                                .turn(Math.toRadians(-108.43494882292201))
                                                .lineTo(new Vector2d(FIELD_HALF_WIDTH - 1 * TILE_SIZE, FIELD_HALF_WIDTH - 4.5 * TILE_SIZE))
                                                .turn(Math.toRadians(-71.56505117707799))
                                                .lineTo(new Vector2d(FIELD_HALF_WIDTH - 1 * TILE_SIZE, FIELD_HALF_WIDTH - 5.5 * TILE_SIZE))

                                                .build()
                                );
                        break;
                    case 2:
                        myBot = new DefaultBotBuilder(meepMeep)
                                // Required: Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                                // Option: Set theme. Default = ColorSchemeRedDark()
                                .setColorScheme(new ColorSchemeRedDark())
                                .followTrajectorySequence(drive ->
                                        drive.trajectorySequenceBuilder(new Pose2d(FIELD_HALF_WIDTH - 2.5 * TILE_SIZE, FIELD_HALF_WIDTH - 6 * TILE_SIZE, 0))
                                                .lineTo(new Vector2d(FIELD_HALF_WIDTH - 2.5 * TILE_SIZE, FIELD_HALF_WIDTH - 4 * TILE_SIZE))
                                                .lineTo(new Vector2d(FIELD_HALF_WIDTH - 1 * TILE_SIZE, FIELD_HALF_WIDTH - 4.25 * TILE_SIZE))
                                                .lineTo(new Vector2d(FIELD_HALF_WIDTH - 1 * TILE_SIZE, FIELD_HALF_WIDTH - 5.5 * TILE_SIZE))

                                                .build()
                                );
                        break;
                }
                break;
        }



        // Set field image
        if (myBot != null) {
            meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                    .setDarkMode(true)
                    // Background opacity from 0-1
                    .setBackgroundAlpha(0.95f)
                    .addEntity(myBot)
                    .start();
        }
    }
}