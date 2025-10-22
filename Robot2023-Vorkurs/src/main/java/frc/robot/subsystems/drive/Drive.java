// Refactoring ideas:
//  organizing methods && fields
//  check 
//  sync DriveBase

package frc.robot.subsystems.drive;

import frc.robot.Constants;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.controls.Follower;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase {
    private DifferentialDrive tankDrive;

    private TalonFX masterRight;
    private TalonFX masterLeft;
    private TalonFX followerRight;
    private TalonFX followerLeft;

    public Drive() {
        masterRight = new TalonFX(Constants.Drive.Motors.FRONTRIGHT);
        masterLeft = new TalonFX(Constants.Drive.Motors.FRONTLEFT);
        followerRight = new TalonFX(Constants.Drive.Motors.BACKRIGHT);
        followerLeft = new TalonFX(Constants.Drive.Motors.BACKLEFT);

        masterRight.setNeutralMode(NeutralModeValue.Brake);
        masterLeft.setNeutralMode(NeutralModeValue.Brake);
        followerRight.setNeutralMode(NeutralModeValue.Brake);
        followerLeft.setNeutralMode(NeutralModeValue.Brake);
        
        followerRight.setControl(new Follower(Constants.Drive.Motors.FRONTRIGHT, false));
        followerLeft.setControl(new Follower(Constants.Drive.Motors.FRONTLEFT, false));

        tankDrive = new DifferentialDrive(masterLeft::set, masterRight::set);
        tankDrive.setDeadband(0.0);

        System.out.println("Drive init completed");
    }

    public void drive(double x, double y) {
        tankDrive.arcadeDrive(x, y);
    }
    
    public void stopMotors() {
        tankDrive.stopMotor();
    }

}
