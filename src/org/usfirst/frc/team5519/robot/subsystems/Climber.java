package org.usfirst.frc.team5519.robot.subsystems;

import org.usfirst.frc.team5519.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
	public void Climb()	{
		RobotMap.climberMotorWinch.set(-1.0);
	}
	
	public void Stop()	{
		RobotMap.climberMotorWinch.set(0.0);
	}

}

