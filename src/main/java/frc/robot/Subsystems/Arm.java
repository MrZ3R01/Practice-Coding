package frc.robot.Subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;

public class Arm {
    private CANSparkMax arm = new CANSparkMax(13, MotorType.kBrushless);
    private RelativeEncoder arm_encoder = arm.getEncoder();
    private ArmFeedforward arm_Feedforward = new ArmFeedforward(0.01141, -0.02295, 0.0018501);
    private ProfiledPIDController arm_PID = new ProfiledPIDController(0, 0, 0, null);
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

    public void moveArmToPos(double goalPos){
        double currentPID = arm_PID.calculate(arm_encoder.getPosition(), goalPos);
        double currentFeedForward = arm_Feedforward.calculate(arm_PID.getSetpoint().position,arm_PID.getSetpoint().velocity);
        moveArm(MathUtil.clamp(currentPID + currentFeedForward,-12,12));
    }

}