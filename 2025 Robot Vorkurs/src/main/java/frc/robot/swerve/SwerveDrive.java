package frc.robot.swerve;

import static edu.wpi.first.units.Units.Meter;
import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.Radians;
import static edu.wpi.first.units.Units.Volt;
import static edu.wpi.first.units.Units.Volts;

import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.units.measure.MutDistance;
import edu.wpi.first.units.measure.MutLinearVelocity;
import edu.wpi.first.units.measure.MutVoltage;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;
import frc.fridowpi.motors.FridolinsMotor.IdleMode;
import frc.fridowpi.utils.AccelerationLimiter;
import frc.robot.Constants;
import frc.robot.RobotContainer;

import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine.Config;

public class SwerveDrive extends SubsystemBase {
    public SwerveModule[] modules;
    private SwerveDriveKinematics kinematics;
    public SwerveDrivePoseEstimator poseEstimator;

    // I made a mistake by a factor of 10 when I considered the force, the
    // accelration should
    // be roughly 10 m / s^2 and not 75.
    private AccelerationLimiter accelLimiter = new AccelerationLimiter(20, 0.267);

    ChassisSpeeds lastSpeeds = new ChassisSpeeds();

    public static final int LOC_FL = 0;
    public static final int LOC_FR = 1;
    public static final int LOC_RL = 2;
    public static final int LOC_RR = 3;

    Thread odometryThread;

    public SwerveDrive(ModuleConfig[] configs) {
        String[] moduleNames = new String[4];
        moduleNames[LOC_FR] = "Front Right";
        moduleNames[LOC_FL] = "Front Left";
        moduleNames[LOC_RR] = "Rear Right";
        moduleNames[LOC_RL] = "Rear Left";

        modules = new SwerveModule[4];
        for (int i = 0; i < 4; i++) {
            configs[i].name = moduleNames[i];
            modules[i] = new SwerveModule(configs[i]);
            Shuffleboard.getTab("Drive").add("SwerveModule " + moduleNames[i], modules[i]);
        }
        Shuffleboard.getTab("Drive").add("SwerveDrive", this);

        kinematics = new SwerveDriveKinematics(
                configs[0].moduleOffset,
                configs[1].moduleOffset,
                configs[2].moduleOffset,
                configs[3].moduleOffset);

        setDefaultCommand(new DriveCommand(this));

    }

    public void setChassisSpeeds(ChassisSpeeds speeds) {
        // speeds = ChassisSpeeds.discretize(speeds, 0.02); // remove the skew

        /*
         * long timeNow = System.currentTimeMillis();
         * if (lastSetpointTime > 0) {
         * speeds = accelLimiter.constrain(lastMeasuredSpeeds, speeds,
         * ((double) (timeNow - lastSetpointTime)) / (double) 1000.0);
         * }
         */

        SwerveModuleState[] moduleStates = kinematics.toSwerveModuleStates(speeds);

        // SwerveDriveKinematics.desaturateWheelSpeeds(moduleStates,
        // Constants.SwerveDrive.maxSpeed);

        for (int i = 0; i < 4; i++) {
            modules[i].setDesiredState(moduleStates[i]);
        }

        // lastSpeeds = speeds;
        // lastSetpointTime = timeNow;
        // lastMeasuredSpeeds = getChassisSpeeds();
    }

    public void voltageDrive(double voltage) {
        for (int i = 0; i < 4; i++) {
            modules[i].setDesiredState(voltage);
        }
    }
    public void voltageDrive(Voltage voltage) {
        for (int i = 0; i < 4; i++) {
            
            modules[i].setDesiredState(voltage.baseUnitMagnitude());
        }
    }

    public double getcharecterizedVelocity() {
        double avareagevelocity = 0;
        for (int i = 0; i < 4; i++) {
            avareagevelocity += modules[i].getState().speedMetersPerSecond;
        }
        return avareagevelocity / 4;
    }

    public double getcharecterizedDistance() {
        double avareageDistance = 0;
        for (int i = 0; i < 4; i++) {
            avareageDistance += modules[i].getPosition().distanceMeters;
        }
        return avareageDistance / 4;
    }

    public double getcharecterizedVoltage() {
        double avareageVoltage = 0;
        for (int i = 0; i < 4; i++) {
            avareageVoltage += modules[i].appliedVoltage();
        }
        return avareageVoltage / 4;
    }

    public ChassisSpeeds getChassisSpeeds() {
        return kinematics.toChassisSpeeds(modules[0].getState(), modules[1].getState(),
                modules[2].getState(), modules[3].getState());
    }

    public void periodic() {
    }

    public Pose2d getPose() {
        return poseEstimator.getEstimatedPosition();
    }

    public void stopMotors() {
        for (var module : modules) {
            module.stopMotors();
        }
    }

    public void resetModulesToAbsolute() {
        for (SwerveModule module : modules) {
            module.resetToAbsolute();
        }
    }

    public void setIdleMode(IdleMode mode) {
        for (var module : modules) {
            module.setIdleMode(mode);
        }
    }

    public SwerveModulePosition[] getModulePositions() {
        return new SwerveModulePosition[] {
                modules[0].getPosition(),
                modules[1].getPosition(),
                modules[2].getPosition(),
                modules[3].getPosition(),
        };
    }

    @Override
    public void initSendable(SendableBuilder builder) {
    }
}