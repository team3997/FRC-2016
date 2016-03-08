package com.team3997.frc2016.auton.actions;

import com.team3997.frc2016.Hardware;
import com.team3997.frc2016.subsystems.Drive;
import com.team3997.frc2016.subsystems.Hanger;
import com.team3997.frc2016.subsystems.Intake;
import com.team3997.frc2016.subsystems.Shooter;

public abstract	class Action {
	protected Drive drive = Hardware.kDrive;
	protected Shooter shooter = Hardware.kShooter;
	protected Intake intake = Hardware.kIntake;

    public abstract boolean isFinished();

    public abstract void update();

    public abstract void done();

    public abstract void start();
}
