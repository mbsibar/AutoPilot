package pilot.obss.test.fligthGearTest;

import java.io.IOException;

import pilot.obss.com.autopilot.brain.Navigator;
import pilot.obss.com.autopilot.brain.pilots.AutoPilotIntf;
import pilot.obss.com.autopilot.brain.pilots.PlanePilot;

public class FGFSDemo {
	private static final long serialVersionUID = 1L;
	private static AutoPilotIntf autoPilot = null;
	
	public FGFSDemo(String host, int port) throws IOException {
		autoPilot = new PlanePilot();
		new Thread(new Navigator(autoPilot)).start();
	}
/*
	public static void main(String args[]) throws Exception {
		UserInterface userInterface = new StandaloneDisplay();
		FGFSDemo gui = new FGFSDemo(HOST, PORT);
		autoPilot.setUserInterface(userInterface);
	}
*/
}
