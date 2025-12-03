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
  public final static class Drive {
    public static final double driveSpeed = 0.8;
    public static final double rotationSpeedlimit = 0.5;

    public static class Motors {
      public static final int FRONTRIGHT = 10;
      public static final int FRONTLEFT = 11;
      public static final int BACKRIGHT = 12;
      public static final int BACKLEFT = 13;
    }
  }

  public static class Joystick {
    public static int ID = 0;
  
    
  }
  public static final class Arm {
    public static final int UPPER_FOLLOWER = 23;
    public static final int UPPER_MASTER = 22;
    public static final int BASE_ARM_MASTER = 20;
    public static final int BASE_ARM_FOLLOWER = 21;


    public static final double UPPERARM_KP = 1.3;
    public static final double UPPERARM_KI = 0.5;
    public static final double UPPERARM_KD = 0.2;
    
  }

}
