/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5519.robot;

import org.usfirst.frc.team5519.robot.commands.AutoDriveStraightDistance;
import org.usfirst.frc.team5519.robot.commands.AutoTurnLeft;
import org.usfirst.frc.team5519.robot.commands.AutoTurnRight;
import org.usfirst.frc.team5519.robot.commands.ChangeDriveDirection;
import org.usfirst.frc.team5519.robot.commands.Climb;
import org.usfirst.frc.team5519.robot.commands.CloseIntake;
import org.usfirst.frc.team5519.robot.commands.Climb;
import org.usfirst.frc.team5519.robot.commands.CloseIntakeLeft;
import org.usfirst.frc.team5519.robot.commands.CloseIntakeRight;
import org.usfirst.frc.team5519.robot.commands.DeployClimberArm;
import org.usfirst.frc.team5519.robot.commands.DeployClimberHook;
import org.usfirst.frc.team5519.robot.commands.Drop;
import org.usfirst.frc.team5519.robot.commands.EjectCube;
import org.usfirst.frc.team5519.robot.commands.LoadCube;
import org.usfirst.frc.team5519.robot.commands.OpenIntake;
import org.usfirst.frc.team5519.robot.commands.ShootHigh;
import org.usfirst.frc.team5519.robot.commands.ShootLow;
import org.usfirst.frc.team5519.robot.subsystems.Climber;
import org.usfirst.frc.team5519.robot.commands.OpenIntakeLeft;
import org.usfirst.frc.team5519.robot.commands.OpenIntakeRight;
import org.usfirst.frc.team5519.robot.commands.RetractClimberHook;

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
	
	// These are the buttons on the Joystick.
	
	public static final int kToggleTriggerButtonNumber = 1;
	public static final int kToggleDriveDirectionButtonNumber = 2;
	
	public static final int kToggleClimberLowArmButtonNumber = 7; // 7
	public static final int kToggleClimberTopArmButtonNumber = 9; // 9
	public static final int kToggleClimberHookButtonNumber = 11;  // 11
	
	
	// These are the buttons on the controller.
	public static final int kToggleShootHighButtonNumber = 2;	// X
	public static final int kToggleShootLowButtonNumber = 3;	// B
	
	public static final int kToggleClimberWinchUpButtonNumber = 9;  // 11
	public static final int kToggleClimberWinchDownButtonNumber = 10;  // 11
	
	public static final int kToggleIntakeWheelsInButtonNumber = 1;	// RT
	public static final int kToggleIntakeWheelsOutButtonNumber = 4;	// LT
	
	public static final int kToggleIntakeArmReleaseLButtonNumber = 7; // LB
	public static final int kToggleIntakeArmCloseLButtonNumber = 5; 	 // RB
	public static final int kToggleIntakeArmReleaseRButtonNumber = 8; // LB
	public static final int kToggleIntakeArmCloseRButtonNumber = 6; 	 // RB
	
	//public static final int kToggleAutoDriveStraightButtonNumber = 10; // 10
	//public static final int kToggleAutoTurnRightButtonNumber = 12;	   // 12
	//public static final int kToggleAutoTurnLeftButtonNumber = 8;	   // 8
	
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
		
		Command ChangeDriveDirection = new ChangeDriveDirection();
		OI.toggleDriveDirectionButton = new JoystickButton(OI.driveStick, kToggleDriveDirectionButtonNumber);
		OI.toggleDriveDirectionButton.toggleWhenPressed(ChangeDriveDirection);

		Command ShootHigh = new ShootHigh();
		OI.toggleShootHighButton = new JoystickButton(OI.controller, kToggleShootHighButtonNumber);
		OI.toggleShootHighButton.toggleWhenPressed(ShootHigh);
		Command ShootLow = new ShootLow();
		OI.toggleShootLowButton = new JoystickButton(OI.controller, kToggleShootLowButtonNumber);
		OI.toggleShootLowButton.toggleWhenPressed(ShootLow);
		
		Command LoadCube = new LoadCube();
		OI.toggleLoadCubeButton = new JoystickButton(OI.controller, kToggleIntakeWheelsInButtonNumber);
		OI.toggleLoadCubeButton.toggleWhenPressed(LoadCube);
		Command EjectCube = new EjectCube();
		OI.toggleEjectCubeButton = new JoystickButton(OI.controller, kToggleIntakeWheelsOutButtonNumber);
		OI.toggleEjectCubeButton.toggleWhenPressed(EjectCube);
		
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
		
		Command DeployLowArm = new DeployClimberArm();
		OI.toggleClimberLowArmButton = new JoystickButton(OI.driveStick, kToggleClimberLowArmButtonNumber);
		OI.toggleClimberLowArmButton.toggleWhenPressed(DeployLowArm);
		Command DeployTopArm = new DeployClimberHook();
		OI.toggleClimberTopArmButton = new JoystickButton(OI.driveStick, kToggleClimberTopArmButtonNumber);
		OI.toggleClimberTopArmButton.toggleWhenPressed(DeployTopArm);
		Command RetractHook = new RetractClimberHook();
		OI.toggleClimberHookButton = new JoystickButton(OI.driveStick, kToggleClimberHookButtonNumber);
		OI.toggleClimberHookButton.toggleWhenPressed(RetractHook);
		Command Climb = new Climb();
		OI.toggleClimberWinchUpButton = new JoystickButton(OI.controller, kToggleClimberWinchUpButtonNumber);
		OI.toggleClimberWinchUpButton.toggleWhenPressed(Climb);
		Command Drop = new Drop();
		OI.toggleClimberWinchDownButton = new JoystickButton(OI.controller, kToggleClimberWinchDownButtonNumber);
		OI.toggleClimberWinchDownButton.toggleWhenPressed(Drop);
		
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
