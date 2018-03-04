package org.usfirst.frc.team5519.robot.subsystems;

import org.usfirst.frc.team5519.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
	public void ShootHigh()	{
		RobotMap.shooterMotorLeft1.set(-0.8);
		RobotMap.shooterMotorLeft2.set(-0.8);
		RobotMap.shooterMotorLeft3.set(-0.8);
		RobotMap.shooterMotorRight1.set(0.8);
		RobotMap.shooterMotorRight2.set(0.8);
		RobotMap.shooterMotorRight3.set(0.8);
	}
	
	public void ShootLow()	{
		RobotMap.shooterMotorLeft1.set(-0.9);
		RobotMap.shooterMotorLeft2.set(-0.7);
		RobotMap.shooterMotorLeft3.set(-0.7);
		RobotMap.shooterMotorRight1.set(0.9);
		RobotMap.shooterMotorRight2.set(0.7);
		RobotMap.shooterMotorRight3.set(0.7);
	}

	public void ReverseLow()	{
		RobotMap.shooterMotorLeft1.set(-0.8);
		RobotMap.shooterMotorLeft2.set(-0.8);
		RobotMap.shooterMotorLeft3.set(-0.8);
		RobotMap.shooterMotorRight1.set(0.8);
		RobotMap.shooterMotorRight2.set(0.8);
		RobotMap.shooterMotorRight3.set(0.8);
	}

	public void stop()	{
		RobotMap.shooterMotorLeft1.set(0.0);
		RobotMap.shooterMotorLeft2.set(0.0);
		RobotMap.shooterMotorLeft3.set(0.0);
		RobotMap.shooterMotorRight1.set(0.0);
		RobotMap.shooterMotorRight2.set(0.0);
		RobotMap.shooterMotorRight3.set(0.0);
	}

}

