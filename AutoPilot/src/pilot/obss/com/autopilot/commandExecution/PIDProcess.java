package pilot.obss.com.autopilot.commandExecution;

import pilot.obss.com.autopilot.sensor.PilotSensor;
import pilot.obss.com.autopilot.util.ActionProcess;
import pilot.obss.com.autopilot.util.PIDTunings;
import pilot.obss.com.autopilot.util.SingletonCollection;
import pilot.obss.com.autopilot.util.StickValues;
import pilot.obss.com.autopilot.util.constants.Settings;
import pilot.obss.com.autopilot.util.types.AlgorithmObject;
import pilot.obss.com.autopilot.util.types.CraftInformation;

public abstract class PIDProcess {
	protected AlgorithmObject pidObject;
	protected ActionProcess actionProcess = Settings.craftType.getAutoPilot().actionProcess;
	protected CraftInformation craftInformation = CraftInformation.getInstance();
	protected PIDTunings pidTunings = SingletonCollection.getPidTunings();
	protected StickValues stickValues = SingletonCollection.getStickValues();
	protected OrientationCommand orientationCommand = SingletonCollection.getOrientationCommand();

	public PIDProcess(AlgorithmObject algorithmObject) {
		this.pidObject = algorithmObject;
	}

    public abstract void execute(ActionProcess actionProcess, PilotSensor pilotSensor);
    
    public abstract void resetI();

}
