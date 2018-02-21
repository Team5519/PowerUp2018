package org.usfirst.frc.team5519.robot.subsystems;

import org.usfirst.frc.team5519.robot.RobotMap;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private Counter leftArmMaxLimitCounter;
	private Counter leftArmMinLimitCounter;
	private Counter rightArmMaxLimitCounter;
	private Counter rightArmMinLimitCounter;

	
	public Intake() {
		//resetleftArmMaxLimitCounter();
		//resetleftArmMinLimitCounter();
		//resetRightArmMaxLimitCounter();
		//resetRightArmMinLimitCounter();
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
 
    private void resetleftArmMaxLimitCounter() {
    	if (leftArmMaxLimitCounter == null) {
    		leftArmMaxLimitCounter = new Counter(RobotMap.intakeLeftArmMaxLimitSwitch);
    	}
    	leftArmMaxLimitCounter.reset();	
    }
    
    private void resetleftArmMinLimitCounter() {
    	if (leftArmMinLimitCounter == null) {
    		leftArmMinLimitCounter = new Counter(RobotMap.intakeLeftArmMinLimitSwitch);
    	}
    	leftArmMinLimitCounter.reset();    	
    }
    
    private void resetRightArmMaxLimitCounter() {
    	if (rightArmMaxLimitCounter == null) {
    		rightArmMaxLimitCounter = new Counter(RobotMap.intakeRightArmMaxLimitSwitch);
    	}
    	rightArmMaxLimitCounter.reset();    	
    }
    
    private void resetRightArmMinLimitCounter() {
    	if (rightArmMinLimitCounter == null) {
    		rightArmMinLimitCounter = new Counter(RobotMap.intakeRightArmMinLimitSwitch);
    	}
    	rightArmMinLimitCounter.reset();   	
    }
    
	public boolean isLeftArmAtMax() {
		if (leftArmMaxLimitCounter != null) { 
			return leftArmMaxLimitCounter.get() > 0;
    	} else {
    		return true;
    	}  		
    }

	public boolean isLeftArmAtMin() {
		if (leftArmMinLimitCounter != null) { 
			return leftArmMinLimitCounter.get() > 0;
    	} else {
    		return true;
    	}  		
    }

	public boolean isRightArmAtMax() {
		if (rightArmMaxLimitCounter != null) { 
			return rightArmMaxLimitCounter.get() > 0;
    	} else {
    		return true;
    	}  		
    }

	public boolean isRightArmAtMin() {
		if (rightArmMinLimitCounter != null) { 
			return rightArmMinLimitCounter.get() > 0;
    	} else {
    		return true;
    	}  		
    }

	public void WheelsRotateLoadDirection()	{
		RobotMap.intakeMotorLeftWheel.set(1.0);
		RobotMap.intakeMotorRightWheel.set(-1.0);
	}

	public void WheelsRotateEjectDirection()	{
		RobotMap.intakeMotorLeftWheel.set(-1.0);
		RobotMap.intakeMotorRightWheel.set(1.0);
	}

	public void WheelsRotationStop()	{
		RobotMap.intakeMotorLeftWheel.set(0.0);
		RobotMap.intakeMotorRightWheel.set(0.0);
	}
	
	public void ArmRelease()	{
		RobotMap.intakeMotorLeftArm.set(0.2);		//NEEDS TESTING
		RobotMap.intakeMotorRightArm.set(-0.2);		//NEEDS TESTING
	}
	
	public void ArmReleaseStop()	{
		RobotMap.intakeMotorLeftArm.set(0.0);
		RobotMap.intakeMotorRightArm.set(0.0);
	}
	
	public void ArmClose()	{
		RobotMap.intakeMotorLeftArm.set(-0.2);
		RobotMap.intakeMotorRightArm.set(0.2);
	}
	
	public void ArmCloseStop()	{
		RobotMap.intakeMotorLeftArm.set(0.0);
		RobotMap.intakeMotorRightArm.set(0.0);
	}
}

