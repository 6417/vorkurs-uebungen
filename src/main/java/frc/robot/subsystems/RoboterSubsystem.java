package frc.robot.subsystems;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.fridowpi.motors.FridoFalcon500v6;
import frc.fridowpi.motors.FridolinsMotor.LimitSwitchPolarity;

public class RoboterSubsystem extends SubsystemBase {
    FridoFalcon500v6 motor;

    public RoboterSubsystem() {
        motor = new FridoFalcon500v6(50);

        motor.enableForwardLimitSwitch(LimitSwitchPolarity.kNormallyClosed, true);

        Shuffleboard.getTab("Roboter").add("Roboter", this);
    }

    public boolean getLimitSwitch() {
        return motor.isForwardLimitSwitchActive();
    }

    public void setLimitSwitch(boolean b) {
        motor.enableForwardLimitSwitch(LimitSwitchPolarity.kNormallyOpen, b);
    }

    public void set() {
        motor.set(0.1);
    }

    public void stop() {
        motor.stopMotor();
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.addBooleanProperty("Encoder Value", this::getLimitSwitch, this::setLimitSwitch);
    }
}
