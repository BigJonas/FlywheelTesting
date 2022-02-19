// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FlywheelSub extends SubsystemBase {

  private CANSparkMax flywheelMotor1;
  private CANSparkMax flywheelMotor2;
  
  private double flywheelVoltage = 0.0;
  private double encoderVelocity = 0.0;

  /** Creates a new flywheelSub. */
  public FlywheelSub() {

    flywheelMotor1 = new CANSparkMax(6, MotorType.kBrushless);
    flywheelMotor2 = new CANSparkMax(7, MotorType.kBrushless);

  }

  public void setVoltage(double v) {

    flywheelMotor1.setVoltage(v);
    flywheelMotor2.setVoltage(v);

    flywheelVoltage = v;

  }

  public double getEncoderPosition() {

    return flywheelMotor1.getEncoder().getPosition();

  }

  public double getEncoderVelocity() {

    return encoderVelocity;

  }

  private void updateEncoderVelocity() {

    // TODO: get hadyen to help, dont really have an idea on how to do this

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    updateEncoderVelocity();

    SmartDashboard.putNumber("Flywheel Voltage", flywheelVoltage);
    SmartDashboard.putNumber("Flywheel Encoder Position", getEncoderPosition());
    SmartDashboard.putNumber("Flywheel Encoder Velocity", getEncoderVelocity());
  }
}
