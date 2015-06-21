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

public class PIDPlaneAileronStabilization extends PIDProcess {
	PIDController controller = new PIDController(0.5, 0.5, 5);

	public PIDPlaneAileronStabilization(AlgorithmObject pidObject) {
		super(pidObject);
		controller.Initialize();
		controller.SetMode(1);
		controller.SetTunings(0.3, 0.5, 5);
//		controller.SetOutputLimits(50, 130);
		controller.SetOutputLimits(-180, 180);
		SingletonCollection.setPidController(controller);
	}

	@Override
	public void execute(ActionProcess actionProcess, PilotSensor pilotSensor) {
		if (CommandObject.getInstance().getStblCommand().isStabilizeAileron()) {
			controller.SetTunings(SingletonCollection.getPIDObject().getP(), SingletonCollection.getPIDObject().getI(), SingletonCollection.getPIDObject().getD());
			controller.setSetPoint(Settings.craftType.getAutoPilot().actionProcess.getRollDegree());
			if (Settings.craftType.getAutoPilot().actionProcess.isStarted()) {
				pilotSensor.setAileron(new Double(controller.Compute(-CraftInformation.getInstance().getRoll())).floatValue());
				controller.SetMode(1);
			} else {
				controller.SetMode(0);
			}
		}
	}
}
