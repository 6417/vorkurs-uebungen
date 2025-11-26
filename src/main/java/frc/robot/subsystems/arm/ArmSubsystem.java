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

    private FridoFalcon500v6 armfollower;
    private FridoFalcon500v6 armmaster;
    private PidValues pidValues;

    public ArmSubsystem() {
        // Erstelle motoren
        armfollower = new FridoFalcon500v6(Constants.Arm.ARMLEFT);
        armmaster = new FridoFalcon500v6(Constants.Arm.ARMRIGHT);

        //  Konfiguriere folgen
        armfollower.follow(armmaster, DirectionType.invertMaster);

        // Konfiguriere PID
        pidValues = new PidValues(Constants.Arm.UPPERARM_KP,Constants.Arm.UPPERARM_KI,Constants.Arm.UPPERARM_KD);
        armmaster.setPID(pidValues);
    }

    public void setPosition(double position) {
        armmaster.setPosition(position);
    }
    

    @Override
    public void periodic() {
        setPosition(3);
        System.out.println(armmaster.getEncoderTicks());
    }
    
}