package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class ExamplePrallel extends ParallelCommandGroup {
    public ExamplePrallel() {
        addCommands(
            new ExampleCommand(),
            new PrintVorkursCommand(3),
            new PrintVorkursCommand(4)
        );
    }
}
