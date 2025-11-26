// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ExampleSubsystem extends SubsystemBase {
  double data;
  /** Creates a new ExampleSubsystem. */
  public ExampleSubsystem() {
    data = 0;
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

  /**
   * Resets the motor and the sensor
   */
  public void reset() {
    data = 0;
    System.out.println("Example Subsystem got resetet!");
  }

  /**
   * Sets the motor speed
   * 
   * @param speed of the motors in [-1,1]
   */
  public void setMotorSpeed(double speed) {
    data += 1.2;
    System.out.printf("Example Subsystem set Motor speed at %d", speed);
  }

  /**
   * stops the motor
   */
  public void stopMotor() {
    System.out.println("Example Subsystem stopped the motor!");
  }

  /**
   * The sensor important for the motor
   * 
   * @return the data from a sensor
   */
  public double getSensorData() {
    System.out.println("Example Subsystem is checking de sensor.");
    return data;
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    System.out.println("Example Subsystem is evaluating a condition.");
    // Query some boolean state, such as a digital sensor.
    return data > 15.3;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
