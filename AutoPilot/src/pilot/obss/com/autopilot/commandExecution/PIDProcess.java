package pilot.obss.com.autopilot.commandExecution;

import pilot.obss.com.autopilot.sensor.PilotSensor;
import pilot.obss.com.autopilot.util.ActionProcess;
import pilot.obss.com.autopilot.util.types.AlgorithmObject;
import pilot.obss.com.autopilot.util.types.PIDObject;

public abstract class PIDProcess {
	protected AlgorithmObject pidObject;

	public PIDProcess(AlgorithmObject algorithmObject) {
		this.pidObject = algorithmObject;
	}

    public abstract void execute(ActionProcess actionProcess, PilotSensor pilotSensor);

}
