package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;

public class OutspittCommand extends Command {
    public OutspittCommand() {
        addRequirements(RobotContainer.intake);
    }

    @Override
    public void initialize() {  }

    @Override
    public void execute() {
        RobotContainer.intake.spittOut();
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
