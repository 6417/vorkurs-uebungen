// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.security.Key;
import java.time.format.SignStyle;

import org.littletonrobotics.junction.Logger;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {

  private SparkMax feederMotor;
  private SparkFlex shooterMotorTop;
  private SparkFlex shooterMotorBottom;

  public ShooterSubsystem() {
    // Initialize the motors
    feederMotor = new SparkMax(Constants.Shooter.feederMotorId, SparkLowLevel.MotorType.kBrushless);
    shooterMotorTop = new SparkFlex(Constants.Shooter.topMotorId, SparkLowLevel.MotorType.kBrushless);
    shooterMotorBottom = new SparkFlex(Constants.Shooter.bottomMotorId, SparkLowLevel.MotorType.kBrushless);

    // Configure the motors
    SparkMaxConfig feederConfig = new SparkMaxConfig();
    feederConfig.apply(SparkMaxConfig.Presets.REV_NEO);
    feederMotor.configure(feederConfig, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);

    SparkMaxConfig shooterTopConfig = new SparkMaxConfig();
    shooterTopConfig.apply(SparkMaxConfig.Presets.REV_Vortex);
    shooterMotorTop.configure(shooterTopConfig, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
    shooterMotorBottom.configure(shooterTopConfig, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);

  }

  public void runShooter() {
    shooterMotorBottom.set(Constants.Shooter.shooterPercent);
    shooterMotorTop.set(Constants.Shooter.shooterPercent);
  }

  public void stopMotors() {
    shooterMotorBottom.stopMotor();;
    shooterMotorTop.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    Logger.recordOutput("Shooter/FeederMotorVelocity", feederMotor.getEncoder().getVelocity());
    Logger.recordOutput("Shooter/FeederMotorPosition", feederMotor.getEncoder().getPosition());

    Logger.recordOutput("Shooter/TopMotorVelocity", shooterMotorTop.getEncoder().getVelocity());
    Logger.recordOutput("Shooter/TopMotorPosition", shooterMotorTop.getEncoder().getPosition());
    
    /**
     * TODO: Log the position and velocity of the bottom motor
     */
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
