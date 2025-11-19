package frc.robot;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.PrintVorkursCommand;

public class Controls implements Sendable {
    public CommandXboxController joystick;

    private Trigger ybutton = joystick.y();
    private Trigger xbutton = joystick.x();
    private Trigger abutton = joystick.a();
    private Trigger bbutton = joystick.b();
  
    private final CommandXboxController m_driverController =
      new CommandXboxController(Constants.OperatorConstants.kDriverControllerPort);

    public Controls() {
        joystick = new CommandXboxController(Constants.Controls.xboxID);

        ybutton.onTrue(new PrintVorkursCommand(1));
        xbutton.whileTrue(new PrintVorkursCommand(2));
    }

    public void initSendable(SendableBuilder builder) {    }
}
