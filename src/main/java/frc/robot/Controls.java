package frc.robot;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.AngleSetterCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.OutspittCommand;
import frc.robot.commands.ShootCommand;

public class Controls implements Sendable{
    public CommandXboxController operatorJoystick = new CommandXboxController(
                        Constants.Joystick.ID);

    Trigger ltButtonOperator = operatorJoystick.leftTrigger();
    Trigger rtButtonOperator = operatorJoystick.rightTrigger();
    Trigger lbButtonOperator = operatorJoystick.leftBumper();
    Trigger rbButtonOperator = operatorJoystick.rightBumper();
    Trigger aButtonOperator = operatorJoystick.a();
    Trigger bButtonOperator = operatorJoystick.b();
    Trigger xButtonOperator = operatorJoystick.x();
    Trigger yButtonOperator = operatorJoystick.y();
    Trigger windowsButtonOperator = operatorJoystick.back();
    Trigger burgerButtonOperator = operatorJoystick.start();
    Trigger pov0Operator = operatorJoystick.povUp();
    Trigger pov2Operator = operatorJoystick.povRight();
    Trigger pov3Operator = operatorJoystick.povDownRight();
    Trigger pov4Operator = operatorJoystick.povDown();
    Trigger pov5Operator = operatorJoystick.povDownLeft();
    Trigger pov6Operator = operatorJoystick.povLeft();
    Trigger leftStickOperator = operatorJoystick.leftStick();

    public Controls() {
        yButtonOperator.whileTrue(new AngleSetterCommand(1));
        aButtonOperator.whileTrue(new AngleSetterCommand(-1));

        rtButtonOperator.whileTrue(new ShootCommand().alongWith(new IntakeCommand()));

        ltButtonOperator.whileTrue(new IntakeCommand());
        lbButtonOperator.whileTrue(new OutspittCommand());
    }

    public void initSendable(SendableBuilder builder) {

    }
}
