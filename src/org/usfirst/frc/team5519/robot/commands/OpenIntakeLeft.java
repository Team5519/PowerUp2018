package org.usfirst.frc.team5519.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team5519.robot.Robot;

/**
 *
 */
public class OpenIntakeLeft extends Command {

    public OpenIntakeLeft() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	//requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize()	{
    	//Robot.intake.resetLeftArmMinLimitCounter();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intake.ArmReleaseLeft();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean isMax = Robot.intake.isLeftArmAtMax();
    	Robot.m_oi.messageDriverStation("COMMAND OpenIntakeLeft reported AT MAX is = " + isMax);
        return isMax;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.ArmReleaseStopLeft();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
