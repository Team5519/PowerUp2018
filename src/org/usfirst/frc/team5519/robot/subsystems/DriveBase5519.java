package org.usfirst.frc.team5519.robot.subsystems;

import org.usfirst.frc.team5519.robot.Robot;
import org.usfirst.frc.team5519.robot.RobotMap;
import org.usfirst.frc.team5519.robot.commands.DriveWithJoystick;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveBase5519 extends Subsystem {

	private double kP = -0.03;
	private AHRS gyro;
	
	
	RobotDrive myDrive;
	
	private Encoder encoder;
    private static final double kPulsesPerRotation = 100;	// Set via DIP
    private static final double kWheelDiameter = 0.10;		// i.e. 10 cm
    private static final double kDistancePerPulse = (3.14 * kWheelDiameter) / kPulsesPerRotation; // in meters

	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public DriveBase5519() {
		gyro = Robot.ahrs;
		myDrive = new RobotDrive(RobotMap.driveMotorLeft, RobotMap.driveMotorRight);
		encoder = RobotMap.EncoderAMT10V;
		encoder.setDistancePerPulse(kDistancePerPulse);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveWithJoystick());
    }
    
	 public void resetDriveSensors() {
		 gyro.reset();
		 encoder.reset();
	 }
	    
	 public double getDistanceTraveled() {
    	//return ahrs.getDisplacementY();
	   	return encoder.getDistance();
	 }
	 
	 public double getGyroAngle() {
	    	//return ahrs.getDisplacementY();
		   	return gyro.getAngle();
		 }
		 
	public void joystickDrive(GenericHID stick) {
		myDrive.arcadeDrive(stick);
		/*
		SmartDashboard.putNumber(   "Joystick/Y-Axis Value",       stick.getY());
		SmartDashboard.putNumber(   "Joystick/X-Axis Value",       stick.getX());
		*/
		/*
 		double moveValue = 1 * stick.getY();
 		if(!isGearFront)	{
 			moveValue = -1 * moveValue;
 		}
		// Correct left / right by inverting X-Axis values.
		double rotateValue = -0.7 * stick.getX();
		myDrive.arcadeDrive(moveValue, rotateValue, true);
		*/
	}
	
	/**
	 * Drive using direct values. 
	 * Code stolen from RobotDrive
	 */
	 public void autoDrive(double moveValue, double targetAngle) {
		myDrive.arcadeDrive(moveValue, targetAngle);
	 }

	 public void autoDriveStraight(double moveValue) {
		double angle = -1 * gyro.getAngle() * kP;
		autoDrive(moveValue, angle);
	 }
	 
	 public void stopDead() {
		autoDrive(0,0);
	 }
	 

}

