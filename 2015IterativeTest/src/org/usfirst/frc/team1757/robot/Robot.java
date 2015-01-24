package org.usfirst.frc.team1757.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Compressor;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	RobotDrive drive;
	Joystick gamepad;
	Compressor compressor;
	Solenoid solenoid;
	CameraServer server;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
	Jaguar jag;
	int counter = 0;
    public void robotInit() {
    	gamepad = new Joystick(0);
    	drive = new RobotDrive(0,1);
    	compressor = new Compressor();
    	solenoid = new Solenoid(0);
    	compressor.start();
    	server = CameraServer.getInstance();
        server.setQuality(50);
        //the camera name (ex "cam0") can be found through the roborio web interface
        server.startAutomaticCapture("cam0");
    }
    
    /**
     * This function is run once each time the robot enters autonomous mode
     */
    public void autonomousInit() {
    	
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    
    }
    
    /**
     * This function is called once each time the robot enters tele-operated mode
     */
    public void teleopInit(){
    	
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	while(isEnabled() && isOperatorControl()){
    		drive.tankDrive(gamepad.getRawAxis(1), gamepad.getRawAxis(5));
    		if(gamepad.getRawButton(1)){
    			solenoid.set(true);
    			SmartDashboard.putString("DB/String 5", Boolean.toString(gamepad.getRawButton(1)));
    		}else{
    			solenoid.set(false);
    		}
    		SmartDashboard.putString("DB/String 0", Boolean.toString(solenoid.get()));
    		//System.out.println("Cycle"); <--- Somehow doesn't work
    	}
    }
      /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	LiveWindow.run();
    }
    
}
