// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class Drive {
      public static final double slowSpeed = 0.375;
      public static final double normalSpeed = 1;
      public static final double encoderToMetersConversion = -22.14274406;

      public static final int motorL_ID = 10; // master
      public static final int motorL2_ID = 11; // follower
      public static final int motorR_ID = 12; // master
      public static final int motorR2_ID = 13; // follower
  }

  public static class Controller {
      public static final int inUseJoystick_ID = 0;
      public static final int slowButton_ID = 8;
  }
}
