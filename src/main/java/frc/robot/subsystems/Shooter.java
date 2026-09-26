package frc.robot.subsystems;

import org.littletonrobotics.junction.Logger;

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase{
    private SparkMax shooter;
    private SparkMax angle;
    private SparkMax feeder;

    public Shooter() {
        shooter = new SparkMax(Constants.Shooter.ShooterMotor.ID, Constants.Shooter.ShooterMotor.MOTOR_TYPE);
        shooter.configure(Constants.Shooter.ShooterMotor.CONFIG, Constants.Shooter.ShooterMotor.RESET_MODE, Constants.Shooter.ShooterMotor.PERSIST_MODE);
    
        angle = new SparkMax(Constants.Shooter.AngleMotor.ID, Constants.Shooter.AngleMotor.MOTOR_TYPE);
        angle.configure(Constants.Shooter.AngleMotor.CONFIG, Constants.Shooter.AngleMotor.RESET_MODE, Constants.Shooter.AngleMotor.PERSIST_MODE);
        
        feeder = new SparkMax(Constants.Shooter.FeederMotor.ID, Constants.Shooter.FeederMotor.MOTOR_TYPE);
        feeder.configure(Constants.Shooter.FeederMotor.CONFIG, Constants.Shooter.FeederMotor.RESET_MODE, Constants.Shooter.FeederMotor.PERSIST_MODE);
    }

    public void setShootAngle(double pos) {
        // TODO: Calculate
    }

    public void setAngleVelocity(double velo) {
        angle.set(velo);
    }

    public void stopAngle() {
        angle.stopMotor();
    }

    public void setShootSpeed(double speed) {
        shooter.set(speed);
        feeder.set(Constants.Shooter.FEEDER_SPEED);
    }

    public void stopShooter() {
        shooter.stopMotor();
        feeder.stopMotor();
    }

    @Override
    public void periodic() {
        Logger.recordOutput("Shooter Speed", shooter.getEncoder().getVelocity());
        Logger.recordOutput("Shooting angle", shooter.getEncoder().getPosition());
    }
}
