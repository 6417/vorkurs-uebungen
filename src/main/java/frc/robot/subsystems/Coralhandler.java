// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.fridowpi.motors.FridoSparkMax;
import frc.fridowpi.motors.FridolinsMotor.LimitSwitchPolarity;
import frc.robot.Constants;

public class Coralhandler extends SubsystemBase {
  private FridoSparkMax intakeMotor;
  private FridoSparkMax angleMotor;
  private double desirdeAngle = 0;
  /** Creates a new ExampleSubsystem. */
  public Coralhandler() {
    angleMotor = new FridoSparkMax(Constants.Coralhandler.angleMotorId);
    intakeMotor = new FridoSparkMax(Constants.Coralhandler.intakeMotorId);
    intakeMotor.enableForwardLimitSwitch(LimitSwitchPolarity.kNormallyOpen, true);

    angleMotor.setPID(Constants.Coralhandler.pid);
    //intakeMotor.setPID(Constants.Coralhandler.pidIntake);

    resetAngleEncoder();

    Shuffleboard.getTab("coral").add("handler", this);
  }
  public void intakeMotor() {
    intakeMotor.set(Constants.Coralhandler.speedIntake);
    //intakeMotor.setVelocity(Constants.Coralhandler.speedIntake);
  }
  
  public void outputMotor() {
    intakeMotor.set(Constants.Coralhandler.speedOutput);
    //intakeMotor.setVelocity(Constants.Coralhandler.speedOutput);
  }

  public void stopMotor() {
    intakeMotor.stopMotor();
  }

  public void resetAngleEncoder() {
    angleMotor.setEncoderPosition(getAbsoluteRotation() * Constants.Coralhandler.keyGearRatio);
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
    desirdeAngle = absoluteTargetPosition;
    angleMotor.setPosition(absoluteTargetPosition);
  }

  public boolean isAtDesiredAngle() {
    return Math.abs(desirdeAngle - angleMotor.getEncoderTicks()) <= 0.5;
  }

  public boolean isLimitSwitchPressed() {
    if (intakeMotor.isForwardLimitSwitchActive()) {
      return true;
    }
    else {
      return false;
    }
  }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }


  @Override
  public void initSendable(SendableBuilder builder){
    builder.addDoubleProperty("Encoder Position", () -> getAbsoluteRotation()*Constants.Coralhandler.keyGearRatio, null);
    builder.addDoubleProperty("Coralhandler Velocity", () -> intakeMotor.get(), null);
    builder.addDoubleProperty("soll velocity", () -> Constants.Coralhandler.speedIntake, null);
    // super.initSendable(builder);
  }
}
