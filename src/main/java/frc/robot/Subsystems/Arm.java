package frc.robot.Subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;

public class Arm {
    private CANSparkMax arm = new CANSparkMax(13, MotorType.kBrushless);
    private RelativeEncoder arm_encoder = arm.getEncoder();
    private PIDController arm_PID = new PIDController(0, 0, 0);

    public Arm(){
        arm.setSmartCurrentLimit(10);
        arm_encoder.setPositionConversionFactor((3.0 / 100) * Math.PI * 2);
    }

    public void resetArmEncoder(){
        arm_encoder.setPosition(0);
    }

    public void moveArm(double movement){
        arm.setVoltage(movement);
    }
    public void setArmPos(double armPos){
        arm.set(arm_PID.calculate(arm_encoder.getPosition(), armPos));
    }

}