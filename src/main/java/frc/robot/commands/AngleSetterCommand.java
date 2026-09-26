package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class AngleSetterCommand extends Command {
    private int directory;

    // direction \in {-1,1}
    public AngleSetterCommand(int direction) {
        directory = direction;
        addRequirements(RobotContainer.shooter);
    }

    @Override
    public void initialize() {  }

    @Override
    public void execute() {
        RobotContainer.shooter.setAngleVelocity(Constants.Shooter.ANGLE_SPEED * directory);
    }

    @Override
    public void end(boolean interrupted) {
        RobotContainer.shooter.stopAngle();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
