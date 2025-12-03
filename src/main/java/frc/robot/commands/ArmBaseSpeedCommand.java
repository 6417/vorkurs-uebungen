package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;

public class ArmBaseSpeedCommand extends Command {
    public double speed;

    public ArmBaseSpeedCommand(double speed) {
        this.speed = speed;
        addRequirements(RobotContainer.armSubsystem);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        RobotContainer.armSubsystem.setSpeedBase(speed);
    }

    @Override
    public boolean isFinished() {
       return false;
    }

    @Override
    public void end(boolean interrupted) {
        RobotContainer.armSubsystem.setSpeedBase(0);
    }
}
