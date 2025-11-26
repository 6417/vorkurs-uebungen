// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.RobotContainer;
import frc.robot.Constants.Coralhandler;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class IntakeCommand extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  /**
   * Creates a new ExampleCommand.
   *
   */
  //---------konstruktor-------------
  public IntakeCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.hans);
  }
  

  // Called when the command is initially scheduled.
  // ------start des Befehls (startbedingungen)-------
  @Override
  public void initialize() {
    RobotContainer.hans.intakeMotor();
    System.out.println("intake initialized");
  }

  // Called every time the scheduler runs while the command is scheduled.
  // -------wird immer wieder aufgerufen---------
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  // ----------beendet den command------------
  @Override
  public void end(boolean interrupted) {
    //TODO: Hier Motor abschalten
  }

  // Returns true when the command should end
  // --------------schaut, ob command beendet ist---------------
  @Override
  public boolean isFinished() {
    return RobotContainer.hans.isLimitSwitchPressed();
  }
}
