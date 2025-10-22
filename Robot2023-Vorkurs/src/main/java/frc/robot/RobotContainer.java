// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.subsystems.drive.Drive;

public class RobotContainer {
  public Joystick joystick;
  /*
   * TODO: Add Drivesubsystem
   */

  public RobotContainer() {
    /*
    * TODO: Add Drivesubsystem
    */
    joystick = new Joystick(0);
  }
}
