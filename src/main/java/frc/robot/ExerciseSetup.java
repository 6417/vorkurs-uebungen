package frc.robot;

import java.util.List;
import java.util.function.Function;

import edu.wpi.first.wpilibj.Joystick;
import frc.fridowpi.joystick.IJoystick;
import frc.fridowpi.joystick.IJoystickId;
import frc.fridowpi.joystick.JoystickHandler;

/**
 * This class hides all the details we don't yet worry about.
 */
class ExerciseSetup {
    IJoystickId joystickId;
    IJoystick joystick;

    public ExerciseSetup(int joystickId, Function<IJoystickId, IJoystick> joystickFactory) {
        this.joystickId = () -> joystickId;

        // Tells the handler how to construct the joystick, this can change depending 
        // on the joystick. It will almost always be either a WPIJoystick or a XBoxJoystick.
        JoystickHandler.getInstance().setJoystickFactory(joystickFactory);
    }

    public void init() {
        // Our joystick gets setup and we save a reference to it in `joystick`.
        JoystickHandler.getInstance().setupJoysticks(List.of(joystickId));
        joystick = JoystickHandler.getInstance().getJoystick(joystickId);
    }

    public double get_joystick_output() {
        return joystick.getY() * 0.6;
    }
}
