package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;

public class ShootCommand extends Command {

    public ShootCommand() {
        addRequirements(RobotContainer.shooter);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        RobotContainer.shooter.runShooter();
    }

    @Override
    public void end(boolean interrupted) {
        RobotContainer.shooter.stopMotors();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}