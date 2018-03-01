package org.usfirst.frc.team5519.robot.subsystems;

import org.usfirst.frc.team5519.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake2 extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
	public void WheelsRotateLoadDirection()	{
		RobotMap.intakeMotorLeftWheel.set(1.0);
		RobotMap.intakeMotorRightWheel.set(-1.0);
		RobotMap.shooterMotorLeft1.set(-0.3);
		RobotMap.shooterMotorRight1.set(0.3);
	}

	public void WheelsRotateEjectDirection()	{
		RobotMap.intakeMotorLeftWheel.set(-1.0);
		RobotMap.intakeMotorRightWheel.set(1.0);
	}

	public void WheelsRotationStop()	{
		RobotMap.intakeMotorLeftWheel.set(0.0);
		RobotMap.intakeMotorRightWheel.set(0.0);
		RobotMap.shooterMotorLeft1.set(0.0);
		RobotMap.shooterMotorRight1.set(0.0);
	}
	
}

