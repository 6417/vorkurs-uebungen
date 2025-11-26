package frc.robot;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.ExamplePrallel;
import frc.robot.commands.ExampleSequential;
import frc.robot.commands.PrintVorkursCommand;

public class Controls implements Sendable {
    public CommandXboxController joystick = new CommandXboxController(Constants.Controls.xboxID);

    private Trigger ybutton = joystick.y();
    private Trigger xbutton = joystick.x();
    private Trigger abutton = joystick.a();
    private Trigger bbutton = joystick.b();
    private Trigger windowsButton = joystick.back();
    private Trigger burgerButton = joystick.start();
  
    private final CommandXboxController m_driverController =
      new CommandXboxController(Constants.OperatorConstants.kDriverControllerPort);

    public Controls() {
      ybutton.onTrue(new PrintVorkursCommand(1));
      xbutton.whileTrue(new PrintVorkursCommand(2));

      windowsButton.onTrue(new PrintVorkursCommand(1).andThen(
                           new WaitCommand(2).andThen(
                           new ExampleCommand()
                          )));

      burgerButton.onTrue(new PrintVorkursCommand(3).alongWith(
                          new PrintVorkursCommand(1).andThen(
                            new WaitCommand(1).andThen(
                              new ExampleCommand().alongWith(new PrintVorkursCommand(1))
                            )
                           )
                         ));

      abutton.onTrue(new ExamplePrallel(0));
      bbutton.onTrue(new ExampleSequential(1));
    }

    public void initSendable(SendableBuilder builder) {    }
}
