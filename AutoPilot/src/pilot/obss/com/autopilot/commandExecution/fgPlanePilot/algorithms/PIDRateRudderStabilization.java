package pilot.obss.com.autopilot.commandExecution.fgPlanePilot.algorithms;

import pilot.obss.com.autopilot.commandExecution.PIDProcess;
import pilot.obss.com.autopilot.sensor.PilotSensor;
import pilot.obss.com.autopilot.util.ActionProcess;
import pilot.obss.com.autopilot.util.LowPassFilter;
import pilot.obss.com.autopilot.util.PIDController3;
import pilot.obss.com.autopilot.util.command.CommandObject;
import pilot.obss.com.autopilot.util.types.AlgorithmObject;

public class PIDRateRudderStabilization extends PIDProcess {
	PIDController3 controller = new PIDController3();
//	private LowPassFilter lpf = new LowPassFilter(400, 100);
	
	public PIDRateRudderStabilization(AlgorithmObject pidObject) {
		super(pidObject);
	}

	double rudderRate = 0d;
	
	@Override
	public void execute(ActionProcess actionProcess, PilotSensor pilotSensor) {
		if (CommandObject.getInstance().getStblCommand().isStabilizeRudder()) {
			float newRudderRate = (float) craftInformation.getRudderRate();
//			rudderRate = (rudderRate * 0.8f) + (newRudderRate * 0.2f);// 57.14286f
//			newRudderRate = lpf.apply(newRudderRate);
			controller.SetTunings(3f, 0.01f, 0f, 300);
//			controller.SetTunings(pidTunings.aileronPID.getP(), pidTunings.aileronPID.getI(), 0f);
			double compute = controller.compute(new Double(newRudderRate - stickValues.getRudder()).floatValue(), 1); //orientationCommand.getRudder()
			pilotSensor.setRudder(new Double(-compute).floatValue());
//			pilotSensor.setRudder(new Double(0).floatValue());
		}
	}

	@Override
	public void resetI() {
		controller.reset_I();
	}

}
