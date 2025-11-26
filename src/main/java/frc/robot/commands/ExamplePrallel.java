package frc.robot.commands;

import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class ExamplePrallel extends ParallelCommandGroup {
    public ExamplePrallel(int state) {
        addCommands(
            new ExampleCommand(),
            new PrintVorkursCommand(Constants.ExampleSubsystem.print0[state]),
            new PrintVorkursCommand(Constants.ExampleSubsystem.print1[state])
        );
    }
}
