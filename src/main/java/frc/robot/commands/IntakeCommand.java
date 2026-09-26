package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;

public class IntakeCommand extends Command{
    public IntakeCommand() {
        addRequirements(RobotContainer.intake);
    }

    @Override
    public void initialize() {  }

    @Override
    public void execute() {
        RobotContainer.intake.intake();
    }

    @Override
    public void end(boolean interrupted) {
        RobotContainer.intake.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
