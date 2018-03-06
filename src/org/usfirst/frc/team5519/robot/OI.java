/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5519.robot;

import org.usfirst.frc.team5519.robot.commands.ChangeDriveDirection;
import org.usfirst.frc.team5519.robot.commands.Shooter.ShootHigh;
import org.usfirst.frc.team5519.robot.commands.Shooter.ShootLow;
import org.usfirst.frc.team5519.robot.commands.Climber.Climb;
import org.usfirst.frc.team5519.robot.commands.Climber.DeployClimberArm;
import org.usfirst.frc.team5519.robot.commands.Climber.DeployClimberHook;
import org.usfirst.frc.team5519.robot.commands.Climber.Drop;
import org.usfirst.frc.team5519.robot.commands.Climber.RetractClimber;
import org.usfirst.frc.team5519.robot.commands.Intake.CloseIntakeLeft;
import org.usfirst.frc.team5519.robot.commands.Intake.CloseIntakeRight;
import org.usfirst.frc.team5519.robot.commands.Intake.EjectCube;
import org.usfirst.frc.team5519.robot.commands.Intake.LoadCube;
import org.usfirst.frc.team5519.robot.commands.Intake.OpenIntakeLeft;
import org.usfirst.frc.team5519.robot.commands.Intake.OpenIntakeRight;

import edu.wpi.first.wpilibj.DriverStation;

//import org.usfirst.frc.team5519.robot.commands.ShootHigh;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a my joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.
	
    public static boolean doMessageDriverStation = true;
    
    public void messageDriverStation(String message) {
    	if (doMessageDriverStation) {
    	       DriverStation.reportWarning(message, false);
    	}
     }

	public static final int kDriveStickPort = 0;	
	public static final int kXboxControllerPort = 1;
	
	public static Joystick driveStick;
	public static XboxController controller;
	
	/**Button Numbers for the Joystick
	 * Trigger = 1
	 * Thumb button = 2
	 */
	
	//Driving
	public static final int kToggleTriggerButtonNumber = 1;
	public static final int kToggleDriveDirectionButtonNumber = 2;
	
	//Climber Arm
	public static final int kToggleClimberLowArmButtonNumber = 3;
	public static final int kToggleClimberTopArmButtonNumber = 4;
	public static final int kToggleClimberHookButtonNumber = 6;
	
	/**Button Numbers for the Controller
	 * X = 1
	 * A = 2
	 * B = 3
	 * Y = 4
	 * LB = 5
	 * RB = 6
	 * LT = 7
	 * RT = 8
	 * Start = 9
	 * Back = 10
	 */
	
	// Shooting
	public static final int kToggleShootHighButtonNumber = 2;
	public static final int kToggleShootLowButtonNumber = 3;
	
	// Winch
	public static final int kToggleClimberWinchUpButtonNumber = 9;
	public static final int kToggleClimberWinchDownButtonNumber = 10;
	
	// Intake Wheels
	public static final int kToggleIntakeWheelsInButtonNumber = 1;
	public static final int kToggleIntakeWheelsOutButtonNumber = 4;
	
	// Intake Arms
	public static final int kToggleIntakeArmReleaseLButtonNumber = 7;
	public static final int kToggleIntakeArmCloseLButtonNumber = 5;
	public static final int kToggleIntakeArmReleaseRButtonNumber = 8;
	public static final int kToggleIntakeArmCloseRButtonNumber = 6;
	
	/**
	public static final int kToggleAutoDriveStraightButtonNumber = 10;
	public static final int kToggleAutoTurnRightButtonNumber = 12;
	public static final int kToggleAutoTurnLeftButtonNumber = 8;   
	*/
	
	// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:
	public static Button toggleDriveDirectionButton;
	public static Button toggleShootHighButton;
	public static Button toggleShootLowButton;
	public static Button toggleLoadCubeButton;
	public static Button toggleEjectCubeButton;
	public static Button toggleArmReleaseLButton;
	public static Button toggleArmCloseLButton;
	public static Button toggleArmReleaseRButton;
	public static Button toggleArmCloseRButton;
	
	public static Button toggleClimberLowArmButton;
	public static Button toggleClimberTopArmButton;
	public static Button toggleClimberHookButton;
	public static Button toggleClimberWinchUpButton;
	public static Button toggleClimberWinchDownButton;
	
	//public static Button toggleAutoDriveStraightButton;
	//public static Button toggleAutoTurnRightButton;
	//public static Button toggleAutoTurnLeftButton;
	
	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	public OI() {
				
		OI.driveStick = new Joystick(kDriveStickPort);
		OI.controller = new XboxController(kXboxControllerPort);
		
		// Driving
		Command ChangeDriveDirection = new ChangeDriveDirection();
		OI.toggleDriveDirectionButton = new JoystickButton(OI.driveStick, kToggleDriveDirectionButtonNumber);
		OI.toggleDriveDirectionButton.toggleWhenPressed(ChangeDriveDirection);
		
		// Shooter
		Command ShootHigh = new ShootHigh();
		OI.toggleShootHighButton = new JoystickButton(OI.controller, kToggleShootHighButtonNumber);
		OI.toggleShootHighButton.whileHeld(ShootHigh);
		Command ShootLow = new ShootLow();
		OI.toggleShootLowButton = new JoystickButton(OI.controller, kToggleShootLowButtonNumber);
		OI.toggleShootLowButton.whileHeld(ShootLow);
		
		// Intake Wheels and Shooter
		Command LoadCube = new LoadCube();
		OI.toggleLoadCubeButton = new JoystickButton(OI.controller, kToggleIntakeWheelsInButtonNumber);
		OI.toggleLoadCubeButton.whileHeld(LoadCube);
		Command EjectCube = new EjectCube();
		OI.toggleEjectCubeButton = new JoystickButton(OI.controller, kToggleIntakeWheelsOutButtonNumber);
		OI.toggleEjectCubeButton.whileHeld(EjectCube);
		
		// Intake Arms
		Command OpenIntakeLeft = new OpenIntakeLeft();
		OI.toggleArmReleaseLButton = new JoystickButton(OI.controller, kToggleIntakeArmReleaseLButtonNumber);
		OI.toggleArmReleaseLButton.whileHeld(OpenIntakeLeft);
		Command CloseIntakeLeft = new CloseIntakeLeft();		// FOR TESTING
		OI.toggleArmCloseLButton = new JoystickButton(OI.controller, kToggleIntakeArmCloseLButtonNumber);
		OI.toggleArmCloseLButton.whileHeld(CloseIntakeLeft);
		Command OpenIntakeRight = new OpenIntakeRight();
		OI.toggleArmReleaseRButton = new JoystickButton(OI.controller, kToggleIntakeArmReleaseRButtonNumber);
		OI.toggleArmReleaseRButton.whileHeld(OpenIntakeRight);
		Command CloseIntakeRight = new CloseIntakeRight();		// FOR TESTING
		OI.toggleArmCloseRButton = new JoystickButton(OI.controller, kToggleIntakeArmCloseRButtonNumber);
		OI.toggleArmCloseRButton.whileHeld(CloseIntakeRight);
		
		// Climber Arm
		Command DeployLowArm = new DeployClimberArm();
		OI.toggleClimberLowArmButton = new JoystickButton(OI.driveStick, kToggleClimberLowArmButtonNumber);
		OI.toggleClimberLowArmButton.toggleWhenPressed(DeployLowArm);
		Command DeployTopArm = new DeployClimberHook();
		OI.toggleClimberTopArmButton = new JoystickButton(OI.driveStick, kToggleClimberTopArmButtonNumber);
		OI.toggleClimberTopArmButton.toggleWhenPressed(DeployTopArm);
		Command RetractHook = new RetractClimber();
		OI.toggleClimberHookButton = new JoystickButton(OI.driveStick, kToggleClimberHookButtonNumber);
		OI.toggleClimberHookButton.toggleWhenPressed(RetractHook);
		
		// Winch
		Command Climb = new Climb();
		OI.toggleClimberWinchUpButton = new JoystickButton(OI.controller, kToggleClimberWinchUpButtonNumber);
		OI.toggleClimberWinchUpButton.toggleWhenPressed(Climb);
		Command Drop = new Drop();
		OI.toggleClimberWinchDownButton = new JoystickButton(OI.controller, kToggleClimberWinchDownButtonNumber);
		OI.toggleClimberWinchDownButton.whileHeld(Drop);
		
		//Command AutoDriveStraight = new AutoDriveStraightDistance(3.0);
		//OI.toggleAutoDriveStraightButton = new JoystickButton(OI.driveStick, kToggleAutoDriveStraightButtonNumber);
		//OI.toggleAutoDriveStraightButton.toggleWhenPressed(AutoDriveStraight);
		//Command AutoTurnRight = new AutoTurnRight(90.0);
		//OI.toggleAutoTurnRightButton = new JoystickButton(OI.driveStick, kToggleAutoTurnRightButtonNumber);
		//OI.toggleAutoTurnRightButton.toggleWhenPressed(AutoTurnRight);
		//Command AutoTurnLeft = new AutoTurnLeft(90.0);
		//OI.toggleAutoTurnLeftButton = new JoystickButton(OI.driveStick, kToggleAutoTurnLeftButtonNumber);
		//OI.toggleAutoTurnLeftButton.toggleWhenPressed(AutoTurnLeft);

	}
	
}
