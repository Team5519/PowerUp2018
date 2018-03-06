package org.usfirst.frc.team5519.robot.subsystems;

import org.usfirst.frc.team5519.robot.Robot;
import org.usfirst.frc.team5519.robot.RobotMap;
import org.usfirst.frc.team5519.robot.commands.DriveWithJoystick;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveBase5519 extends Subsystem {

	private static boolean DIRECTION_FORWARD = true;
	private static boolean DIRECTION_BACKWARD = false;
	
	private boolean driveDirection;
	
	private double kP = -0.03;
	private AHRS gyro;
	
	
	RobotDrive myDrive;
	
	private Encoder encoder;
    private static final double kPulsesPerRotation = 100;	// Set via DIP
    private static final double kWheelDiameter = 0.10;		// i.e. 10 cm
    private static final double kDistancePerPulse = (3.14 * kWheelDiameter) / kPulsesPerRotation; // in meters
    //private static final double kDistancePerPulse = 0.20/100; // in meters -- For Testing

	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public DriveBase5519() {
		driveDirection = DIRECTION_FORWARD;
		gyro = Robot.ahrs;
		myDrive = new RobotDrive(RobotMap.driveMotorLeft, RobotMap.driveMotorRight);
		//myDrive.setInvertedMotor(MotorType.kFrontLeft, true);
		encoder = RobotMap.EncoderAMT10V;
		encoder.setDistancePerPulse(kDistancePerPulse);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveWithJoystick());
    }
    
	 public void resetDriveSensors() {
		 gyro.reset();
		 encoder.reset();
	 }
	    
	 public double getDistanceTraveled() {
	   	return encoder.getDistance()*-1.0;
	 }
	 
	 public double getGyroAngle() {
		 return gyro.getAngle() * -1.0;				// Invert Gyro Heading!
	 }

	 public void changeDriveDirection() {
		 driveDirection = !driveDirection;
	 }

	public void joystickDrive(GenericHID stick) {
		
 		double moveValue = 1 * stick.getY();
 		if(driveDirection == DIRECTION_BACKWARD)	{
 			moveValue = 1 * moveValue;
 		}
		// Correct left / right by NOT inverting Y-Axis values.
		double rotateValue = 1 * stick.getX();
		myDrive.arcadeDrive(moveValue, rotateValue, true);
		//myDrive.arcadeDrive(stick);
	}
	
	/**
	 * Drive using direct values. 
	 * Code stolen from RobotDrive
	 */
	 public void autoDrive(double moveValue, double targetAngle) {
		myDrive.arcadeDrive(moveValue, targetAngle);
	 }

	 public void autoDriveStraight(double moveValue) {
		double angle = -1 * gyro.getAngle() * kP;
		autoDrive(moveValue, angle);
	 }
	 
	 public void stopDead() {
		autoDrive(0,0);
	 }

	    public void dumpSensorData (String strCommand) {
	    	if (!Robot.m_oi.doMessageDriverStation) {
	    		return;
	    	}
	    	// AMT10V Encoder Data
	    	SmartDashboard.putNumber(   "AHRS/AMT_Distance",         encoder.getDistance());
	    	
	        /* Display 6-axis Processed Angle Data                                      */
	        SmartDashboard.putBoolean(  "AHRS/IMU_Connected",        gyro.isConnected());
	        SmartDashboard.putBoolean(  "AHRS/IMU_IsCalibrating",    gyro.isCalibrating());
	        SmartDashboard.putNumber(   "AHRS/IMU_Yaw",              gyro.getYaw());
	        SmartDashboard.putNumber(   "AHRS/IMU_Pitch",            gyro.getPitch());
	        SmartDashboard.putNumber(   "AHRS/IMU_Roll",             gyro.getRoll());
	        
	        /* Display tilt-corrected, Magnetometer-based heading (requires             */
	        /* magnetometer calibration to be useful)                                   */
	        
	        SmartDashboard.putNumber(   "AHRS/IMU_CompassHeading",   gyro.getCompassHeading());
	        
	        /* Display 9-axis Heading (requires magnetometer calibration to be useful)  */
	        //SmartDashboard.putNumber(   "AHRS/IMU_FusedHeading",     gyro.getFusedHeading());

	        /* These functions are compatible w/the WPI Gyro Class, providing a simple  */
	        /* path for upgrading from the Kit-of-Parts gyro to the navx-MXP            */
	        
	        SmartDashboard.putNumber(   "AHRS/GSN_GetAngle",         gyro.getAngle());
	        
	        SmartDashboard.putNumber(   "AHRS/IMU_TotalYaw",         gyro.getAngle());
	        SmartDashboard.putNumber(   "AHRS/IMU_YawRateDPS",       gyro.getRate());

	        /* Display Processed Acceleration Data (Linear Acceleration, Motion Detect) */
	        
	        //SmartDashboard.putNumber(   "AHRS/IMU_Accel_X",          gyro.getWorldLinearAccelX());
	        //SmartDashboard.putNumber(   "AHRS/IMU_Accel_Y",          gyro.getWorldLinearAccelY());
	        //SmartDashboard.putBoolean(  "AHRS/IMU_IsMoving",         gyro.isMoving());
	        //SmartDashboard.putBoolean(  "AHRS/IMU_IsRotating",       gyro.isRotating());

	        /* Display estimates of velocity/displacement.  Note that these values are  */
	        /* not expected to be accurate enough for estimating robot position on a    */
	        /* FIRST FRC Robotics Field, due to accelerometer noise and the compounding */
	        /* of these errors due to single (velocity) integration and especially      */
	        /* double (displacement) integration.                                       */
	        
	        //SmartDashboard.putNumber(   "AHRS/Velocity_X",           gyro.getVelocityX());
	        //SmartDashboard.putNumber(   "AHRS/Velocity_Y",           gyro.getVelocityY());
	        //SmartDashboard.putNumber(   "AHRS/Displacement_X",       gyro.getDisplacementX());
	        //SmartDashboard.putNumber(   "AHRS/Displacement_Y",       gyro.getDisplacementY());
	        
	        /* Display Raw Gyro/Accelerometer/Magnetometer Values                       */
	        /* NOTE:  These values are not normally necessary, but are made available   */
	        /* for advanced users.  Before using this data, please consider whether     */
	        /* the processed data (see above) will suit your needs.                     */
	        
	        //SmartDashboard.putNumber(   "AHRS/RawGyro_X",            gyro.getRawGyroX());
	        //SmartDashboard.putNumber(   "AHRS/RawGyro_Y",            gyro.getRawGyroY());
	        //SmartDashboard.putNumber(   "AHRS/RawGyro_Z",            gyro.getRawGyroZ());
	        //SmartDashboard.putNumber(   "AHRS/RawAccel_X",           gyro.getRawAccelX());
	        //SmartDashboard.putNumber(   "AHRS/RawAccel_Y",           gyro.getRawAccelY());
	        //SmartDashboard.putNumber(   "AHRS/RawAccel_Z",           gyro.getRawAccelZ());
	        //SmartDashboard.putNumber(   "AHRS/RawMag_X",             gyro.getRawMagX());
	        //SmartDashboard.putNumber(   "AHRS/RawMag_Y",             gyro.getRawMagY());
	        //SmartDashboard.putNumber(   "AHRS/RawMag_Z",             gyro.getRawMagZ());
	        //SmartDashboard.putNumber(   "AHRS/IMU_Temp_C",           gyro.getTempC());
	        
	        /* Omnimount Yaw Axis Information                                           */
	        /* For more info, see http://navx-mxp.kauailabs.com/installation/omnimount  */
	        //AHRS.BoardYawAxis yaw_axis = gyro.getBoardYawAxis();
	        //SmartDashboard.putString(   "AHRS/YawAxisDirection",     yaw_axis.up ? "Up" : "Down" );
	        //SmartDashboard.putNumber(   "AHRS/YawAxis",              yaw_axis.board_axis.getValue() );
	        
	        /* Sensor Board Information                                                 */
	        //SmartDashboard.putString(   "AHRS/FirmwareVersion",      gyro.getFirmwareVersion());
	        
	        /* Quaternion Data                                                          */
	        /* Quaternions are fascinating, and are the most compact representation of  */
	        /* orientation data.  All of the Yaw, Pitch and Roll Values can be derived  */
	        /* from the Quaternions.  If interested in motion processing, knowledge of  */
	        /* Quaternions is highly recommended.                                       */
	        //SmartDashboard.putNumber(   "AHRS/QuaternionW",          gyro.getQuaternionW());
	        //SmartDashboard.putNumber(   "AHRS/QuaternionX",          gyro.getQuaternionX());
	        //SmartDashboard.putNumber(   "AHRS/QuaternionY",          gyro.getQuaternionY());
	        //SmartDashboard.putNumber(   "AHRS/QuaternionZ",          gyro.getQuaternionZ());
	        
	        /* Connectivity Debugging Support                                           */
	        //SmartDashboard.putNumber(   "AHRS/IMU_Byte_Count",       gyro.getByteCount());
	        //SmartDashboard.putNumber(   "AHRS/IMU_Update_Count",     gyro.getUpdateCount());
	    }


}

