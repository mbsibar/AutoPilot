package pilot.obss.com.autopilot.commandExecution.fgPlanePilot.algorithms;

import pilot.obss.com.autopilot.commandExecution.PIDProcess;
import pilot.obss.com.autopilot.sensor.PilotSensor;
import pilot.obss.com.autopilot.util.ActionProcess;
import pilot.obss.com.autopilot.util.command.CommandObject;
import pilot.obss.com.autopilot.util.types.AlgorithmObject;
import pilot.obss.com.autopilot.util.types.CraftInformation;
import pilot.obss.com.autopilot.util.types.PIDObject;

public class PIDRudderStabilization extends PIDProcess {

	public PIDRudderStabilization(AlgorithmObject pidObject) {
		super(pidObject);
	}

	@Override
	public void execute(ActionProcess actionProcess, PilotSensor pilotSensor) {

        if(CommandObject.getInstance().getStblCommand().isStabilizeRudder()) {
            Float fdmRudder = CraftInformation.getInstance().getHeading();
            float error = actionProcess.getHeadingDegree() - fdmRudder;
            pidObject.addIntegral(error * (float) actionProcess.getHertz());
            pidObject.setDerivative((error - pidObject.getPreError()) / (float) actionProcess.getHertz());
            float heading_ctrl = pidObject.getTotal(error);
            pidObject.setPreError(error);
            if (error > 15) {
                actionProcess.setRollDegree(calculateRoll(error));
                pilotSensor.setRudder(0);
            } else if (error < -15) {
                actionProcess.setRollDegree(calculateRoll(error));
                pilotSensor.setRudder(0);
            } else {
                actionProcess.setRollDegree(calculateRoll(error));
                pilotSensor.setRudder(heading_ctrl);
            }

        }
	}

	private Float calculateRoll(float error) {
		float angle = error/3 ;
		if (angle > 15)
			return 15f;
		else if (angle < -15)
			return -15f;
		return angle;
	}
}
