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
		resetLeftArmMaxLimitCounter();
		resetLeftArmMinLimitCounter();
		resetRightArmMaxLimitCounter();
		resetRightArmMinLimitCounter();
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
 
    public void resetLeftArmMaxLimitCounter() {
    	if (leftArmMaxLimitCounter == null) {
    		leftArmMaxLimitCounter = new Counter(RobotMap.intakeLeftArmMaxLimitSwitch);
    	}
    	leftArmMaxLimitCounter.reset();	
    }
    
    public void resetLeftArmMinLimitCounter() {
    	if (leftArmMinLimitCounter == null) {
    		leftArmMinLimitCounter = new Counter(RobotMap.intakeLeftArmMinLimitSwitch);
    	}
    	leftArmMinLimitCounter.reset();
    }
    
    public void resetRightArmMaxLimitCounter() {
    	if (rightArmMaxLimitCounter == null) {
    		rightArmMaxLimitCounter = new Counter(RobotMap.intakeRightArmMaxLimitSwitch);
    	}
    	rightArmMaxLimitCounter.reset();    	
    }
    
    public void resetRightArmMinLimitCounter() {
    	if (rightArmMinLimitCounter == null) {
    		rightArmMinLimitCounter = new Counter(RobotMap.intakeRightArmMinLimitSwitch);
    	}
    	rightArmMinLimitCounter.reset();   	
    }
    
	public boolean isLeftArmAtMax() {
		return (leftArmMaxLimitCounter.get() > 0);
    }

	public boolean isLeftArmAtMin() {
		return (leftArmMinLimitCounter.get() > 0);
    }

	public boolean isRightArmAtMax() {
		return (rightArmMaxLimitCounter.get() > 0);
    }

	public boolean isRightArmAtMin() {
		return (rightArmMinLimitCounter.get() > 0);
    }

	
	public void ArmReleaseLeft()	{
		RobotMap.intakeMotorLeftArm.set(0.4);		
	}

	public void ArmReleaseRight()	{
		RobotMap.intakeMotorRightArm.set(0.4);		
	}

	public void ArmReleaseStopLeft()	{
		RobotMap.intakeMotorLeftArm.set(0.0);
		leftArmMinLimitCounter.reset(); 			// Enable Arm Closing
	}	
	
	public void ArmReleaseStopRight()	{
		RobotMap.intakeMotorRightArm.set(0.0);
		rightArmMinLimitCounter.reset(); 			// Enable Arm Closing
	}

	public void ArmCloseLeft()	{
		RobotMap.intakeMotorLeftArm.set(-0.6);
	}
	
	public void ArmCloseRight()	{
		RobotMap.intakeMotorRightArm.set(-0.6);
	}
	
	public void ArmCloseStopLeft()	{
		RobotMap.intakeMotorLeftArm.set(0.0);
		leftArmMaxLimitCounter.reset();				// Enable Arm Opening
	}
	
	public void ArmCloseStopRight()	{
		RobotMap.intakeMotorRightArm.set(0.0);
		rightArmMaxLimitCounter.reset();			// Enable Arm Opening
	}

}

