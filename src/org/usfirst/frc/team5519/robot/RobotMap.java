/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5519.robot;

import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;
	
	
	// Motor Port Definitions
	public static int kDriveMotorLeftPort = 1;
	public static int kDriveMotorRightPort = 1;
	
	public static int kShooterMotorLeft1Port = 1;
	public static int kShooterMotorLeft2Port = 1;
	public static int kShooterMotorLeft3Port = 1;
	public static int kShooterMotorRight1Port = 1;
	public static int kShooterMotorRight2Port = 1;
	public static int kShooterMotorRight3Port = 1;
	
	public static int kIntakeMotorLeftWheelPort = 1;
	public static int kIntakeMotorLeftArmPort = 1;
	public static int kIntakeMotorRightWheelPort = 1;
	public static int kIntakeMotorRightArmPort = 1;
	
	public static int kClimberMotorWinchPort = 1;
	
	// Motor Controller Definitions
	public static PWMSpeedController driveMotorLeft;
	public static PWMSpeedController driveMotorRight;
	
	public static PWMSpeedController shooterMotorLeft1;
	public static PWMSpeedController shooterMotorLeft2;
	public static PWMSpeedController shooterMotorLeft3;
	public static PWMSpeedController shooterMotorRight1;
	public static PWMSpeedController shooterMotorRight2;
	public static PWMSpeedController shooterMotorRight3;

	public static PWMSpeedController intakeMotorLeftWheel;
	public static PWMSpeedController intakeMotorLeftArm;
	public static PWMSpeedController intakeMotorRightWheel;
	public static PWMSpeedController intakeMotorRightArm;

	public static PWMSpeedController climberMotorWinch;


	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	
	public static void init() {
		driveMotorLeft = new Spark(kDriveMotorLeftPort);
		driveMotorRight = new Spark(kDriveMotorRightPort);
		
		shooterMotorLeft1 = new VictorSP(kShooterMotorLeft1Port);
		shooterMotorLeft2 = new VictorSP(kShooterMotorLeft2Port);
		shooterMotorLeft3 = new VictorSP(kShooterMotorLeft3Port);
		shooterMotorRight1 = new VictorSP(kShooterMotorRight1Port);
		shooterMotorRight2 = new VictorSP(kShooterMotorRight2Port);
		shooterMotorRight3 = new VictorSP(kShooterMotorRight3Port);
		
		intakeMotorLeftWheel = new Spark(kIntakeMotorLeftWheelPort);
		intakeMotorLeftArm = new Talon(kIntakeMotorLeftArmPort);
		intakeMotorRightWheel = new Spark(kIntakeMotorRightWheelPort);
		intakeMotorRightArm = new Talon(kIntakeMotorRightArmPort);
		
		climberMotorWinch = new Talon(kClimberMotorWinchPort);
	}
}
