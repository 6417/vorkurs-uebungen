// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.io.ObjectInputFilter.Config;

import org.ejml.dense.row.decomposition.eig.SwitchingEigenDecomposition_DDRM;

import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.fridowpi.motors.FridoSparkMax;
import frc.fridowpi.motors.FridolinsMotor.LimitSwitchPolarity;
import frc.robot.Constants;

public class Coralhandler extends SubsystemBase {
  private FridoSparkMax intakeMotor;
  private FridoSparkMax angleMotor;
  /** Creates a new ExampleSubsystem. */
  public Coralhandler() {
    angleMotor = new FridoSparkMax(Constants.Coralhandler.angleMotorId);
    intakeMotor = new FridoSparkMax(Constants.Coralhandler.intakeMotorId);
    //intakeMotor.enableForwardLimitSwitch(LimitSwitchPolarity.kNormallyOpen, true);

    angleMotor.setPID(Constants.Coralhandler.pid);

    resetAngleEncoder();
  }
  public void intakeMotor() {
    intakeMotor.set(Constants.Coralhandler.speedIntake);
  }
  
  public void outputMotor() {
    intakeMotor.set(Constants.Coralhandler.speedOutput);
  }
  
  public void stopMotor() {
    intakeMotor.stopMotor();
  }

public void resetAngleEncoder() {
  angleMotor.setEncoderPosition(getAbsoluteRotation()*Constants.Coralhandler.keyGearRatio);
}

public double getAbsoluteRotation() {
  double encoder = angleMotor.asSparkMax().getAbsoluteEncoder().getPosition();
  if (encoder > 0.5) {
    encoder -= 1;
  }
  return encoder;
}


public void positionMotor(double absoluteTargetPosition) {
  if (absoluteTargetPosition > Constants.Coralhandler.angleMaxA) {
    absoluteTargetPosition = Constants.Coralhandler.angleMaxA;
  }
  if (absoluteTargetPosition < Constants.Coralhandler.angleMaxB) {
    absoluteTargetPosition = Constants.Coralhandler.angleMaxB;
  }
  angleMotor.setPosition(absoluteTargetPosition);
}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  
}
