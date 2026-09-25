package frc.robot;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.shooter.ShootCommand;

public class Controls {

    public CommandXboxController driveController;

    public Controls() {
        // Initialize all joysticks
        driveController = new CommandXboxController(Constants.Joystick.kDriverControllerPort);

        // Bind buttons to joysticks
        driveController.leftTrigger().whileTrue(new ShootCommand());
    }
}