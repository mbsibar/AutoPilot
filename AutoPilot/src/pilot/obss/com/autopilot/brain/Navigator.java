package pilot.obss.com.autopilot.brain;

import java.util.concurrent.TimeUnit;

import pilot.obss.com.autopilot.brain.pilots.AutoPilotIntf;

public class Navigator implements Runnable {
	private AutoPilotIntf pilot;
	private long startTime = 0l;

	public Navigator(AutoPilotIntf pilot) {
		this.pilot = pilot;
	}

	@Override
	public void run() {
		try {
			while (true) {
				while (System.nanoTime() - startTime < 22000000f) {
					TimeUnit.NANOSECONDS.sleep(100);
				}
				pilot.cycle();
				startTime = System.nanoTime();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
