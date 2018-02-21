/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5519.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
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
	
	
	// Constants and Global Variables
	
	public final static double AUTO_FAST_SPEED = 0.7;
	public final static double AUTO_MEDIUM_SPEED = 0.5;
	public final static double AUTO_SLOW_SPEED = 0.3;

	public enum PinType { DigitalIO, PWM, AnalogIn, AnalogOut };
	
	static int MAX_NAVX_MXP_DIGIO_PIN_NUMBER      = 9;
	static int MAX_NAVX_MXP_ANALOGIN_PIN_NUMBER   = 3;
	static int MAX_NAVX_MXP_ANALOGOUT_PIN_NUMBER  = 1;
	static int NUM_ROBORIO_ONBOARD_DIGIO_PINS     = 10;
	static int NUM_ROBORIO_ONBOARD_PWM_PINS       = 10;
	static int NUM_ROBORIO_ONBOARD_ANALOGIN_PINS  = 4;

	// PWM Port Assignments

	public static int kShooterMotorLeft1Port = 5;
	public static int kShooterMotorLeft2Port = 4;
	public static int kShooterMotorLeft3Port = 3;
	public static int kShooterMotorRight1Port = 2;
	public static int kShooterMotorRight2Port = 1;
	public static int kShooterMotorRight3Port = 0;

	public static int kAvailableRoboRIOPort = 6;
	public static int kClimberMotorWinchPort = 7;	

	public static int kDriveMotorLeftPort = 8;
	public static int kDriveMotorRightPort = 9;

	public static int kIntakeMotorLeftWheelPortNAVX = 9;
	public static int kIntakeMotorLeftArmPortNAVX = 8;		
	public static int kIntakeMotorRightWheelPortNAVX = 7;	
	public static int kIntakeMotorRightArmPortNAVX = 6;		

	// DIO Port Assignments
	
	public final static int kEncoderDioPortA = 0;
	public final static int kEncoderDioPortB = 1;
	
	public final static int kIntakeLeftArmMaxDioPort = 2;
	public final static int kIntakeLeftArmMinDioPort = 3;
	public final static int kIntakeRightArmMaxDioPort = 4;
	public final static int kIntakeRightArmMinDioPort = 5;
	
	// PCM / CAN Port Assignments
	
	public static int kClimberTopSolenoidInPortPCM = 0;
	public static int kClimberTopSolenoidOutPortPCM = 1;
	public static int kClimberLowSolenoidInPortPCM = 3;
	public static int kClimberLowSolenoidOutPortPCM = 2;
		
	// Controllers for the DRIVE Subsystem
	
	public static Encoder EncoderAMT10V;
	
	public static PWMSpeedController driveMotorLeft;
	public static PWMSpeedController driveMotorRight;

	// Controllers for the SHOOTER Subsystem

	public static PWMSpeedController shooterMotorLeft1;
	public static PWMSpeedController shooterMotorLeft2;
	public static PWMSpeedController shooterMotorLeft3;
	public static PWMSpeedController shooterMotorRight1;
	public static PWMSpeedController shooterMotorRight2;
	public static PWMSpeedController shooterMotorRight3;
	
	// Controllers for the INTAKE Subsystem
	
	public static DigitalInput intakeLeftArmMaxLimitSwitch;
	public static DigitalInput intakeLeftArmMinLimitSwitch;
	public static DigitalInput intakeRightArmMaxLimitSwitch;
	public static DigitalInput intakeRightArmMinLimitSwitch;
	
	public static PWMSpeedController intakeMotorLeftWheel;
	public static PWMSpeedController intakeMotorLeftArm;
	public static PWMSpeedController intakeMotorRightWheel;
	public static PWMSpeedController intakeMotorRightArm;
	
	// Controllers for the CLIMBER Subsystem
	
	public static DoubleSolenoid 	 climberTopSolenoid;
	public static DoubleSolenoid 	 climberLowSolenoid;
	
	public static PWMSpeedController climberMotorWinch;
	
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
        EncoderAMT10V = new Encoder(kEncoderDioPortA,kEncoderDioPortB,false,Encoder.EncodingType.k2X);
		
		shooterMotorLeft1 = new VictorSP(kShooterMotorLeft1Port);
		shooterMotorLeft2 = new VictorSP(kShooterMotorLeft2Port);
		shooterMotorLeft3 = new VictorSP(kShooterMotorLeft3Port);
		shooterMotorRight1 = new VictorSP(kShooterMotorRight1Port);
		shooterMotorRight2 = new VictorSP(kShooterMotorRight2Port);
		shooterMotorRight3 = new VictorSP(kShooterMotorRight3Port);
		
		intakeMotorLeftWheel = new Spark(getChannelFromPin(PinType.PWM,kIntakeMotorLeftWheelPortNAVX));
		intakeMotorLeftArm = new Talon(getChannelFromPin(PinType.PWM,kIntakeMotorLeftArmPortNAVX));
		intakeMotorRightWheel = new Spark(getChannelFromPin(PinType.PWM,kIntakeMotorRightWheelPortNAVX));
		intakeMotorRightArm = new Talon(getChannelFromPin(PinType.PWM,kIntakeMotorRightArmPortNAVX));
		intakeLeftArmMaxLimitSwitch = new DigitalInput(kIntakeLeftArmMaxDioPort);
		intakeLeftArmMinLimitSwitch = new DigitalInput(kIntakeLeftArmMinDioPort);
		intakeRightArmMaxLimitSwitch = new DigitalInput(kIntakeRightArmMaxDioPort);
		intakeRightArmMaxLimitSwitch = new DigitalInput(kIntakeRightArmMinDioPort);
		
		climberMotorWinch = new Talon(kClimberMotorWinchPort);
		climberTopSolenoid  = new DoubleSolenoid(kClimberTopSolenoidInPortPCM,kClimberTopSolenoidOutPortPCM);
		climberLowSolenoid  = new DoubleSolenoid(kClimberLowSolenoidInPortPCM,kClimberLowSolenoidOutPortPCM);
	}
}
