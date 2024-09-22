// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.fridowpi.motors.FridoCanSparkMax;
import frc.fridowpi.motors.FridoFalcon500;
import frc.fridowpi.motors.FridoFalcon500v6;
import frc.fridowpi.motors.FridoTalonSRX;
import frc.fridowpi.motors.FridolinsMotor;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the
 * build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    /**
     * This function is run when the robot is first started up and should be used
     * for any
     * initialization code.
     */
    @Override
    public void robotInit() {
        // Instantiate our RobotContainer. This will perform all our button bindings,
        // and put our
        // autonomous chooser on the dashboard.
    }

    /**
     * This function is called every 20 ms, no matter the mode. Use this for items
     * like diagnostics
     * that you want ran during disabled, autonomous, teleoperated and test.
     *
     * <p>
     * This runs after the mode specific periodic functions, but before LiveWindow
     * and
     * SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {
        // Runs the Scheduler. This is responsible for polling buttons, adding
        // newly-scheduled
        // commands, running already-scheduled commands, removing finished or
        // interrupted commands,
        // and running subsystem periodic() methods. This must be called from the
        // robot's periodic
        // block in order for anything in the Command-based framework to work.
        CommandScheduler.getInstance().run();
    }

    /** This function is called once each time the robot enters Disabled mode. */
    @Override
    public void disabledInit() {
    }

    @Override
    public void disabledPeriodic() {
    }

    /**
     * This autonomous runs the autonomous command selected by your
     * {@link RobotContainer} class.
     */
    @Override
    public void autonomousInit() {
    }

    /** This function is called periodically during autonomous. */
    @Override
    public void autonomousPeriodic() {
    }

    /*
     * ################################################################################
     * Exercise: 
     *  1. Initialize a motor (Motor Type: TBD, ID: TBD)
     *  2. Let the motor run at 50%
     * ################################################################################
     */
    static final int id = -1;
    FridolinsMotor motor;

    @Override
    public void teleopInit() {

        // Type Falcon500 with old firmware
        motor = new FridoFalcon500(id);
        
        // Type Falcon500 with new Phoneix v6 firmware
        motor = new FridoFalcon500v6(id);

        // Type CAN Spark Max
        motor = new FridoCanSparkMax(id, MotorType.kBrushless);

        // TalonSRX
        motor = new FridoTalonSRX(id);
    }

    /** This function is called periodically during operator control. */
    @Override
    public void teleopPeriodic() {
        motor.set(0.5);
    }

    @Override
    public void testInit() {
    }

    /** This function is called periodically during test mode. */
    @Override
    public void testPeriodic() {
    }

    /** This function is called once when the robot is first started up. */
    @Override
    public void simulationInit() {
    }

    /** This function is called periodically whilst in simulation. */
    @Override
    public void simulationPeriodic() {
    }
}