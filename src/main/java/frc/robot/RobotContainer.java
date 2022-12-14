// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import frc.robot.commands.control.ArmCommand;
import frc.robot.commands.control.DriveCommand;
import frc.robot.commands.control.ElevatorCommand;
import frc.robot.commands.autonomous.SequentialCommand;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  JoyUtil drivingController = new JoyUtil(0);
  JoyUtil bucketController = new JoyUtil(1);

  // The robot's subsystems and commands are defined here...
  DriveTrainSubsystem m_DriveTrainSubsystem = new DriveTrainSubsystem();
  DriveCommand m_DriveCommand = new DriveCommand(m_DriveTrainSubsystem, drivingController);

  ArmSubsystem m_ArmSubsystem = new ArmSubsystem();
  ArmCommand m_ArmCommand = new ArmCommand(m_ArmSubsystem, drivingController, bucketController);

  ElevatorSubsystem m_ElevatorSubsystem = new ElevatorSubsystem();
  ElevatorCommand m_ElevatorCommand = new ElevatorCommand(m_ElevatorSubsystem, drivingController, bucketController);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // schedule all commands
    m_DriveTrainSubsystem.setDefaultCommand(m_DriveCommand);
    m_ArmSubsystem.setDefaultCommand(m_ArmCommand);
    m_ElevatorSubsystem.setDefaultCommand(m_ElevatorCommand);

    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new SequentialCommand(m_ArmSubsystem, m_ElevatorSubsystem, m_DriveTrainSubsystem);
  }
}
