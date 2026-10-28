package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class FeederCommand extends Command{
    public FeederCommand(){

    }    

    @Override
    public void initialize() {  }

    @Override
    public void execute() {
        RobotContainer.shooter.setFeederSpeed(Constants.Shooter.FeederMotor.speed);
    }

    @Override
    public void end(boolean interrupted) {
        RobotContainer.shooter.stopFeeder();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}


