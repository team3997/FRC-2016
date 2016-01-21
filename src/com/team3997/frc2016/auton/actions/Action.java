package com.team3997.frc2016.auton.actions;

import com.team3997.frc2016.Robot;
import com.team3997.frc2016.subsystems.Climber;
import com.team3997.frc2016.subsystems.Drive;
import com.team3997.frc2016.subsystems.Intake;
import com.team3997.frc2016.subsystems.Shooter;

public abstract	class Action {
	protected Drive drive = Robot.drive;
	protected Shooter shooter = Robot.shooter;
	protected Intake intake = Robot.intake;
	protected Climber climber = Robot.climber;

    public abstract boolean isFinished();

    public abstract void update();

    public abstract void done();

    public abstract void start();
}
