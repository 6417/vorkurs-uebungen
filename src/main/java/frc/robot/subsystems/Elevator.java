// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.config.SoftLimitConfig;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.fridowpi.motors.FridoSparkMax;
import frc.fridowpi.motors.FridolinsMotor.DirectionType;
import frc.fridowpi.motors.FridolinsMotor.IdleMode;
import frc.fridowpi.motors.FridolinsMotor.LimitSwitchPolarity;
import frc.robot.Constants;

public class Elevator extends SubsystemBase {
    private FridoSparkMax elevateMasterMotor;
    private FridoSparkMax elevateFollowerMotor;
  /** Creates a new ElevatorSubsystem. */
    public Elevator() {
        // 2 Motoren erstellen (Master und Follower)
        elevateMasterMotor = new FridoSparkMax(Constants.Elevator.elevateMasterMotorId);
        elevateFollowerMotor = new FridoSparkMax(Constants.Elevator.elevateFollowerMotorId);

        // Limit Switch aktivieren
        elevateMasterMotor.enableForwardLimitSwitch(LimitSwitchPolarity.kNormallyClosed, false);
        // Follower folgt dem Master, jedoch umgekehrt (z.B. Master:9, Follower:-9 --> wegen Mechanik)  
        elevateFollowerMotor.follow(elevateMasterMotor, DirectionType.invertMaster);
        // PID's aktivieren
        elevateMasterMotor.setPID(null);

        // Encoder wieder auf Null stellen
        elevateMasterMotor.setEncoderPosition(0);

        // Master Motor wird invertiert
        elevateMasterMotor.setInverted(true);

        elevateMasterMotor.setIdleMode(IdleMode.kBrake);
        
    } 

    // Diese Funktion fährt eine bestimmte Höhe an (höhe in Constants definiert)
    public void heightPosition(double targetHeight) {
        if (targetHeight > Constants.Elevator.heightMaxUp) {
          targetHeight = Constants.Elevator.heightMaxUp;
        }
        if (targetHeight < Constants.Elevator.heightMaxDown) {
          targetHeight = Constants.Elevator.heightMaxDown;
        }
        elevateMasterMotor.setPosition(targetHeight);
      }
}