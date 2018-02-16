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
	public static int kDriveMotorLeftPort = 8;
	public static int kDriveMotorRightPort = 9;
	
	public static int kShooterMotorLeft1Port = 5;
	public static int kShooterMotorLeft2Port = 4;
	public static int kShooterMotorLeft3Port = 3;
	public static int kShooterMotorRight1Port = 2;
	public static int kShooterMotorRight2Port = 1;
	public static int kShooterMotorRight3Port = 0;
	
	public enum PinType { DigitalIO, PWM, AnalogIn, AnalogOut };
	
	public static int kIntakeMotorLeftWheelPortNAVX = 9;
	public static int kIntakeMotorLeftArmPortNAVX = 8;		
	public static int kIntakeMotorRightWheelPortNAVX = 7;	
	public static int kIntakeMotorRightArmPortNAVX = 6;		
	
	public static int kClimberMotorWinchPort = 7;		
	
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

	
	static int MAX_NAVX_MXP_DIGIO_PIN_NUMBER      = 9;
	static int MAX_NAVX_MXP_ANALOGIN_PIN_NUMBER   = 3;
	static int MAX_NAVX_MXP_ANALOGOUT_PIN_NUMBER  = 1;
	static int NUM_ROBORIO_ONBOARD_DIGIO_PINS     = 10;
	static int NUM_ROBORIO_ONBOARD_PWM_PINS       = 10;
	static int NUM_ROBORIO_ONBOARD_ANALOGIN_PINS  = 4;
	
	/* getChannelFromPin( PinType, int ) - converts from a navX-MXP */
    /* Pin type and number to the corresponding RoboRIO Channel     */
    /* Number, which is used by the WPI Library functions.          */
    
    static int getChannelFromPin( PinType type, int io_pin_number ) 
               throws IllegalArgumentException {
        int roborio_channel = 0;
        if ( io_pin_number < 0 ) {
            throw new IllegalArgumentException("Error:  navX-MXP I/O Pin #");
        }
        switch ( type ) {
        case DigitalIO:
            if ( io_pin_number > MAX_NAVX_MXP_DIGIO_PIN_NUMBER ) {
                throw new IllegalArgumentException("Error:  Invalid navX-MXP Digital I/O Pin #");
            }
            roborio_channel = io_pin_number + NUM_ROBORIO_ONBOARD_DIGIO_PINS + 
                              (io_pin_number > 3 ? 4 : 0);
            break;
        case PWM:
            if ( io_pin_number > MAX_NAVX_MXP_DIGIO_PIN_NUMBER ) {
                throw new IllegalArgumentException("Error:  Invalid navX-MXP Digital I/O Pin #");
            }
            roborio_channel = io_pin_number + NUM_ROBORIO_ONBOARD_PWM_PINS;
            break;
        case AnalogIn:
            if ( io_pin_number > MAX_NAVX_MXP_ANALOGIN_PIN_NUMBER ) {
                throw new IllegalArgumentException("Error:  Invalid navX-MXP Analog Input Pin #");
            }
            roborio_channel = io_pin_number + NUM_ROBORIO_ONBOARD_ANALOGIN_PINS;
            break;
        case AnalogOut:
            if ( io_pin_number > MAX_NAVX_MXP_ANALOGOUT_PIN_NUMBER ) {
                throw new IllegalArgumentException("Error:  Invalid navX-MXP Analog Output Pin #");
            }
            roborio_channel = io_pin_number;            
            break;
        }
        return roborio_channel;
    }
	
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
		
		intakeMotorLeftWheel = new Spark(getChannelFromPin(PinType.PWM,kIntakeMotorLeftWheelPortNAVX));
		//intakeMotorLeftWheel = new Spark(6);
		intakeMotorLeftArm = new Talon(getChannelFromPin(PinType.PWM,kIntakeMotorLeftArmPortNAVX));
		intakeMotorRightWheel = new Spark(getChannelFromPin(PinType.PWM,kIntakeMotorRightWheelPortNAVX));
		//intakeMotorRightWheel = new Spark(7);
		intakeMotorRightArm = new Talon(getChannelFromPin(PinType.PWM,kIntakeMotorRightArmPortNAVX));
		
		//climberMotorWinch = new Talon(kClimberMotorWinchPort);
	}
}
