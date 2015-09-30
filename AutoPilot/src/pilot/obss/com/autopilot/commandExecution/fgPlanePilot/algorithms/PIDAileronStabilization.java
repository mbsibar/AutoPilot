package pilot.obss.com.autopilot.commandExecution.fgPlanePilot.algorithms;

import pilot.obss.com.autopilot.commandExecution.PIDProcess;
import pilot.obss.com.autopilot.sensor.PilotSensor;
import pilot.obss.com.autopilot.util.ActionProcess;
import pilot.obss.com.autopilot.util.PIDController;
import pilot.obss.com.autopilot.util.command.CommandObject;
import pilot.obss.com.autopilot.util.types.AlgorithmObject;

public class PIDAileronStabilization extends PIDProcess {
	PIDController controller = new PIDController(1, 1, 1, 1, 1, 1);

	public PIDAileronStabilization(AlgorithmObject pidObject) {
		super(pidObject);
	}

	long lastTime = 0;
	float information = 0;

	@Override
	public void execute(ActionProcess actionProcess, PilotSensor pilotSensor) {
		if (CommandObject.getInstance().getStblCommand().isStabilizeAileron() && (System.nanoTime() - lastTime) / 1000000f > 4) {
			lastTime = System.nanoTime();
			float error = -1 * 4.5f * (craftInformation.getRoll() - stickValues.getAileron());
			if (error > 5000) {
				error = 5000;
			} else if (error < -5000) {
				error = -5000;
			}
			orientationCommand.setAileron(error);
//			orientationCommand.setAileron(stickValues.getAileron());
		}
	}

	@Override
	public void resetI() {
		controller.reset_I();
	}
}
