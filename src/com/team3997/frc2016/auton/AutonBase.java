package com.team3997.frc2016.auton;

import com.team3997.frc2016.auton.actions.Action;


public abstract class AutonBase {
	protected double m_update_rate = 1.0 / 50.0;
	public boolean m_active = false;
	
	protected abstract void routine() throws AutonModeEndedException;
    public abstract void prestart();
    
    public void run() {
        m_active = true;
        try {
            routine();
        } catch (AutonModeEndedException e) {
            System.out.println("Auton mode done, ended early");
            return;
        }
        System.out.println("Auto mode done");
        
    }
    
    public void stop() {
        m_active = false;
    }
    
    public boolean isActive() {
        return m_active;
    }

    public boolean isActiveWithThrow() throws AutonModeEndedException {
        if (!isActive()) {
            throw new AutonModeEndedException();
        }
        return isActive();
    }

    public void runAction(Action action) throws AutonModeEndedException {
        isActiveWithThrow();
        action.start();
        while (isActiveWithThrow() && !action.isFinished()) {
            action.update();
            try {
                Thread.sleep((long) (m_update_rate * 1000.0));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        action.done();
    }
    
}
