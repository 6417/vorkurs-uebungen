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

import edu.wpi.first.units.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.fridowpi.motors.FridoServoMotor;
import frc.robot.Constants;

public class ClimberSubsystem extends SubsystemBase {

  private FridoServoMotor releaseMotor;  
  private SparkMax ratchetMotor;

    public ClimberSubsystem() {

        releaseMotor = new FridoServoMotor(Constants.Climber.releaseMotorId);
        ratchetMotor = new SparkMax(Constants.Climber.ratchetMotorId, SparkLowLevel.MotorType.kBrushless);

        SparkMaxConfig ratchetConfig = new SparkMaxConfig();
        ratchetConfig.apply(SparkMaxConfig.Presets.REV_NEO);
        ratchetMotor.configure(ratchetConfig, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);

    }
    


    
    public double getCurrentOutput() {
      return ratchetMotor.getOutputCurrent();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
