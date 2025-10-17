
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.DriveCommand;

public class DriveSubsystem extends SubsystemBase {

    // Declare all variables used in the subsystem
    private DifferentialDrive tank;

    private SparkMax motorL;
    private SparkMax motorL2;
    private SparkMax motorR;
    private SparkMax motorR2;

    private SparkMaxConfig motorLconfig;
    private SparkMaxConfig motorL2config;
    private SparkMaxConfig motorRconfig;
    private SparkMaxConfig motorR2config;

    private RelativeEncoder encoderL;
    private RelativeEncoder encoderR;

    private double speed = Constants.Drive.normalSpeed;

    // The constructor of the subsystem
    public DriveSubsystem() {
        configMotors();
        setDefaultCommand(new DriveCommand(this));
    }

    private void configMotors() {
        // Instantiate motors
        motorL = new SparkMax(Constants.Drive.motorL_ID, MotorType.kBrushless);
        motorL2 = new SparkMax(Constants.Drive.motorL2_ID, MotorType.kBrushless);
        motorR = new SparkMax(Constants.Drive.motorR_ID, MotorType.kBrushless);
        motorR2 = new SparkMax(Constants.Drive.motorR2_ID, MotorType.kBrushless);

        // Configure the motors
        motorLconfig = new SparkMaxConfig();
        motorL2config = new SparkMaxConfig();
        motorRconfig = new SparkMaxConfig();
        motorR2config = new SparkMaxConfig();

        motorL2config.follow(motorL);
        motorR2config.follow(motorR);

        motorLconfig.inverted(true);
        motorRconfig.inverted(true);

        encoderL = motorL.getEncoder();
        encoderR = motorR.getEncoder();

        motorL.configure(motorLconfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        motorL2.configure(motorL2config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        motorR.configure(motorRconfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        motorR2.configure(motorR2config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        // Create the tankdrive object
        tank = new DifferentialDrive(motorL, motorR);

        // Reset the odometry of the robot
        resetSensors();
    }

    public void resetSensors() {
        encoderL.setPosition(0);
        encoderR.setPosition(0);
    }

    public void drive() {
        tank.arcadeDrive(-RobotContainer.m_controls.driveJoystick.getX() * this.speed,
                RobotContainer.m_controls.driveJoystick.getY() * this.speed);
    }

    @Override
    public void periodic() {
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
    }
}
