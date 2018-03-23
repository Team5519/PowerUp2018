/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5519.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5519.robot.commands.Autonomous.AutoDeliverFarLeft;
import org.usfirst.frc.team5519.robot.commands.Autonomous.AutoDeliverFarRight;
import org.usfirst.frc.team5519.robot.commands.Autonomous.AutoDeliverLeft;
import org.usfirst.frc.team5519.robot.commands.Autonomous.AutoDeliverLeftMiddle;
import org.usfirst.frc.team5519.robot.commands.Autonomous.AutoDeliverMiddle;
import org.usfirst.frc.team5519.robot.commands.Autonomous.AutoDeliverRight;
import org.usfirst.frc.team5519.robot.commands.Autonomous.AutoDriveStraightDistance;
import org.usfirst.frc.team5519.robot.commands.Autonomous.AutoTurnLeft;
import org.usfirst.frc.team5519.robot.commands.Autonomous.AutoTurnRight;
import org.usfirst.frc.team5519.robot.subsystems.Climber;
import org.usfirst.frc.team5519.robot.subsystems.DriveBase5519;
import org.usfirst.frc.team5519.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team5519.robot.subsystems.Intake;
import org.usfirst.frc.team5519.robot.subsystems.IntakeandShootLow;
import org.usfirst.frc.team5519.robot.subsystems.Shooter;

import com.kauailabs.navx.frc.AHRS;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static final ExampleSubsystem kExampleSubsystem
			= new ExampleSubsystem();
	public static OI m_oi;
    public static Shooter shooter;
    public static Intake intake;
    public static IntakeandShootLow intakeandShootLow;
    public static Climber climber;
    public static DriveBase5519 driveBase;
    
    public static AHRS ahrs;

	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();
	
	public String autoGameData;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		autoGameData = "";
		RobotMap.init ();
        // GyroSamples
        try {
            /* Communicate w/navX-MXP via the MXP SPI Bus.                                     */
            /* Alternatively:  I2C.Port.kMXP, SerialPort.Port.kMXP or SerialPort.Port.kUSB     */
            /* See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface/ for details. */
            ahrs = new AHRS(SPI.Port.kMXP); 
        } catch (RuntimeException ex ) {
            DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
        }
		shooter = new Shooter();
        intake = new Intake();
        intakeandShootLow = new IntakeandShootLow();
        climber = new Climber();
        driveBase = new DriveBase5519();
		m_oi = new OI();
		CameraServer.getInstance().startAutomaticCapture("Intake View",0);
		CameraServer.getInstance().startAutomaticCapture("Climber View",1);
		
		//m_chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		//SmartDashboard.putData("Auto mode", m_chooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		//m_autonomousCommand = m_chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */
		
		autoGameData = DriverStation.getInstance().getGameSpecificMessage();
		boolean isSwitchLocationLeft = true;
		if (autoGameData.length() > 0) {
			if (autoGameData.charAt(0) == 'R') {
				isSwitchLocationLeft = false;
			}
		}

		String autoSelected = SmartDashboard.getString("Auto Selector","Default");
        m_oi.messageDriverStation("AUTONOMOUS COMMAND = " + autoSelected);
		switch(autoSelected) { 
			// Drive straight auto commands
			case "Left":
				m_oi.messageDriverStation("AUTONOMOUS COMMAND :: Starting position left!");
				if (isSwitchLocationLeft) {
					m_autonomousCommand = new AutoDeliverLeft();
				} else {
					m_autonomousCommand = new AutoDeliverFarRight();
				}
				break;
				
			case "Middle":
				m_oi.messageDriverStation("AUTONOMOUS COMMAND :: Starting position middle!");
				if (isSwitchLocationLeft) {
					m_autonomousCommand = new AutoDeliverLeftMiddle();
				} else {
					m_autonomousCommand = new AutoDeliverMiddle();
				}
			case "Right":
				m_oi.messageDriverStation("AUTONOMOUS COMMAND :: Starting position left!");
				if (isSwitchLocationLeft) {
					m_autonomousCommand = new AutoDeliverRight();
				} else {
					m_autonomousCommand = new AutoDeliverFarLeft();
				}
				break;

			case "DS1":
				m_oi.messageDriverStation("AUTONOMOUS COMMAND :: Driving 1 Meters!");
				m_autonomousCommand = new AutoDriveStraightDistance(75);
				break;
			case "DS2":
				m_oi.messageDriverStation("AUTONOMOUS COMMAND :: Driving 2 Meters!");
				m_autonomousCommand = new AutoDriveStraightDistance(100);
				break;
			case "DS3":
				m_oi.messageDriverStation("AUTONOMOUS COMMAND :: Driving 3 Meters!");
				m_autonomousCommand = new AutoDriveStraightDistance(150);
				break;
					
			// Default auto command
			case "Auto Default": 
			default:
				m_autonomousCommand = new AutoDriveStraightDistance(150);
				break; 
		}
		
		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
