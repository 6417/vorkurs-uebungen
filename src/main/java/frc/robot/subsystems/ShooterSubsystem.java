// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.subsystems;

import org.littletonrobotics.junction.Logger;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.units.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.Shooter;

public class ShooterSubsystem extends SubsystemBase {

    private SparkMax shooterMotorLeft;
    private SparkMax shooterMotorRight;
    private SparkMax feederMotor;

    public ShooterSubsystem() {
        // Initialize all motors
        /*
         * TODO:
         * - Instanziere den Feeder-Motor
         */

        shooterMotorLeft = new SparkMax(Constants.Shooter.shooterLeftMotorId, SparkLowLevel.MotorType.kBrushless);
        shooterMotorRight = new SparkMax(Constants.Shooter.shooterRightMotorId, SparkLowLevel.MotorType.kBrushless);
        feederMotor = new SparkMax(Constants.Shooter.feederMotorId, SparkLowLevel.MotorType.kBrushless);

        SparkMaxConfig feederConfig = new SparkMaxConfig();
        feederConfig.apply(SparkMaxConfig.Presets.REV_NEO);
        feederConfig.smartCurrentLimit(5,5);
        feederMotor.configure(feederConfig, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);


        
        SparkMaxConfig shooterLeftConfig = new SparkMaxConfig();
        shooterLeftConfig.apply(SparkMaxConfig.Presets.REV_NEO);
        shooterLeftConfig.smartCurrentLimit(15,20);
        shooterLeftConfig.inverted(true);
        shooterLeftConfig.closedLoop.pid(
          Constants.Shooter.velocityPidShooter.kP, Constants.Shooter.velocityPidShooter.kI, Constants.Shooter.velocityPidShooter.kD
        );
        shooterLeftConfig.closedLoop.feedForward.kS(Constants.Shooter.velocityffShooter.kS);
        shooterLeftConfig.closedLoop.feedForward.kV(Constants.Shooter.velocityffShooter.kV);
        shooterMotorLeft.configure(shooterLeftConfig, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);

        SparkMaxConfig shooterRightConfig = new SparkMaxConfig();
        shooterRightConfig.apply(SparkMaxConfig.Presets.REV_NEO);
        shooterRightConfig.smartCurrentLimit(15,20);
        shooterRightConfig.inverted(false);
         shooterRightConfig.closedLoop.pid(
          Constants.Shooter.velocityPidShooter.kP, Constants.Shooter.velocityPidShooter.kI, Constants.Shooter.velocityPidShooter.kD
        );
        shooterRightConfig.closedLoop.feedForward.kS(Constants.Shooter.velocityffShooter.kS);
        shooterRightConfig.closedLoop.feedForward.kV(Constants.Shooter.velocityffShooter.kV);
        shooterRightConfig.follow(shooterMotorLeft, true);
        shooterMotorRight.configure(shooterRightConfig, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
    }
    

    public void shoot(double speedLeft) {
      //shooterMotorLeft.set(speedLeft);
      shooterMotorLeft.getClosedLoopController().setSetpoint(Constants.Shooter.ShootSpeedRPM, ControlType.kVelocity);
      shooterMotorRight.getClosedLoopController().setSetpoint(Constants.Shooter.ShootSpeedRPM, ControlType.kVelocity);
    }

    public void stopShooting() {
      shooterMotorLeft.stopMotor();
      shooterMotorRight.stopMotor();

    }

    public void runFeeder(double speed) {
      //feederMotor.set(speed);
     
    }

    public void stopFeeder() {
      feederMotor.stopMotor();
    }

    public double getCurrentOutput() {
      return feederMotor.getOutputCurrent();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        Logger.recordOutput("Shooter/MotorLeftRPM", shooterMotorLeft.getEncoder().getVelocity());
        Logger.recordOutput("Shooter/Current", this.getCurrentOutput());
        Logger.recordOutput("Shooter/MotorRightRPM", shooterMotorRight.getEncoder().getVelocity());
        Logger.recordOutput("Shooter/MotorFeederRPM", feederMotor.getEncoder().getVelocity());

        Logger.recordOutput("Shooter/MotorLeftCurrent", shooterMotorLeft.getOutputCurrent());
        Logger.recordOutput("Shooter/MotorRightCurrent", shooterMotorRight.getOutputCurrent());
        Logger.recordOutput("Shooter/MotorFeederCurrent", feederMotor.getOutputCurrent());
        Logger.recordOutput("Shooter/MotorFeederAppliedOutput", feederMotor.getAppliedOutput());
        

    }
}
