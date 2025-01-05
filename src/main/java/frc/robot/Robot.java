// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Subsystems.Arm;
//import frc.robot.Subsystems.Drivetrain;
import frc.robot.Subsystems.Drivetrain;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private XboxController controller = new XboxController(0);
  private Drivetrain drivetrain = new Drivetrain();
  private CANSparkMax intake = new CANSparkMax(12, MotorType.kBrushless);
  private Arm arm = new Arm();
  private boolean manualArm = true;
  private double[] armPositions = {0,1,2};
  private int currentArmPos = 0;
  private double armPos = armPositions[currentArmPos];

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {

  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    
  }
  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {

  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
      drivetrain.arcadeDrive(controller.getLeftY(), controller.getRightX());

      if(controller.getAButton()){
        intake.setVoltage(4);
      }else if(controller.getBButton()){
        intake.setVoltage(-4);
      }else{
        intake.setVoltage(0);
      }
      if(controller.getXButtonPressed()){
        manualArm = !manualArm;
      }
      if(manualArm){
        if(controller.getLeftTriggerAxis()>0.1){
          arm.moveArm(controller.getLeftTriggerAxis()*4);
        }else if(controller.getRightTriggerAxis()>0.1){
          arm.moveArm(controller.getRightTriggerAxis()*-3);
        }else{
          arm.moveArm(0);
        }
      }else{
        armPos = armPositions[currentArmPos];
        arm.setArmPos(armPos);
      }

      if (controller.getLeftBumperPressed()) {
        currentArmPos--;
      }else if (controller.getRightBumperPressed()){
        currentArmPos++;
      }
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
