// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.subsystems;

import org.littletonrobotics.junction.Logger;

import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {

    private SparkMax shooterMotorLeft;
    private SparkMax shooterMotorRight;
    private SparkMax feederMotor;

    /**
     * Creates a new ExampleSubsystem.
     */
    public ShooterSubsystem() {

        // Initialize all motors
        shooterMotorLeft = new SparkMax(Constants.Shooter.shooterLeftMotorId, SparkLowLevel.MotorType.kBrushless);
        shooterMotorRight = new SparkMax(Constants.Shooter.shooterRightMotorId, SparkLowLevel.MotorType.kBrushless);
        feederMotor = new SparkMax(Constants.Shooter.feederMotorId, SparkLowLevel.MotorType.kBrushless);

    }

    public void runFeeder(double percent) {
      feederMotor.set(percent);
    }

    public void stopFeeder() {
      feederMotor.stopMotor();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        Logger.recordOutput("Shooter/MotorLeftRPM", shooterMotorLeft.getEncoder().getVelocity());
        Logger.recordOutput("Shooter/MotorRightRPM", shooterMotorRight.getEncoder().getVelocity());
        Logger.recordOutput("Shooter/MotorFeederRPM", feederMotor.getEncoder().getVelocity());
    }
}
