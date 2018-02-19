package org.usfirst.frc.team5519.robot.commands;

import org.usfirst.frc.team5519.robot.Robot;
import org.usfirst.frc.team5519.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoTurnRight extends Command {

	private double kP = 0.1;
	private double kTolerance = 2;
	private double requiredAngle;
	
    public AutoTurnRight(double angle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveBase);
    	requiredAngle = angle;
    	requiredAngle = 90;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveBase.stopDead();
    	Robot.driveBase.resetDriveSensors();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
       	Robot.m_oi.messageDriverStation("COMMAND AutoTurnRight reported angle is = " + Robot.driveBase.getGyroAngle());
       	double angle = Robot.driveBase.getGyroAngle();
       	double increment = (requiredAngle - angle) * kP;
       	Robot.driveBase.autoDrive(RobotMap.AUTO_SLOW_SPEED,increment);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
       	double angle = Robot.driveBase.getGyroAngle();
    	if (Math.abs(requiredAngle - angle) <= kTolerance) {
    		Robot.m_oi.messageDriverStation("COMMAND AutoTurnRight ARRIVED angle is = " + Robot.driveBase.getGyroAngle());
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
