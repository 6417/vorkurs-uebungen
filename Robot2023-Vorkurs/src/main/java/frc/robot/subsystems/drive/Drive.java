// Refactoring ideas:
//  organizing methods && fields
//  check 
//  sync DriveBase

package frc.robot.subsystems.drive;

import frc.robot.Constants;

import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.controls.Follower;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase {
    private DifferentialDriveOdometry odometry;
    private DifferentialDriveKinematics kinematics;
    private DifferentialDrive tankDrive;

    private TalonFX masterRight;
    private TalonFX masterLeft;
    private TalonFX followerRight;
    private TalonFX followerLeft;

    private PIDController rightVelocityController;
    private PIDController leftVelocityController;

    public Drive() {
        masterRight = new TalonFX(Constants.Drive.Motors.FRONTRIGHT);
        masterLeft = new TalonFX(Constants.Drive.Motors.FRONTLEFT);
        followerRight = new TalonFX(Constants.Drive.Motors.BACKRIGHT);
        followerLeft = new TalonFX(Constants.Drive.Motors.BACKLEFT);

        odometry = new DifferentialDriveOdometry(
                new Rotation2d(0),
                0.0, 0.0,
                new Pose2d(new Translation2d(0, 0), new Rotation2d(0)));

        kinematics = new DifferentialDriveKinematics(
                Constants.Drive.Odometry.trackWidthMeters);



        masterRight.setNeutralMode(NeutralModeValue.Brake);
        masterLeft.setNeutralMode(NeutralModeValue.Brake);
        followerRight.setNeutralMode(NeutralModeValue.Brake);
        followerLeft.setNeutralMode(NeutralModeValue.Brake);
        
        followerRight.setControl(new Follower(Constants.Drive.Motors.FRONTRIGHT, false));
        followerLeft.setControl(new Follower(Constants.Drive.Motors.FRONTLEFT, false));

        tankDrive = new DifferentialDrive(masterLeft::set, masterRight::set);
        tankDrive.setDeadband(0.0);

        resetSensors();

        System.out.println("Drive init completed");
    }

    /* Getter Methods */
    public Pose2d getPosition() {
        return odometry.getPoseMeters();
    }
    
    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
        return new DifferentialDriveWheelSpeeds(
            masterLeft.getRotorPosition().getValueAsDouble(),
            masterRight.getRotorPosition().getValueAsDouble()
        );
    }

    public void drive(double x, double y) {
        tankDrive.arcadeDrive(x, y);
    }
    
    public ChassisSpeeds getChassisSpeeds() {
        return kinematics.toChassisSpeeds(getWheelSpeeds());
    }
    
    public PIDController getRightVelocityController() {
        return leftVelocityController;
    }

    
    public PIDController getLeftVelocityController() {
        return rightVelocityController;
    }

    
    public DifferentialDriveKinematics getDriveKinematics() {
        return kinematics;
    }

    private void resetSensors() {
        odometry.resetPosition(
                new Rotation2d(0), 0, 0, new Pose2d(0, 0, new Rotation2d(0)));
        masterRight.getConfigurator().apply(
            new FeedbackConfigs().withFeedbackRotorOffset(0)
        );
        masterLeft.getConfigurator().apply(
            new FeedbackConfigs().withFeedbackRotorOffset(0)
        );
    }

    public void stopMotors() {
        tankDrive.stopMotor();
    }

}
