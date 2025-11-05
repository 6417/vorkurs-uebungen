
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

public class ShooterSubsystem extends SubsystemBase {

    // Declare all variables used in the subsystem

    private FridoSparkMax motorShootR;
    private FridoSparkMax motorShootL;
    private FridoSparkMax motorShootFront;

    //private double speed = Constants.Drive.normalSpeed;

    // The constructor of the subsystem
    public ShooterSubsystem() {
        configMotors();
    }

    private void configMotors() {
        // Instantiate motors
        motorShootR = new FridoSparkMax(Constants.Shooter.motorShootR_ID);
        motorShootL = new FridoSparkMax(Constants.Shooter.motorShootL_ID);
        motorShootFront = new FridoSparkMax(Constants.Shooter.motorShootFront_ID);


        // Configure the motors

        motorShootR.follow(motorShootL, DirectionType.followMaster);

        motorShootR.setInverted(true);
        motorShootFront.setInverted(true);

    }

    public void shootBack(double speed) {
        motorShootR.set(speed);
    }

    public void shootFront(double speed) {
      motorShootFront.set(speed);
  }

    @Override
    public void periodic() {
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
    }
}
