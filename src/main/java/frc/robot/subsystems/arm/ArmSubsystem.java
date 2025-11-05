// Refactoring ideas:
//  organizing methods && fields
//  check 
//  sync DriveBase

package frc.robot.subsystems.arm;

import frc.robot.Constants;

import frc.fridowpi.motors.FridoFalcon500v6;
import frc.fridowpi.motors.FridolinsMotor.DirectionType;
import frc.fridowpi.motors.FridolinsMotor.IdleMode;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {

    private FridoFalcon500v6 armleft;
    private FridoFalcon500v6 armright;

    public ArmSubsystem() {
        armleft = new FridoFalcon500v6(Constants.Arm.ARMLEFT);
        armright = new FridoFalcon500v6(Constants.Arm.ARMRIGHT);

        armleft.follow(armright, DirectionType.invertMaster);
    }

    
}