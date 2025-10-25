// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.fridowpi.motors.FridoSparkMax;
import frc.robot.Constants;

public class Coralhandler extends SubsystemBase {
  private FridoSparkMax intakeMotor;
  /** Creates a new ExampleSubsystem. */
  public Coralhandler() {
    intakeMotor = new FridoSparkMax(Constants.Coralhandler.intakeMotorId);
  }
  public void startMotor() {
    intakeMotor.set(Constants.Coralhandler.speedIntake);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  
}
