package pilot.obss.com.autopilot.commandExecution.fgPlanePilot.algorithms;

import pilot.obss.com.autopilot.commandExecution.PIDProcess;
import pilot.obss.com.autopilot.sensor.PilotSensor;
import pilot.obss.com.autopilot.util.ActionProcess;
import pilot.obss.com.autopilot.util.LowPassFilter;
import pilot.obss.com.autopilot.util.PIDController5;
import pilot.obss.com.autopilot.util.SingletonCollection;
import pilot.obss.com.autopilot.util.command.CommandObject;
import pilot.obss.com.autopilot.util.types.AlgorithmObject;

public class PIDRudderStabilization extends PIDProcess {
	PIDController5 controller = new PIDController5();

	public PIDRudderStabilization(AlgorithmObject pidObject) {
		super(pidObject);
	}

	@Override
	public void execute(ActionProcess actionProcess, PilotSensor pilotSensor) {
//		if (CommandObject.getInstance().getStblCommand().isStabilizeRudder()) {
//			 controller.SetTunings(pidTunings.aileronPID.getP(), pidTunings.aileronPID.getI(), 0);
////			controller.SetTunings(0.1f, 0f, 0f);
//			if (orientationCommand.isRotateEnabled()) {
//				orientationCommand.setRudder(stickValues.getRudder());
//			} else {
//				float realRudder = craftInformation.getRudder() + (craftInformation.getPitch() / 3) + (craftInformation.getRoll() / 3);
//				float diff = orientationCommand.getRudderRate() - realRudder;
//				float computeRudder = diff;
//				if (diff < -180) {
//					float x = orientationCommand.getRudderRate() + 180;
//					float y = realRudder - 180;
//					computeRudder = x - y;
//				} else if (diff > 180) {
//					float x = orientationCommand.getRudderRate() - 180;
//					float y = realRudder + 180;
//					computeRudder = x - y;
//				}
//				double compute = controller.compute(computeRudder, 1);
////				orientationCommand.setRudder(new Double(compute).floatValue());
//				orientationCommand.setRudder(stickValues.getRudder());
//			}
//		}
	}

	@Override
	public void resetI() {
		controller.reset_I();
	}

}
