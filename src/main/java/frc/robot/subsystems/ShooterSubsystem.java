// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.subsystems;

import org.littletonrobotics.junction.Logger;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {

    private SparkMax shooterMotorLeft;
    private SparkMax feederMotor;

    public ShooterSubsystem() {
        // Initialize all motors
        /*
         * TODO:
         * - Instanziere den Feeder-Motor
         */

        shooterMotorLeft = new SparkMax(Constants.Shooter.shooterLeftMotorId, SparkLowLevel.MotorType.kBrushless);

        SparkMaxConfig feederConfig = new SparkMaxConfig();
        feederConfig.apply(SparkMaxConfig.Presets.REV_NEO);
        feederMotor.configure(feederConfig, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);

        SparkMaxConfig shooterLeftConfig = new SparkMaxConfig();
        shooterLeftConfig.apply(SparkMaxConfig.Presets.REV_NEO);
        shooterMotorLeft.configure(shooterLeftConfig, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void shoot(double speed) {
      shooterMotorLeft.set(speed);
    }

    public void stopShooting() {
      shooterMotorLeft.stopMotor();
    }

    public void runFeeder(double speed) {
      /*
       * TODO:
       * - Run Feeder
       */
    }

    public void stopFeeder() {
      /*
       * TODO:
       * - Stop Motor
       */
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        Logger.recordOutput("Shooter/MotorLeftRPM", shooterMotorLeft.getEncoder().getVelocity());
        Logger.recordOutput("Shooter/MotorFeederRPM", feederMotor.getEncoder().getVelocity());
    }
}
