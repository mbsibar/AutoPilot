package pilot.obss.com.autopilot.commandExecution.fgPlanePilot.algorithms;

import pilot.obss.com.autopilot.commandExecution.PIDProcess;
import pilot.obss.com.autopilot.sensor.PilotSensor;
import pilot.obss.com.autopilot.util.ActionProcess;
import pilot.obss.com.autopilot.util.PIDController;
import pilot.obss.com.autopilot.util.SingletonCollection;
import pilot.obss.com.autopilot.util.command.CommandObject;
import pilot.obss.com.autopilot.util.types.AlgorithmObject;

public class PIDElevatorStabilization extends PIDProcess {
	PIDController controller = new PIDController();

	public PIDElevatorStabilization(AlgorithmObject pidObject) {
		super(pidObject);
	}

	int it = 0;

	@Override
	public void execute(ActionProcess actionProcess, PilotSensor pilotSensor) {
		if (CommandObject.getInstance().getStblCommand().isStabilizeElevator()) {
			controller.SetTunings(2.5f, 0f, SingletonCollection.getPIDObject().getD());
//			 controller.SetTunings(3.53f, 0.018f, 0f);
			double compute = controller.compute(craftInformation.getPitch() - actionProcess.getPitchStickValue(), 1);
//			System.out.println( craftInformation.getPitch() +"\t"+ actionProcess.getPitchStickValue()+"\t" + compute);
			actionProcess.setPitchDegree(new Double(compute).floatValue());
			// actionProcess.setPitchDegree(-actionProcess.getPitchStickValue());
		}
	}

	@Override
	public void resetI() {
		controller.reset_I();
	}

}
