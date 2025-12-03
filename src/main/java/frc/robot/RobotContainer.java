// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.ArmBaseSpeedCommand;
import frc.robot.subsystems.arm.ArmSubsystem;
import frc.robot.subsystems.drive.Drive;

public class RobotContainer {
  public static Joystick joystick = new Joystick(Constants.Joystick.ID);
  public static Drive driveSubsystem = new Drive();
  public static ArmSubsystem armSubsystem = new ArmSubsystem(); 

  private JoystickButton armBaseForwardButton = new JoystickButton(joystick, 3);
  private JoystickButton armBaseReverseButton = new JoystickButton(joystick, 4);

  public RobotContainer() {
    Shuffleboard.getTab("Arm").add(armSubsystem);

    configureButtonBinds();
  }

  private void configureButtonBinds() {
    armBaseForwardButton.whileTrue(new ArmBaseSpeedCommand(0.1));
    armBaseReverseButton.whileTrue(new ArmBaseSpeedCommand(-0.1));
  }
}
