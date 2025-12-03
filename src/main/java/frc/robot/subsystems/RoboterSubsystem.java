package frc.robot.subsystems;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.fridowpi.motors.FridoSparkMax;
import frc.fridowpi.motors.FridolinsMotor.LimitSwitchPolarity;

public class RoboterSubsystem extends SubsystemBase {
    FridoSparkMax motor;

    public RoboterSubsystem() {
        motor = new FridoSparkMax(50);

        motor.enableForwardLimitSwitch(LimitSwitchPolarity.kNormallyClosed, false);

        Shuffleboard.getTab("Roboter").add("Roboter", this);
    }

    public boolean getLimitSwitch() {
        return motor.isForwardLimitSwitchActive();
    }

    public void set() {
        motor.set(0.1);
    }

    public void stop() {
        motor.stopMotor();
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.addBooleanProperty("Encoder Value", this::getLimitSwitch, null);
    }
}
