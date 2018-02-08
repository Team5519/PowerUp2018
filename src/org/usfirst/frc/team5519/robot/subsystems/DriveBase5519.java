package org.usfirst.frc.team5519.robot.subsystems;

import org.usfirst.frc.team5519.robot.RobotMap;
import org.usfirst.frc.team5519.robot.commands.DriveWithJoystick;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveBase5519 extends Subsystem {

	AHRS ahrs;
	RobotDrive myDrive;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public DriveBase5519() {
		myDrive = new RobotDrive(RobotMap.driveMotorLeft, RobotMap.driveMotorRight);
        //myDrive.setSafetyEnabled(true); 	// Ensure motor safety
        //myDrive.setExpiration(0.1);			// Suggested default safety timeout
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveWithJoystick());
    }
    
	/**
	 * Just Drive! Under joystick command. 
	 * Code stolen from RobotDrive
	 */
	public void drive(GenericHID stick) {
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
	 public void directDrive(double moveValue, double targetAngle) {
		//DriverStation.reportWarning("Drive Rotate Bot rotateAngle:  " + targetAngle, false);
		/*
	    double rotateValue = -0.200;
	    if (targetAngle < 0.0) {
	    	rotateValue = -1.3 * rotateValue;
	        //DriverStation.reportWarning("Rotate in place applying CORRECTION.", false);
	    }
	    */
	 	//myDrive.arcadeDrive(pidMoveValue(moveValue), pidRotateValue (targetAngle));
		myDrive.arcadeDrive(moveValue, targetAngle);
	 }

	 public void stopDead() {
		myDrive.arcadeDrive(0, 0);
		/*
		if (ahrs.getVelocityY() > 0.1) {
		 	myDrive.arcadeDrive(-0.05, 0);			 			
		} else if (ahrs.getVelocityY() < -0.1) {
		 	myDrive.arcadeDrive(0.05, 0);			 			
		} else {
		 	myDrive.arcadeDrive(0, 0);			 			
		}
		*/
	 }

}

