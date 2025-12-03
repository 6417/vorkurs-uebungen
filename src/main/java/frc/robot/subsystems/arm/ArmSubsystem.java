// Refactoring ideas:
//  organizing methods && fields
//  check 
//  sync DriveBase

package frc.robot.subsystems.arm;

import frc.robot.Constants;

import frc.fridowpi.motors.FridoFalcon500v6;
import frc.fridowpi.motors.FridolinsMotor.DirectionType;
import frc.fridowpi.motors.FridolinsMotor.IdleMode;
import frc.fridowpi.motors.FridolinsMotor.LimitSwitchPolarity;
import frc.fridowpi.motors.utils.PidValues;

import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;

import edu.wpi.first.util.sendable.SendableBuilder;
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
        upperArmFollower = new FridoFalcon500v6(Constants.Arm.UPPER_FOLLOWER);
        upperArmMaster = new FridoFalcon500v6(Constants.Arm.UPPER_MASTER);
        baseArmFollower = new FridoFalcon500v6(Constants.Arm.BASE_ARM_FOLLOWER);
        baseArmMaster = new FridoFalcon500v6(Constants.Arm.BASE_ARM_MASTER);

        baseArmMaster.setInverted(true);

        //  Konfiguriere folgen
        upperArmFollower.follow(upperArmMaster, DirectionType.invertMaster);
        baseArmFollower.follow(baseArmMaster, DirectionType.followMaster);

        // Konfiguriere PID
        pidValues = new PidValues(Constants.Arm.UPPERARM_KP,Constants.Arm.UPPERARM_KI,Constants.Arm.UPPERARM_KD);
        upperArmMaster.setPID(pidValues);
        
        // Limitswitches
        baseArmMaster.enableForwardLimitSwitch(LimitSwitchPolarity.kNormallyOpen,true);
        baseArmMaster.enableReverseLimitSwitch(LimitSwitchPolarity.kNormallyOpen,true);
        baseArmFollower.enableForwardLimitSwitch(LimitSwitchPolarity.kNormallyOpen,true);
        baseArmFollower.enableReverseLimitSwitch(LimitSwitchPolarity.kNormallyOpen,true);
    }

    public void setPosition(double position) {
        upperArmMaster.setPosition(position);
    }
    
    public void setSpeedBase(double speed) {
        baseArmMaster.set(speed);
    }

    @Override
    public void periodic() {
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
        builder.addBooleanProperty("MasterReverseLimit", ()->baseArmMaster.isReverseLimitSwitchActive(), null);
        builder.addBooleanProperty("FollowerReverseLimit", ()->baseArmFollower.isReverseLimitSwitchActive(), null);
        builder.addBooleanProperty("MasterForwardLimit", ()->baseArmMaster.isForwardLimitSwitchActive(), null);
        builder.addBooleanProperty("FollowerForwardLimit", ()->baseArmFollower.isForwardLimitSwitchActive(), null);
    }
    
}