package pilot.obss.com.autopilot.commandExecution.fgPlanePilot.algorithms;

import pilot.obss.com.autopilot.commandExecution.PIDProcess;
import pilot.obss.com.autopilot.sensor.PilotSensor;
import pilot.obss.com.autopilot.util.ActionProcess;
import pilot.obss.com.autopilot.util.PIDController;
import pilot.obss.com.autopilot.util.command.CommandObject;
import pilot.obss.com.autopilot.util.constants.Constants;
import pilot.obss.com.autopilot.util.types.AlgorithmObject;

public class PIDRateElevatorStabilization extends PIDProcess {
	PIDController controller = new PIDController(0.150f,0.100f,0.004f,2000,20,0.0025f);
//	private LowPassFilter lpf = new LowPassFilter(800, 600);

	public PIDRateElevatorStabilization(AlgorithmObject pidObject) {
		super(pidObject);
	}

	float pitchRate;
	long lastTime;
	
	@Override
	public void execute(ActionProcess actionProcess, PilotSensor pilotSensor) {
		
		if (CommandObject.getInstance().getStblCommand().isStabilizeElevator()) {// && (System.nanoTime() - lastTime) / 1000000f > 5
			float p, i, d; // used to capture pid values for logging
			float rate_error; // simply target_rate - current_rate

//		    controller.kP(pidTunings.aileronPID.getP());
//		    controller.kI(pidTunings.aileronPID.getI());
//			controller.kD(pidTunings.elevatorPID.getD());
//			controller.kI(1014f);
		    controller.kP(0.7f);
			controller.kI(1f);
			controller.kD(0f);
		    controller.set_dt(Constants.loopTimer);
		    controller.imax(50);
			
			float pitchRate = new Double(-craftInformation.getPitchRate()).floatValue();

			rate_error = orientationCommand.getElevator() - pitchRate*57.14f;
//			controller.set_input_filter_all(rate_error);
			controller.set_input_filter_d(rate_error);
			controller.set_desired_rate(rate_error);

			// get p value
			p = controller.get_p();

			// get i term
			i = controller.get_i();

			// update i term as long as we haven't breached the limits or the I
			// term will certainly reduce
			if ((i > 0 && rate_error < 0) || (i < 0 && rate_error > 0)) {
				i = controller.get_i();
			}

			// get d term
			d = controller.get_d();

			// constrain output and return
			if ((p + i + d) < -Constants.AC_ATTITUDE_RATE_RP_CONTROLLER_OUT_MAX)
				pilotSensor.setElevator(-Constants.AC_ATTITUDE_RATE_RP_CONTROLLER_OUT_MAX);
			else if ((p + i + d) > Constants.AC_ATTITUDE_RATE_RP_CONTROLLER_OUT_MAX)
				pilotSensor.setElevator(Constants.AC_ATTITUDE_RATE_RP_CONTROLLER_OUT_MAX);
			else
				pilotSensor.setElevator(p + i + d);
			
			lastTime = System.nanoTime();
		}
	}

	@Override
	public void resetI() {
		controller.reset_I();
	}

}