package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class ExampleSequential extends SequentialCommandGroup{
    public ExampleSequential() {
        addCommands(
            new PrintVorkursCommand(1),
            new WaitCommand(5),
            new PrintVorkursCommand(2),
            new ExamplePrallel()
        );
    }
}
