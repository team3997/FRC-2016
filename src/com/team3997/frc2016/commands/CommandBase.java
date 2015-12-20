package com.team3997.frc2016.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This class overrides the default abstract methods
 * from Command. For readability and code simplicity.
 */
public class CommandBase extends Command {

    @Override protected void initialize() { }
    @Override protected void execute() { }
    @Override protected boolean isFinished() { return false; }
    @Override protected void end() { }
    @Override protected void interrupted() { }
}