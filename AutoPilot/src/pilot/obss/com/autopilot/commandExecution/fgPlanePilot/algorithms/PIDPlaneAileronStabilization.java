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
//	PIDController controller = new PIDController;

	public PIDPlaneAileronStabilization(AlgorithmObject pidObject) {
		super(pidObject);
//		controller.setOutputLimits(-90, 90);
//		controller.setSampleTime(10);
//		SingletonCollection.setPidController(controller);
	}

	@Override
	public void execute(ActionProcess actionProcess, PilotSensor pilotSensor) {
//		if (CommandObject.getInstance().getStblCommand().isStabilizeAileron()) {
//			controller.SetTunings(SingletonCollection.getPIDObject().getP(), SingletonCollection.getPIDObject().getI(), SingletonCollection.getPIDObject().getD());
//			controller.setSetPoint(Settings.craftType.getAutoPilot().actionProcess.getRollDegree());
//			float d = new Double(controller.compute(-CraftInformation.getInstance().getRoll())).floatValue();
//			pilotSensor.setAileron(d + 90);
//		}
	}

	@Override
	public void resetI() {
		// TODO Auto-generated method stub
		
	}
}
