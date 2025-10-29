// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.fridowpi.motors.utils.FeedForwardValues;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import frc.fridowpi.motors.FridolinsMotor.LimitSwitchPolarity;
import frc.fridowpi.motors.utils.PidValues;
import frc.robot.subsystems.swervedrive.swerve.ModuleConfig;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class Joystick {
        public static final int driveJoystickId = 0;
    }

    public static final class Coralhandler {
        public static final int angleMotorId = 40;
        public static final int intakeMotorId = 50;
        public static final double speedIntake = -0.2;
        public static final double speedOutput = 0.2;
        public static final double angle = 20;
        public static final double speedAngle = 0.1;
    
        
    }

    public static final class SwerveDrive {
        public static ModuleConfig[] configs = new ModuleConfig[4];
        public static boolean isGyroInverted = false;

        public static final double maxSpeed = 4.9; // TODO: for testing
        public static ModuleConfig defaultModuleConfig2024 = new ModuleConfig();
        public static final double moduleXoffset = 0.267;
        public static final double moduleYoffset = 0.267;
        public static final double maxTurnSpeed = 10;// 12// Math.hypot(moduleXoffset, moduleYoffset) * maxSpeed /
                                                     // (Math.PI *
                                                     // 2); // rps

        static {
            defaultModuleConfig2024.maxSpeed = maxSpeed;
            defaultModuleConfig2024.wheelCircumference = Units.inchesToMeters(4) * Math.PI * 0.977 * 1.058376;

            defaultModuleConfig2024.driveGearboxRatio = 6.181;
            defaultModuleConfig2024.driveMotorStallCurrentLimit = 70;
            defaultModuleConfig2024.driveMotorFreeCurrentLimit = 40;
            defaultModuleConfig2024.drivePidValues = new PidValues(1.1926E-05, 0.00, 0);
            //defaultModuleConfig2024.driveFFValues = new FeedForwardValues(0.13271 / 12, 2.1395 / 12, 0.15313 / 12);
            defaultModuleConfig2024.driveFFValues = new FeedForwardValues(0.057, 0.1177, 0.05);

            defaultModuleConfig2024.angleGearboxRatio = 7.44;
            defaultModuleConfig2024.angleMotorStallCurrentLimit = 35;
            defaultModuleConfig2024.angleMotorFreeCurrentLimit = 20;
            defaultModuleConfig2024.angleMotorIzone = 0.1;
            defaultModuleConfig2024.anglePidValues = new PidValues(1, 0.0, 0.05);

            defaultModuleConfig2024.encoderThicksToRotationFalcon = 1;
            defaultModuleConfig2024.encoderVelocityToRPSFalcon = 1;
            defaultModuleConfig2024.encoderThicksToRotationNEO = 1;
            defaultModuleConfig2024.encoderVelocityToRPSNEO = 1;

            final int LOC_FL = frc.robot.subsystems.swervedrive.SwerveDrive.LOC_FL;
            final int LOC_FR = frc.robot.subsystems.swervedrive.SwerveDrive.LOC_FR;
            final int LOC_RL = frc.robot.subsystems.swervedrive.SwerveDrive.LOC_RL;
            final int LOC_RR = frc.robot.subsystems.swervedrive.SwerveDrive.LOC_RR;

            configs[LOC_FL] = defaultModuleConfig2024.clone();
            configs[LOC_FR] = defaultModuleConfig2024.clone();
            configs[LOC_RL] = defaultModuleConfig2024.clone();
            configs[LOC_RR] = defaultModuleConfig2024.clone();

            configs[LOC_FL].driveMotorID = 1;
            configs[LOC_FL].angleMotorID = 11;
            configs[LOC_FL].driveMotorInverted = false;
            configs[LOC_FL].angleMotorInverted = true;
            configs[LOC_FL].moduleOffset = new Translation2d(moduleXoffset, moduleYoffset);
            configs[LOC_FL].encoderChannel = 0;
            configs[LOC_FL].absEncoderOffset = 0.952;

            configs[LOC_FR].driveMotorID = 2;
            configs[LOC_FR].angleMotorID = 12;
            configs[LOC_FR].driveMotorInverted = false;
            configs[LOC_FR].angleMotorInverted = true;
            configs[LOC_FR].moduleOffset = new Translation2d(moduleXoffset, -moduleYoffset);
            configs[LOC_FR].encoderChannel = 1;
            configs[LOC_FR].absEncoderOffset = 0.490;

            configs[LOC_RL].driveMotorID = 3;
            configs[LOC_RL].angleMotorID = 13;
            configs[LOC_RL].driveMotorInverted = false;
            configs[LOC_RL].angleMotorInverted = true;
            configs[LOC_RL].moduleOffset = new Translation2d(-moduleXoffset, moduleYoffset);
            configs[LOC_RL].encoderChannel = 2;
            configs[LOC_RL].absEncoderOffset = 0.853;

            configs[LOC_RR].driveMotorID = 4;
            configs[LOC_RR].angleMotorID = 14;
            configs[LOC_RR].driveMotorInverted = false;
            configs[LOC_RR].angleMotorInverted = true;
            configs[LOC_RR].moduleOffset = new Translation2d(-moduleXoffset, -moduleYoffset);
            configs[LOC_RR].encoderChannel = 3;
            configs[LOC_RR].absEncoderOffset = 0.199;
        }
    }
}