package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CountSubsystem;

public class PrintVorkursCommand extends Command {
  CountSubsystem mySubsystem;

  public PrintVorkursCommand(int index) {
    // Use addRequirements() here to declare subsystem dependencies.
    mySubsystem = new CountSubsystem(index);
    addRequirements(mySubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    mySubsystem.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    mySubsystem.run();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    mySubsystem.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // return false;
    return mySubsystem.condition();
  }
}
