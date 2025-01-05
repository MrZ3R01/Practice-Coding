package frc.robot.Subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drivetrain {

  private CANSparkMax left = new CANSparkMax(15, MotorType.kBrushless);
  private CANSparkMax right = new CANSparkMax(14, MotorType.kBrushless);
  private DifferentialDrive driveTrain;

public Drivetrain() {
    left.setInverted(true);
    left.setSmartCurrentLimit(35);
    right.setSmartCurrentLimit(35);

    driveTrain = new DifferentialDrive(left, right);
}

public void arcadeDrive(double leftY, double rightX){
  driveTrain.arcadeDrive(leftY, rightX);
}

public void tankDrive(double leftY, double rightY){
  driveTrain.tankDrive(leftY, rightY);
}

}