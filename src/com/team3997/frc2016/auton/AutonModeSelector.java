package com.team3997.frc2016.auton;

import java.util.ArrayList;

import com.team3997.frc2016.auton.modes.DoNothingAutoMode;

public class AutonModeSelector {
    private static AutonModeSelector instance = null;
    private ArrayList<AutonMode> autoModes = new ArrayList<AutonMode>();
    int selectedIndex = 0;
    public static AutonModeSelector getInstance() {
        if (instance == null) {
            instance = new AutonModeSelector();
        }
        return instance;
    }

    public void registerAutonomous(AutonMode auto) {
        autoModes.add(auto);
    }

    public AutonModeSelector() {
        registerAutonomous(new DoNothingAutoMode()); //order of auton modes
    }
    
    public AutonMode getAutoMode() {
        return autoModes.get(selectedIndex); //Find a way to change the selecteed index
    }

    public ArrayList<String> getAutoModeList() {
        ArrayList<String> list = new ArrayList<String>();
        for (AutonMode autoMode : autoModes) {
            list.add(autoMode.getClass().getSimpleName());
        }
        return list;
    }
}