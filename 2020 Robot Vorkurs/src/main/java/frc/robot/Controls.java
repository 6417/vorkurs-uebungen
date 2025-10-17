package frc.robot;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * Holds the data concerning input, which should be available
 * either to the entire program or get exported to the shuffleboard
 */
public class Controls implements Sendable {

    // Initialize Joysticks
    public Joystick driveJoystick = new Joystick(Constants.Controller.inUseJoystick_ID);
    
    // Initialize Joystick buttons

    public Controls() {
    }

    // Shuffleboard
    public void initSendable(SendableBuilder builder) {
    }

}