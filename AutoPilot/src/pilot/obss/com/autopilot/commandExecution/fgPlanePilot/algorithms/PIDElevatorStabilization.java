package pilot.obss.com.autopilot.commandExecution.fgPlanePilot.algorithms;

import pilot.obss.com.autopilot.commandExecution.PIDProcess;
import pilot.obss.com.autopilot.sensor.PilotSensor;
import pilot.obss.com.autopilot.util.ActionProcess;
import pilot.obss.com.autopilot.util.PIDController;
import pilot.obss.com.autopilot.util.command.CommandObject;
import pilot.obss.com.autopilot.util.types.AlgorithmObject;

public class PIDElevatorStabilization extends PIDProcess {
	PIDController controller = new PIDController(1, 1, 1, 1, 1, 1);

	public PIDElevatorStabilization(AlgorithmObject pidObject) {
		super(pidObject);
	}

	long lastTime = 0;
	float information = 0;

	@Override
	public void execute(ActionProcess actionProcess, PilotSensor pilotSensor) {
		if (CommandObject.getInstance().getStblCommand().isStabilizeElevator() && (System.nanoTime() - lastTime) / 1000000f > 4) {
			float error = -1f* 4.5f * (craftInformation.getPitch() - stickValues.getElevator());
			if (error > 5000) {
				error = 5000;
			} else if (error < -5000) {
				error = -5000;
			}
			orientationCommand.setElevator(error);
//			orientationCommand.setElevator(stickValues.getElevator());
		}
	}

	@Override
	public void resetI() {
		controller.reset_I();
	}

}
