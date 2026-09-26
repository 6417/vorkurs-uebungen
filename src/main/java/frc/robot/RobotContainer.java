package frc.robot;

import com.ctre.phoenix6.hardware.Pigeon2;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.SwerveSubsystem;

public class RobotContainer {
    public static final Pigeon2 gyro = new Pigeon2(Constants.Gyro.PIGEON_ID);
    public static final SwerveSubsystem drive = new SwerveSubsystem();
    public static final ShooterSubsystem shooter = new ShooterSubsystem();

    public static final Controls controls = new Controls();

    public Command getAutonomousCommand() {
      return new InstantCommand();
    }
}
