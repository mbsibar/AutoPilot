package pilot.obss.com.autopilot.commandExecution.fgPlanePilot.algorithms;

import pilot.obss.com.autopilot.commandExecution.PIDProcess;
import pilot.obss.com.autopilot.sensor.PilotSensor;
import pilot.obss.com.autopilot.util.ActionProcess;
import pilot.obss.com.autopilot.util.PIDController;
import pilot.obss.com.autopilot.util.SingletonCollection;
import pilot.obss.com.autopilot.util.command.CommandObject;
import pilot.obss.com.autopilot.util.constants.Settings;
import pilot.obss.com.autopilot.util.types.AlgorithmObject;
import pilot.obss.com.autopilot.util.types.CraftInformation;

public class PIDPlaneElevatorStabilization extends PIDProcess {

	PIDController controller = new PIDController(0.5, 0.5, 5);

    public PIDPlaneElevatorStabilization(AlgorithmObject pidObject) {
        super(pidObject);
        controller.Initialize();
		controller.SetMode(1);
		controller.SetTunings(0.3, 0.5, 5);
		controller.SetOutputLimits(0, 270);
		SingletonCollection.setPidController(controller);
    }

    @Override
    public void execute(ActionProcess actionProcess, PilotSensor pilotSensor) {
        if (CommandObject.getInstance().getStblCommand().isStabilizeElevator() && !Float.isNaN(actionProcess.getPitchDegree())) {
        	double d = controller.Compute(CraftInformation.getInstance().getPitch());
			controller.SetTunings(5+SingletonCollection.getPIDObject().getP()*2, 5+SingletonCollection.getPIDObject().getI() * 2, SingletonCollection.getPIDObject().getD());
			controller.setSetPoint(Settings.craftType.getAutoPilot().actionProcess.getPitchDegree());
			if (Settings.craftType.getAutoPilot().actionProcess.isStarted()) {
				pilotSensor.setElevator(new Double(d).floatValue());
				controller.SetMode(1);
			} else {
				controller.SetMode(0);
			}
        }
    }
}
