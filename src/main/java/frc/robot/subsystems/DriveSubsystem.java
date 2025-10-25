
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.fridowpi.motors.FridoSparkMax;
import frc.fridowpi.motors.FridolinsMotor.DirectionType;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.DriveCommand;

public class DriveSubsystem extends SubsystemBase {

    // Declare all variables used in the subsystem
    private DifferentialDrive tank;

    private FridoSparkMax motorL;
    private FridoSparkMax motorL2;
    private FridoSparkMax motorR;
    private FridoSparkMax motorR2;

    private double speed = Constants.Drive.normalSpeed;

    // The constructor of the subsystem
    public DriveSubsystem() {
        configMotors();
        setDefaultCommand(new DriveCommand(this));
    }

    private void configMotors() {
        // Instantiate motors
        motorL = new FridoSparkMax(Constants.Drive.motorL_ID);
        motorL2 = new FridoSparkMax(Constants.Drive.motorL2_ID);
        motorR = new FridoSparkMax(Constants.Drive.motorR_ID);
        motorR2 = new FridoSparkMax(Constants.Drive.motorR2_ID);

        // Configure the motors

        motorL2.follow(motorL, DirectionType.followMaster);
        motorR2.follow(motorR, DirectionType.followMaster);

        motorL.setInverted(true);
        motorR.setInverted(true);

        // Create the tankdrive object
        tank = new DifferentialDrive(motorL, motorR);

        // Reset the odometry of the robot
        resetSensors();
    }

    public void resetSensors() {
        motorL.setEncoderPosition(0);
        motorR.setEncoderPosition(0);
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
