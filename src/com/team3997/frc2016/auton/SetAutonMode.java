package com.team3997.frc2016.auton;

public class SetAutonMode {
	private AutonBase m_auton_mode;
	private Thread m_thread = null;

	public void setAutoMode(AutonBase new_auton_mode) {
		m_auton_mode = new_auton_mode;
	}

	// Create a new thread and start the autonomous mode
	public void start() {
		if (m_thread == null) {
			m_thread = new Thread(new Runnable() {
				@Override
				public void run() {
					if (m_auton_mode != null) {
						m_auton_mode.run();
					}
				}
			});
			m_thread.start();
		}
	}

	public void stop() {
		if (m_auton_mode != null) {
			m_auton_mode.stop();
		}
		m_thread = null;
	}
}