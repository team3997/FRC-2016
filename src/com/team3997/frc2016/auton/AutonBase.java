package com.team3997.frc2016.auton;

import com.team3997.frc2016.components.Dashboard;


public abstract class AutonBase {
	
	public boolean m_active = false;
	
	protected abstract void routine() throws AutoModeEndedException;
    public abstract void prestart();
    
    public void run() {
        m_active = true;
        try {
            routine();
        } catch (AutoModeEndedException e) {
            System.out.println("Auto mode done, ended early");
            return;
        }
        
        
    }
    
    public void stop() {
        m_active = false;
    }
    
    public boolean isActive() {
        return m_active;
    }
    
    public boolean isActiveWithThrow() throws AutoModeEndedException {
        if (!isActive()) {
            throw new AutoModeEndedException();
        }
        return isActive();
    }
    
}
