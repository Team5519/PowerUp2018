package org.usfirst.frc.team5519.robot.commands;

import org.usfirst.frc.team5519.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OpenIntakeRight extends Command {

    public OpenIntakeRight() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intake.ArmReleaseRight();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean isMax = Robot.intake.isRightArmAtMax();
    	Robot.m_oi.messageDriverStation("COMMAND OpenIntakeRight reported AT MAX is = " + isMax);
        return isMax;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.ArmReleaseStopRight();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
