package pilot.obss.com.autopilot.commandExecution.fgPlanePilot.algorithms;

import pilot.obss.com.autopilot.commandExecution.PIDProcess;
import pilot.obss.com.autopilot.sensor.PilotSensor;
import pilot.obss.com.autopilot.util.ActionProcess;
import pilot.obss.com.autopilot.util.PIDController;
import pilot.obss.com.autopilot.util.SingletonCollection;
import pilot.obss.com.autopilot.util.command.CommandObject;
import pilot.obss.com.autopilot.util.types.AlgorithmObject;

public class PIDRudderStabilization extends PIDProcess {
	PIDController controller = new PIDController();
	public PIDRudderStabilization(AlgorithmObject pidObject) {
		super(pidObject);
	}

	@Override
	public void execute(ActionProcess actionProcess, PilotSensor pilotSensor) {
		if (CommandObject.getInstance().getStblCommand().isStabilizeRudder()) {
//			controller.SetTunings(SingletonCollection.getPIDObject().getP(), SingletonCollection.getPIDObject().getI(), SingletonCollection.getPIDObject().getD());
			controller.SetTunings(2.7f, 0.08f, 0.005f);
			
//			System.out.println(craftInformation.getRudder());
			if (craftInformation.isRotateEnabled()) {
				actionProcess.setRudderDegree(actionProcess.getRudderStickValue().floatValue());
			} else {
				float realRudder = craftInformation.getRudder() + (craftInformation.getPitch()/3) + (craftInformation.getRoll()/3);
				float diff = actionProcess.getRudderConstantValue() - realRudder;
				float computeRudder = diff;
				if (diff < -180) {
					float x = actionProcess.getRudderConstantValue() + 180;
					float y = realRudder - 180;
					computeRudder = x - y;
				} else if (diff > 180) {
					float x = actionProcess.getRudderConstantValue() - 180;
					float y = realRudder + 180;
					computeRudder = x - y;
				}
				double compute = controller.compute(computeRudder, 1);
//				System.out.println( actionProcess.getRudderConstantValue() +"\t"+ computeRudder +"\t"+compute);
				actionProcess.setRudderDegree(new Double(compute).floatValue());
			}

		}
	}
	
	@Override
	public void resetI() {
		controller.reset_I();
	}

}
