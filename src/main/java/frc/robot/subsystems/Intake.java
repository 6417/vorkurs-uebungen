package frc.robot.subsystems;

import org.littletonrobotics.junction.Logger;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
    private SparkMax motor;

    public Intake() {
        motor = new SparkMax(Constants.Intake.id, MotorType.kBrushless);

        SparkMaxConfig config = new SparkMaxConfig();

        config.apply(SparkMaxConfig.Presets.REV_NEO);

        motor.configure(config, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void intake() {
        motor.set(Constants.Intake.intakeSpeed);
    }

    public void spittOut() {
        motor.set(Constants.Intake.outspittSpeed);
    }

    public void stop() {
        motor.stopMotor();;
    }

    @Override
    public void periodic() {
        Logger.recordOutput("IntakeSpeed", motor.getEncoder().getVelocity());
        // TODO: Log the used Voltage
    }
}
