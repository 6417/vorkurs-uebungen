// Refactoring ideas:
//  organizing methods && fields
//  check 
//  sync DriveBase

package frc.robot.subsystems.arm;

import frc.robot.Constants;

import frc.fridowpi.motors.FridoFalcon500v6;
import frc.fridowpi.motors.FridolinsMotor.DirectionType;
import frc.fridowpi.motors.FridolinsMotor.IdleMode;
import frc.fridowpi.motors.utils.PidValues;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {

    private FridoFalcon500v6 upperArmFollower;
    private FridoFalcon500v6 upperArmMaster;
    private PidValues pidValues;
    private FridoFalcon500v6 baseArmFollower;
    private FridoFalcon500v6 baseArmMaster;

    public ArmSubsystem() {
        // Erstelle motoren
        upperArmFollower = new FridoFalcon500v6(Constants.Arm.ARMLEFT);
        upperArmMaster = new FridoFalcon500v6(Constants.Arm.ARMRIGHT);
        baseArmFollower = new FridoFalcon500v6(20);
        baseArmMaster = new FridoFalcon500v6(21);

        //  Konfiguriere folgen
        upperArmFollower.follow(upperArmMaster, DirectionType.invertMaster);
        baseArmFollower.follow(baseArmMaster, DirectionType.followMaster);
        // Konfiguriere PID
        pidValues = new PidValues(Constants.Arm.UPPERARM_KP,Constants.Arm.UPPERARM_KI,Constants.Arm.UPPERARM_KD);
        upperArmMaster.setPID(pidValues);
    }

    public void setPosition(double position) {
        upperArmMaster.setPosition(position);
    }
    

    @Override
    public void periodic() {
        baseArmMaster.set(0.6);
    }
    
}