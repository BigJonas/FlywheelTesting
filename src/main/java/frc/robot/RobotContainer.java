// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private CANSparkMax flywheelMotor1 = new CANSparkMax(6, MotorType.kBrushless);
  private CANSparkMax flywheelMotor2 = new CANSparkMax(7, MotorType.kBrushless);

  private double flywheelVoltage = 0.0;

  private Logitech stick = new Logitech(0);
  private JoystickButton aButton = new JoystickButton(stick, Logitech.Ports.A);
  private JoystickButton bButton = new JoystickButton(stick, Logitech.Ports.B);
  private JoystickButton xButton = new JoystickButton(stick, Logitech.Ports.X);
  private JoystickButton yButton = new JoystickButton(stick, Logitech.Ports.Y);
  private JoystickButton leftBumper = new JoystickButton(stick, Logitech.Ports.LEFT_BUMPER);
  private JoystickButton rightBumper = new JoystickButton(stick, Logitech.Ports.RIGHT_BUMPER); 

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    /*
    * Controls
    * A: Increase voltage by 0.1
    * B: Decrease voltage by 0.1
    * X: Increase voltage by 1.0
    * Y: Decrease voltage by 1.0
    * LB: Reset voltage to 0
    * RB: Run flywheel at set voltage
    */
    aButton.whenPressed(new InstantCommand(() -> {

      flywheelVoltage += 0.1;
      SmartDashboard.putNumber("Flywheel Voltage", flywheelVoltage);

    }));
    bButton.whenPressed(new InstantCommand(() -> {

      flywheelVoltage -= 0.1;
      SmartDashboard.putNumber("Flywheel Voltage", flywheelVoltage);

    }));
    xButton.whenPressed(new InstantCommand(() -> {

      flywheelVoltage += 1.0;
      SmartDashboard.putNumber("Flywheel Voltage", flywheelVoltage);

    }));
    yButton.whenPressed(new InstantCommand(() -> {

      flywheelVoltage -= 1.0;
      SmartDashboard.putNumber("Flywheel Voltage", flywheelVoltage);

    }));
    leftBumper.whenPressed(new InstantCommand(() -> {

      flywheelVoltage = 0.0;
      flywheelMotor1.setVoltage(Math.max(flywheelVoltage, 0.0));
      flywheelMotor2.setVoltage(Math.max(flywheelVoltage, 0.0));
      SmartDashboard.putNumber("Flywheel Voltage", flywheelVoltage);

    }));
    rightBumper.whenHeld(new RunCommand(() -> {

      flywheelMotor1.setVoltage(Math.max(flywheelVoltage, 0.0));
      flywheelMotor2.setVoltage(Math.max(flywheelVoltage, 0.0));
      SmartDashboard.putNumber("Flywheel Voltage", flywheelVoltage);

    }));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
