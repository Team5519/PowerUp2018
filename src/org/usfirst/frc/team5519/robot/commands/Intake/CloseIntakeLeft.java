package org.usfirst.frc.team5519.robot.commands.Intake;

import org.usfirst.frc.team5519.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CloseIntakeLeft extends Command {

    public CloseIntakeLeft() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	//requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.intake.resetLeftArmMaxLimitCounter();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intake.ArmCloseLeft();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        //return false;
    	boolean isMin = Robot.intake.isLeftArmAtMin();
    	Robot.m_oi.messageDriverStation("COMMAND CloseIntakeLeft reported AT MIN is = " + isMin);
        return isMin;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.ArmCloseStopLeft();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
