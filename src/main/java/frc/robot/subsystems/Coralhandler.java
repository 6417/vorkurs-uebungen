// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

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
  
  
  public void angleMotor(double relativePosition) {
    double targetPosition = angleMotor.getEncoderTicks() + relativePosition;
    if (relativePosition > 0) {
      while (targetPosition > angleMotor.getEncoderTicks()) {
        angleMotor.set(Constants.Coralhandler.speedAngle);
      }
      angleMotor.stopMotor();
    }
    else{
      while (targetPosition < angleMotor.getEncoderTicks()) {
        angleMotor.set(-Constants.Coralhandler.speedAngle);
      }
      angleMotor.stopMotor();
    }
    
  }



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  
}
