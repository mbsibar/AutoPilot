package pilot.obss.com.autopilot.commandExecution.fgPlanePilot.algorithms;

import pilot.obss.com.autopilot.commandExecution.PIDProcess;
import pilot.obss.com.autopilot.sensor.PilotSensor;
import pilot.obss.com.autopilot.util.ActionProcess;
import pilot.obss.com.autopilot.util.PIDController;
import pilot.obss.com.autopilot.util.SingletonCollection;
import pilot.obss.com.autopilot.util.command.CommandObject;
import pilot.obss.com.autopilot.util.constants.Settings;
import pilot.obss.com.autopilot.util.types.AlgorithmObject;
import pilot.obss.com.autopilot.util.types.CraftInformation;
import pilot.obss.com.autopilot.util.types.PIDObject;

public class PIDAileronStabilization extends PIDProcess {
	PIDController controller = new PIDController();

	public PIDAileronStabilization(AlgorithmObject pidObject) {
		super(pidObject);
	}

	@Override
	public void execute(ActionProcess actionProcess, PilotSensor pilotSensor) {
		if (CommandObject.getInstance().getStblCommand().isStabilizeAileron()) {
			controller.SetTunings(2.5f, 0f, SingletonCollection.getPIDObject().getD());
//			controller.SetTunings(3.53f, 0.018f, 0f);
			double compute = controller.compute(CraftInformation.getInstance().getRoll() - Settings.craftType.getAutoPilot().actionProcess.getAileronStickValue(), 1);
			actionProcess.setRollDegree(new Double(compute).floatValue());
			// actionProcess.setRollDegree(-actionProcess.getAileronStickValue());
		}
	}
	
	@Override
	public void resetI() {
		controller.reset_I();
	}
}
