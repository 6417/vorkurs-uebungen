package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;

public class ExampleSequential extends SequentialCommandGroup{
    public ExampleSequential(int state) {
        addCommands(
            new PrintVorkursCommand(Constants.ExampleSubsystem.print0[state]),
            new WaitCommand(5),
            new PrintVorkursCommand(Constants.ExampleSubsystem.print1[state]),
            new ExamplePrallel(state)
        );
    }
}
