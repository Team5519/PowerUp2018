/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5519.robot;

//import org.usfirst.frc.team5519.robot.commands.Climb;
import org.usfirst.frc.team5519.robot.commands.CloseIntake;
import org.usfirst.frc.team5519.robot.commands.DeployClimberArm;
import org.usfirst.frc.team5519.robot.commands.DeployClimberHook;
import org.usfirst.frc.team5519.robot.commands.EjectCube;
import org.usfirst.frc.team5519.robot.commands.LoadCube;
import org.usfirst.frc.team5519.robot.commands.ShootHigh;
import org.usfirst.frc.team5519.robot.commands.ShootLow;
import org.usfirst.frc.team5519.robot.commands.OpenIntake;
import org.usfirst.frc.team5519.robot.commands.RetractClimberHook;

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

	public static final int kDriveStickPort = 0;	
	public static final int kXboxControllerPort = 1;
	
	public static Joystick driveStick;
	public static XboxController controller;
	
	// These are the buttons on the Joystick.
	
	// These are the buttons on the controller.
	public static final int kToggleShootHighButtonNumber = 7;	// A
	public static final int kToggleShootLowButtonNumber = 8;	// A
	
	public static final int kToggleIntakeWheelsInButtonNumber = 9;	// A
	public static final int kToggleIntakeWheelsOutButtonNumber = 10;
	
	public static final int kToggleIntakeArmReleaseButtonNumber = 6;
	public static final int kToggleIntakeArmCloseButtonNumber = 5;
	
	public static final int kToggleClimbButtonNumber = 4;
	public static final int kToggleClimberLowArmButtonNumber = 3;
	public static final int kToggleClimberTopArmButtonNumber = 4;
	public static final int kToggleClimberHookButtonNumber = 5;
	
	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	public static Button toggleShootHighButton;
	public static Button toggleShootLowButton;
	public static Button toggleLoadCubeButton;
	public static Button toggleEjectCubeButton;
	public static Button toggleArmReleaseButton;
	public static Button toggleArmCloseButton;
	public static Button toggleClimbButton;
	public static Button toggleClimberLowArmButton;
	public static Button toggleClimberTopArmButton;
	public static Button toggleClimberHookButton;
	
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
		
		Command ShootHigh = new ShootHigh();
		OI.toggleShootHighButton = new JoystickButton(OI.driveStick, kToggleShootHighButtonNumber);
		OI.toggleShootHighButton.toggleWhenPressed(ShootHigh);
		Command ShootLow = new ShootLow();
		OI.toggleShootLowButton = new JoystickButton(OI.driveStick, kToggleShootLowButtonNumber);
		OI.toggleShootLowButton.toggleWhenPressed(ShootLow);

		Command LoadCube = new LoadCube();
		OI.toggleLoadCubeButton = new JoystickButton(OI.driveStick, kToggleIntakeWheelsInButtonNumber);
		OI.toggleLoadCubeButton.toggleWhenPressed(LoadCube);
		Command EjectCube = new EjectCube();
		OI.toggleEjectCubeButton = new JoystickButton(OI.driveStick, kToggleIntakeWheelsOutButtonNumber);
		OI.toggleEjectCubeButton.toggleWhenPressed(EjectCube);
		
		Command OpenIntake = new OpenIntake();
		OI.toggleArmReleaseButton = new JoystickButton(OI.driveStick, kToggleIntakeArmReleaseButtonNumber);
		OI.toggleArmReleaseButton.toggleWhenPressed(OpenIntake);
		Command CloseIntake = new CloseIntake();
		OI.toggleArmCloseButton = new JoystickButton(OI.driveStick, kToggleIntakeArmCloseButtonNumber);
		OI.toggleArmCloseButton.toggleWhenPressed(CloseIntake);

		/**Command Climb = new Climb();
		OI.toggleClimbButton = new JoystickButton(OI.driveStick, kToggleClimbButtonNumber);
		OI.toggleClimbButton.toggleWhenPressed(Climb);
		*/
		Command DeployLowArm = new DeployClimberArm();
		OI.toggleClimberLowArmButton = new JoystickButton(OI.driveStick, kToggleClimberLowArmButtonNumber);
		OI.toggleClimberLowArmButton.toggleWhenPressed(DeployLowArm);
		Command DeployTopArm = new DeployClimberHook();
		OI.toggleClimberTopArmButton = new JoystickButton(OI.driveStick, kToggleClimberTopArmButtonNumber);
		OI.toggleClimberTopArmButton.toggleWhenPressed(DeployTopArm);
		Command RetractHook = new RetractClimberHook();
		OI.toggleClimberHookButton = new JoystickButton(OI.driveStick, kToggleClimberHookButtonNumber);
		OI.toggleClimberHookButton.toggleWhenPressed(RetractHook);

	}
	
}
