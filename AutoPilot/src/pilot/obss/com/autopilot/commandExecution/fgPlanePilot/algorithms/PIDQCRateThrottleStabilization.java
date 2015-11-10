package pilot.obss.com.autopilot.commandExecution.fgPlanePilot.algorithms;

import pilot.obss.com.autopilot.commandExecution.PIDProcess;
import pilot.obss.com.autopilot.sensor.PilotSensor;
import pilot.obss.com.autopilot.util.ActionProcess;
import pilot.obss.com.autopilot.util.PIDController;
import pilot.obss.com.autopilot.util.PIDValues;
import pilot.obss.com.autopilot.util.PinMap;
import pilot.obss.com.autopilot.util.types.AlgorithmObject;

public class PIDQCRateThrottleStabilization extends PIDProcess {
	PIDController controller = new PIDController(0.150f, 0.100f, 0.004f, 500, 20, 0.0025f);
	private Double prevAltitude = 0d;

	public PIDQCRateThrottleStabilization(AlgorithmObject pidObject) {
		super(pidObject);
		controller.imax(500);
	}

	
	int it = 1;
	double altitude;
	
	@Override
	public void execute(ActionProcess actionProcess, PilotSensor pilotSensor) {
		altitude = craftInformation.getAltitudeFt();
//		if (it >= 10) {
			// System.out.printf("%4.5f\t%4.5f\t%4.5f\t%4.5f\n", stickValues.getThrottle(), craftInformation.getAltitudeFt(), new Float((craftInformation.getAltitudeFt() - prevAltitude) * 100),
			// orientationCommand.getThrottle());
			// orientationCommand.setThrottle(stickValues.getThrottle());
			Float speed = new Float((altitude/it - prevAltitude) / 0.005);
			float rate_error = stickValues.getThrottle() - speed;
//			System.out.printf("%4.4f\t%4.4f\t%4.4f\n",speed, altitude/it , prevAltitude);
			prevAltitude = altitude/it;

			controller.set_input_filter_d(rate_error);
			controller.set_desired_rate(rate_error);

			controller.kP(pidTunings.aileronPID.getP());
			controller.kI(pidTunings.aileronPID.getI());
			controller.kD(0f);

			// get p value
			float p = controller.get_p();
			// get i term
			float i = controller.get_i();
			// update i term as long as we haven't breached the limits or the I
			// term
			// will certainly reduce
			if ((i > 0 && rate_error < 0) || (i < 0 && rate_error > 0)) {
				i = controller.get_i();
			}
			// get d term
			float d = controller.get_d();

			// PIDValues.putValue(PinMap.THURSTOUT, 1500 + (p + i + d));
			 System.out.printf("%4.4f\t%4.4f\t%4.4f\t%4.4f\n",speed, stickValues.getThrottleRaw(), altitude, craftInformation.getGravity());
			PIDValues.putValue(PinMap.THURSTOUT, (1500 + p + i + d));
//			it = 0;
//		}
//		it++;
	}

	@Override
	public void resetI() {
		// TODO Auto-generated method stub

	}
}
