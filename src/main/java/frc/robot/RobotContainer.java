// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.subsystems.arm.ArmSubsystem;
import frc.robot.subsystems.drive.Drive;

public class RobotContainer {
  public Joystick joystick;
  public Drive driveSubsystem;
  public ArmSubsystem armSubsystem; 

  public RobotContainer() {
    driveSubsystem = new Drive();
    joystick = new Joystick(Constants.Joystick.ID);

    armSubsystem = new ArmSubsystem();
    Shuffleboard.getTab("Arm").add(armSubsystem);
  }
}
