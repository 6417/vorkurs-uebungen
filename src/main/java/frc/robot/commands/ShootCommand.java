package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class ShootCommand extends Command{
    public ShootCommand() {
        addRequirements(RobotContainer.shooter);
    }

    @Override
    public void initialize() {  }

    @Override
    public void execute() {
        RobotContainer.shooter.setShootSpeed(Constants.Shooter.SHOOT_SPEED);
    }

    @Override
    public void end(boolean interrupted) {
        RobotContainer.shooter.stopShooter();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
