package com.team3997.frc2016.auton.actions;

import com.team3997.frc2016.HardwareBase;
import com.team3997.frc2016.Params;
import com.team3997.frc2016.subsystems.*;

public abstract class Action {

    protected Drive drive = new Drive();
    //protected Intake intake = HardwareBase.kIntake;

    public abstract boolean isFinished();

    public abstract void update();

    public abstract void done();

    public abstract void start();
}
