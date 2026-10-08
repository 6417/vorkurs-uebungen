package frc.robot.subsystems;

import org.littletonrobotics.junction.Logger;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase{
    private SparkMax shooterMotor;
    private SparkMax angleMotor;
    private SparkMax feederMotor;

    public Shooter() {
        shooterMotor = new SparkMax(Constants.Shooter.ShooterMotor.id, MotorType.kBrushless);

        SparkMaxConfig shooterConfig = new SparkMaxConfig();
        shooterConfig.apply(SparkMaxConfig.Presets.REV_NEO);
        shooterMotor.configure(shooterConfig, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
    
        angleMotor = new SparkMax(Constants.Shooter.AngleMotor.id, MotorType.kBrushless);

        SparkMaxConfig angleConfig = new SparkMaxConfig();
        angleConfig.apply(SparkMaxConfig.Presets.REV_NEO);
        angleMotor.configure(angleConfig, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
    
        feederMotor = new SparkMax(Constants.Shooter.ShooterMotor.id, MotorType.kBrushless);

        SparkMaxConfig feederConfig = new SparkMaxConfig();
        feederConfig.apply(SparkMaxConfig.Presets.REV_NEO);
        feederMotor.configure(feederConfig, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void setShootAngle(double pos) {
        // TODO: Calculate
    }

    public void setAngleVelocity(double velo) {
        angleMotor.set(velo);
    }

    public void stopAngle() {
        angleMotor.stopMotor();
    }

    public void setShootSpeed(double speed) {
        shooterMotor.set(speed);
        feederMotor.set(Constants.Shooter.FeederMotor.speed);
    }

    public void stopShooter() {
        shooterMotor.stopMotor();
        feederMotor.stopMotor();
    }

    @Override
    public void periodic() {
        /*
         * TODO:
         * Log the Angle Position
         */
        Logger.recordOutput("Shooter Speed", shooterMotor.getEncoder().getVelocity());
        Logger.recordOutput("Shooting angle", shooterMotor.getEncoder().getPosition());
    }
}
