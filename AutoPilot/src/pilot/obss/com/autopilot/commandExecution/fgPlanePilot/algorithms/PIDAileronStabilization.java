package pilot.obss.com.autopilot.commandExecution.fgPlanePilot.algorithms;

import pilot.obss.com.autopilot.commandExecution.PIDProcess;
import pilot.obss.com.autopilot.sensor.PilotSensor;
import pilot.obss.com.autopilot.util.ActionProcess;
import pilot.obss.com.autopilot.util.SingletonCollection;
import pilot.obss.com.autopilot.util.command.CommandObject;
import pilot.obss.com.autopilot.util.types.AlgorithmObject;
import pilot.obss.com.autopilot.util.types.CraftInformation;
import pilot.obss.com.autopilot.util.types.PIDObject;

public class PIDAileronStabilization extends PIDProcess {

	public PIDAileronStabilization(AlgorithmObject pidObject) {
		super(pidObject);
	}

	@Override
	public void execute(ActionProcess actionProcess, PilotSensor pilotSensor) {
        if(CommandObject.getInstance().getStblCommand().isStabilizeAileron()) {
            float error = actionProcess.getRollDegree() - CraftInformation.getInstance().getRoll();
            pidObject.addIntegral(error * (float) actionProcess.getHertz());
            pidObject.setDerivative((error - pidObject.getPreError()) / (float) actionProcess.getHertz());
            float aileronControl = pidObject.getTotal(error);
            pidObject.setPreError(error);
           // pilotSensor.setAileron(aileronControl);
        }
	}
}
