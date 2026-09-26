package frc.robot;

import java.util.Map;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.shooter.ShootCommand;

/**
 * Holds the data concerning input, which should be available
 * either to the entire program or get exported to the shuffleboard
 */
public class Controls implements Sendable {
        public CommandXboxController driveJoystick = new CommandXboxController(Constants.Joystick.driveJoystickId);
        public CommandXboxController operatorJoystick = new CommandXboxController(
                        Constants.Joystick.operatorJoystickId);

        Trigger xButtonDrive = driveJoystick.x();
        Trigger burgerButtonDrive = driveJoystick.start();
        Trigger intakeButton = driveJoystick.rightStick();
        Trigger shootButton = driveJoystick.leftTrigger();

        public enum DriveSpeed {
                DEFAULT_SPEED,
                FAST,
                SLOW
        }

        public enum DriveOrientation {
                FieldOriented, Forwards, Backwards
        }

        public Map<DriveSpeed, Double> speedFactors = Map.of(
                        DriveSpeed.DEFAULT_SPEED, 1.0,
                        DriveSpeed.FAST, 0.9,
                        DriveSpeed.SLOW, 0.3);
        private DriveSpeed activeSpeedFactor = DriveSpeed.DEFAULT_SPEED;
        private double accelerationSensitivity = speedFactors.get(activeSpeedFactor);

        public static double deadBandDrive = 0.08;
        public static double deadBandTurn = 0.08;
        public boolean inputsSquared = false;

        public boolean slewRateLimited = true;
        public double slewRateLimit = 1.0;

        public double turnSensitivity = 0.08;

        public DriveOrientation driveOrientation = DriveOrientation.Forwards;

        public void setActiveSpeedFactor(DriveSpeed speedFactor) {
                activeSpeedFactor = speedFactor;
                accelerationSensitivity = speedFactors.get(speedFactor);
        }

        public DriveSpeed getActiveSpeedFactor() {
                return activeSpeedFactor;
        }

        public double getAccelerationSensitivity() {
                return accelerationSensitivity;
        }

        public Controls() {

                // Configure the drive buttons
                intakeButton.whileTrue(new InstantCommand(() -> RobotContainer.drive.setIntakeMode(true)))
                                .onFalse(new InstantCommand(() -> RobotContainer.drive.setIntakeMode(false)));
                burgerButtonDrive.onTrue(new InstantCommand(() -> {
                        RobotContainer.drive.zeroGyroWithAlliance();
                }));
                xButtonDrive.onTrue(new InstantCommand(() -> RobotContainer.drive.lock()));

                // Configure the shoot button
                shootButton.whileTrue(new ShootCommand());

                Shuffleboard.getTab("Drive").add("Controls", this);
        }

        public double[] getJoystickAxesFromDriveJoystick() {
                double[] joystickAxes = {
                                driveJoystick.getLeftX(),
                                driveJoystick.getLeftY(),
                                driveJoystick.getRightX(),
                                driveJoystick.getRightY()
                };
                for (int i = 0; i < joystickAxes.length; i++) {
                        if (Math.abs(joystickAxes[i]) < Constants.Controls.deadBandDrive) {
                                joystickAxes[i] = 0.0;
                        }
                        joystickAxes[i] *= getAccelerationSensitivity();
                }
                return joystickAxes;
        }

        public double[] getJoystickAxesFromOperatorJoystick() {
                double[] joystickAxes = {
                                operatorJoystick.getLeftX(),
                                operatorJoystick.getLeftY(),
                                operatorJoystick.getRightX(),
                                operatorJoystick.getRightY()
                };
                for (int i = 0; i < joystickAxes.length; i++) {
                        if (Math.abs(joystickAxes[i]) < Constants.Controls.deadBandDrive) {
                                joystickAxes[i] = 0.0;
                        }
                        joystickAxes[i] *= getAccelerationSensitivity();
                }
                return joystickAxes;
        }

        // Shuffleboard
        public void initSendable(SendableBuilder builder) {
                builder.setSmartDashboardType("Motor Controller");
        }

}
