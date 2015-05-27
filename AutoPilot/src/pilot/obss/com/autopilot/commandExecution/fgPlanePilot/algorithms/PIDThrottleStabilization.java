package pilot.obss.com.autopilot.commandExecution.fgPlanePilot.algorithms;

import pilot.obss.com.autopilot.commandExecution.PIDProcess;
import pilot.obss.com.autopilot.sensor.PilotSensor;
import pilot.obss.com.autopilot.util.ActionProcess;
import pilot.obss.com.autopilot.util.command.CommandObject;
import pilot.obss.com.autopilot.util.types.AlgorithmObject;
import pilot.obss.com.autopilot.util.types.CraftInformation;
import pilot.obss.com.autopilot.util.types.PIDObject;

public class PIDThrottleStabilization extends PIDProcess {

	public PIDThrottleStabilization(AlgorithmObject pidObject) {
		super(pidObject);
	}

	@Override
	public void execute(ActionProcess actionProcess, PilotSensor pilotSensor) {
        if(CommandObject.getInstance().getStblCommand().isStabilizeAirSpeed()) {
            Float fdmAirSpeed = CraftInformation.getInstance().getAirSpeed();
            float error = actionProcess.getAirSpeedDegree() - fdmAirSpeed;
            pidObject.addIntegral(error * (float) actionProcess.getHertz());
            pidObject.setDerivative((error - pidObject.getPreError()) / (float) actionProcess.getHertz());
            float output = pidObject.getTotal(error);
            pidObject.setPreError(error);
            pilotSensor.setThrottle(output);
        }
	}
}
