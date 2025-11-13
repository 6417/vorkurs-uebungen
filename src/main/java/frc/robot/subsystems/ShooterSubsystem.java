
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.fridowpi.motors.FridoSparkMax;
import frc.fridowpi.motors.FridolinsMotor.DirectionType;
import frc.fridowpi.motors.FridolinsMotor.IdleMode;
import frc.fridowpi.motors.utils.PidValues;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.DriveCommand;

public class ShooterSubsystem extends SubsystemBase {

    // Declare all variables used in the subsystem

    private FridoSparkMax motorShootMaster;
    private PidValues shooterVelocityPIDValues;

    //private double speed = Constants.Drive.normalSpeed;

    // The constructor of the subsystem
    public ShooterSubsystem() {
        configMotors();
    }

    private void configMotors() {
        // Instantiate motors
        motorShootMaster = new FridoSparkMax(Constants.Shooter.motorShootR_ID);
        // Configure the motors

        motorShootMaster.setIdleMode(IdleMode.kCoast);

        shooterVelocityPIDValues = new PidValues(0.0001, 0, 0, 0.0002941);
        motorShootMaster.setPID(shooterVelocityPIDValues);
    }

  public void stopMotors() {
    motorShootMaster.stopMotor();
  }
  
  public void setVelocity() {
    motorShootMaster.setVelocity(Constants.Shooter.shooterVelocity);
  }

    @Override
    public void periodic() {
        //motorShootMaster.set(0.5);
        setVelocity();
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
        builder.addDoubleProperty("Motor velocity", ()->motorShootMaster.getEncoderVelocity(), null);
    }
}
