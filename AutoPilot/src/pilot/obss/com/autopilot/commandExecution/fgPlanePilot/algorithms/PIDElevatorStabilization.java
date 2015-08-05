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

public class PIDElevatorStabilization extends PIDProcess {

	PIDController controller = new PIDController(1, 1, 1);
	public PIDElevatorStabilization(AlgorithmObject pidObject) {
		super(pidObject);
		controller.setOutputLimits(-2000, 2000);
		controller.setSampleTime(1);
		SingletonCollection.setPidController(controller);
	}

	@Override
	public void execute(ActionProcess actionProcess, PilotSensor pilotSensor) {
		if (CommandObject.getInstance().getStblCommand().isStabilizeElevator()) {
			CraftInformation.getInstance().setResetMotorLastSpeeds();
			controller.SetTunings(SingletonCollection.getPIDObject().getP(), SingletonCollection.getPIDObject().getI(), SingletonCollection.getPIDObject().getD());
//			controller.SetTunings(2.1f, 0.5, 0.04f);
			controller.setSetPoint(Settings.craftType.getAutoPilot().actionProcess.getPitchStickValue());
//			System.out.println(Settings.craftType.getAutoPilot().actionProcess.getPitchStickValue());
			float d = new Double(controller.compute(CraftInformation.getInstance().getPitch())).floatValue();
			Settings.craftType.getAutoPilot().actionProcess.setPitchDegree(d);
		}
	}

}
