package org.usfirst.frc.team5519.robot.subsystems;

import org.usfirst.frc.team5519.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;

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
    public void extendLowArm()	{
    	RobotMap.climberLowSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void retractLowArm()	{
    	RobotMap.climberLowSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public void extendTopArm()	{
    	RobotMap.climberTopSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    public void retractTopArm()	{
    	RobotMap.climberTopSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void retractHook()	{
    	RobotMap.climberTopSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
	public void startClimb()	{
		RobotMap.climberMotorWinch.set(-1.0);
	}
	
	public void stopClimb()	{
		RobotMap.climberMotorWinch.set(0.0);
	}
	
	public void startDrop()	{
		RobotMap.climberMotorWinch.set(1.0);
	}

	public void stopDrop()	{
		RobotMap.climberMotorWinch.set(0.0);
	}
}

