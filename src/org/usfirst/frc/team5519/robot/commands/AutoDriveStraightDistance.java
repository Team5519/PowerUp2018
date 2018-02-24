package org.usfirst.frc.team5519.robot.commands;

import org.usfirst.frc.team5519.robot.Robot;
import org.usfirst.frc.team5519.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveStraightDistance extends Command {
	
	private double requiredDistance;

    public AutoDriveStraightDistance(double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveBase);
    	requiredDistance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveBase.stopDead();
    	Robot.driveBase.resetDriveSensors();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.m_oi.messageDriverStation("COMMAND AutoDriveStraightDistance reported distance is = " + 
    			Robot.driveBase.getDistanceTraveled());
    	Robot.m_oi.messageDriverStation("COMMAND AutoDriveStraightDistance reported angle is = " + 
    			Robot.driveBase.getGyroAngle());
    	Robot.driveBase.dumpSensorData("AutoDriveStraightDistance");   	
    	if (Math.abs(Robot.driveBase.getDistanceTraveled()) > (requiredDistance-0.25)) {
        	Robot.driveBase.autoDriveStraight(RobotMap.AUTO_SLOW_SPEED);
        } else {
        	Robot.driveBase.autoDriveStraight(RobotMap.AUTO_FAST_SPEED);
        }

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Math.abs(Robot.driveBase.getDistanceTraveled()) >= requiredDistance) {
    		Robot.m_oi.messageDriverStation("COMMAND DriveStraightDistance ARRIVED distance is = " + Robot.driveBase.getDistanceTraveled());
    		return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveBase.stopDead();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
