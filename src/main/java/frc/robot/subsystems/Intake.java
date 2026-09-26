package frc.robot.subsystems;

import org.littletonrobotics.junction.Logger;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
    private SparkMax motor;

    public Intake() {
        /*
         * TODO:
         * - Die Motor-Instance erstellen.
         */

        // Configurations
        SparkMaxConfig config = new SparkMaxConfig();

        config.apply(SparkMaxConfig.Presets.REV_NEO);

        motor.configure(config, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void intake() {
        /*
         * TODO:
         * - Motor drehen
         */
    }

    public void spittOut() {
        /*
         * TODO:
         * - Motor drehen
         */
    }

    public void stop() {
        /*
         * TODO:
         * - Der Motor stoppen
         */
    }

    @Override
    public void periodic() {
        Logger.recordOutput("IntakeSpeed", motor.getEncoder().getVelocity());
        // TODO: Log the used Voltage
    }
}
