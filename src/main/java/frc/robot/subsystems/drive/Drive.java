// Refactoring ideas:
//  organizing methods && fields
//  check 
//  sync DriveBase

package frc.robot.subsystems.drive;

import frc.robot.Constants;

import frc.fridowpi.motors.FridoFalcon500v6;
import frc.fridowpi.motors.FridolinsMotor.DirectionType;
import frc.fridowpi.motors.FridolinsMotor.IdleMode;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase {
    private DifferentialDrive tankDrive;

    private FridoFalcon500v6 masterRight;
    private FridoFalcon500v6 masterLeft;
    private FridoFalcon500v6 followerRight;
    private FridoFalcon500v6 followerLeft;

    public Drive() {
        masterRight = new FridoFalcon500v6(Constants.Drive.Motors.FRONTRIGHT);
        masterLeft = new FridoFalcon500v6(Constants.Drive.Motors.FRONTLEFT);
        followerRight = new FridoFalcon500v6(Constants.Drive.Motors.BACKRIGHT);
        followerLeft = new FridoFalcon500v6(Constants.Drive.Motors.BACKLEFT);

        masterRight.setIdleMode(IdleMode.kCoast);
        masterLeft.setIdleMode(IdleMode.kCoast);
        followerRight.setIdleMode(IdleMode.kCoast);
        followerLeft.setIdleMode(IdleMode.kCoast);
       
        followerRight.follow(masterRight, DirectionType.followMaster);
        followerLeft.follow(masterLeft, DirectionType.followMaster);

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
