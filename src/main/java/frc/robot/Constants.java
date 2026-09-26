package frc.robot;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;

public class Constants {
    // Set to true during tuning sessions; false for competition.
    // When true, shooter RPMs are read live from the dashboard instead of interpolation tables.
    public static final boolean TUNING_MODE = false;

    public static final class Field {
        public static final double FIELD_LENGTH_METERS = 16.540988;
        public static final double FIELD_WIDTH_METERS = 8.069326;
        public static final double FIELD_WIDTH_INCHES = 317.69;
        public static final double FIELD_LENGTH_INCHES = 651.22;

        public static final Pose2d HUB_CENTER_BLUE = new Pose2d(
                Units.inchesToMeters(23.5 + 158.6),
                Units.inchesToMeters(Field.FIELD_WIDTH_INCHES / 2),
                null);

        public static final Pose2d HUB_CENTER_RED = new Pose2d(
                Units.inchesToMeters(Field.FIELD_LENGTH_INCHES - (23.5 + 158.6)), // X= 11.915 meters
                Units.inchesToMeters(Field.FIELD_WIDTH_INCHES / 2), // Y= 4.032 meters
                null);

        public static final double RADIUS_TO_HUB = 3.0; // in meters
        public static final double START_NEUTRALZONE_INCHES = 177.17;

        // Manual fallback pose for odometry reset button.
        // Fill in the real field position where the robot will be placed before pressing the button.
        // Units:
        // x/y in meters, heading in degrees as Rotation2d.
        // to reset, goto right side of the field in team zone, then reset
        public static final Pose2d ODOMETRY_SET_POSE_RED = new Pose2d(
                Field.FIELD_LENGTH_METERS - 0.498,
                7.532,
                Rotation2d.fromDegrees(180.0));

        public static final Pose2d ODOMETRY_SET_POSE_BLUE = new Pose2d(
                0.498,
                0.498,
                Rotation2d.fromDegrees(0.0));

        // to be set in Robot.java based on alliance
        public static Pose2d EDGERight;
        public static Pose2d EDGELeft;
        public static Pose2d HUB_CENTER;
        public static double neutralZoneStartX;       
    }
    public static final class Joystick {
        public static final int driveJoystickId = 0;
        public static final int operatorJoystickId = 1;
        public static final int idCounterStart = 1000;
        public static final double lt_rt_reshold = 0.2;
    }

    public static final class Gyro {
        public static final int PIGEON_ID = 0;
    }

    public static final class SwerveSubsystem {
        public static final double maxSpeed = 4.9; // TODO: for testing
        public static final double moduleXoffset = 0.262;
        public static final double moduleYoffset = 0.262;
        public static final double maxTurnSpeed = 10;// 12// Math.hypot(moduleXoffset, moduleYoffset) * maxSpeed /
                                                     // (Math.PI *
                                                     // 2); // rps
        public static final boolean oldTurnSystem = true;
        public static final SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(0.15, 2.2, 0);

    }
    public static final class Controls {
        public static final double deadBandDrive = 0.08;
        public static final double deadBandTurn = 0.08;
    }

    public static final class Shooter {
        public static final int topMotorId = ;
        public static final int bottomMotorId = ;
        public static int feederMotorId = 31;

        public static final double shooterPercent = 0.6;
        public static final double feederPercent = 0.3;
    }

}
