package pilot.obss.com.autopilot.commandExecution.fgPlanePilot.algorithms;

import pilot.obss.com.autopilot.commandExecution.PIDProcess;
import pilot.obss.com.autopilot.sensor.PilotSensor;
import pilot.obss.com.autopilot.util.ActionProcess;
import pilot.obss.com.autopilot.util.types.AlgorithmObject;
import pilot.obss.com.autopilot.util.types.CraftInformation;
import pilot.obss.com.autopilot.util.types.PIDObject;

public class PIDVerticalSpeedStabilization extends PIDProcess {

	public PIDVerticalSpeedStabilization(AlgorithmObject pidObject) {
		super(pidObject);
	}

	@Override
	public void execute(ActionProcess actionProcess, PilotSensor pilotSensor) {
		Double fdmAltitudeFeet = CraftInformation.getInstance().getAltitudeFt();
		float vSpeedError = (float) (actionProcess.getAltitudeFt() - fdmAltitudeFeet);
		actionProcess.setVerticalSpeed(3f * vSpeedError);

		Float fdmVerticalSpeed = CraftInformation.getInstance().getVerticalSpeed();
		float error = actionProcess.getVerticalSpeed() - fdmVerticalSpeed;
		pidObject.addIntegral(error * (float) actionProcess.getHertz());
		pidObject.setDerivative((error - pidObject.getPreError()) / (float) actionProcess.getHertz());
		float output = pidObject.getTotal(error);
		pidObject.setPreError(error);
		if (output > 10) {
			output = 10;
		}
		if (output < -10) {
			output = -10;
		}
		/*actionProcess.setPitchDegree(output);*/
	}

	@Override
	public void resetI() {
		// TODO Auto-generated method stub
		
	}
}
