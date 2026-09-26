package frc.robot.subsystems;

import org.littletonrobotics.junction.Logger;

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
    private SparkMax motor;

    public Intake() {
        motor = new SparkMax(Constants.Intake.ID, Constants.Intake.MOTOR_TYPE);
        motor.configure(Constants.Intake.CONFIG, Constants.Intake.RESET_MODE, Constants.Intake.PERSIST_MODE);
    }

    public void intake() {
        motor.set(Constants.Intake.INTAKE_SPEED);
    }

    public void spittOut() {
        motor.set(Constants.Intake.OUTSPITT_SPEED);
    }

    public void stop() {
        motor.set(0);
    }

    @Override
    public void periodic() {
        Logger.recordOutput("IntakeSpeed", motor.getEncoder().getVelocity());
        // TODO: Log the used Voltage
    }
}
