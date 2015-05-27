package pilot.obss.com.autopilot.commandExecution.fgPlanePilot.algorithms;

import pilot.obss.com.autopilot.commandExecution.PIDProcess;
import pilot.obss.com.autopilot.sensor.PilotSensor;
import pilot.obss.com.autopilot.util.ActionProcess;
import pilot.obss.com.autopilot.util.Converter;
import pilot.obss.com.autopilot.util.PIDController;
import pilot.obss.com.autopilot.util.SingletonCollection;
import pilot.obss.com.autopilot.util.command.CommandObject;
import pilot.obss.com.autopilot.util.types.AlgorithmObject;
import pilot.obss.com.autopilot.util.types.CraftInformation;

public class PIDPlaneAileronStabilization extends PIDProcess {

    PIDController controller = new PIDController(0.01, 0.01, 0.01);
	public PIDPlaneAileronStabilization(AlgorithmObject pidObject) {
		super(pidObject);
        controller.setSetpoint(0);
        controller.setOutputRange(-90, 90);
        SingletonCollection.setPidController(controller);
	}

	@Override
	public void execute(ActionProcess actionProcess, PilotSensor pilotSensor) {
        if(CommandObject.getInstance().getStblCommand().isStabilizeAileron()) {
        /*    float error = actionProcess.getRollDegree() - CraftInformation.getInstance().getRoll();
            pidObject.addIntegral(error * (float) actionProcess.getHertz());
            pidObject.setDerivative((error - pidObject.getPreError()) / (float) actionProcess.getHertz());
            float aileronControl = pidObject.getTotal(error);
            pidObject.setPreError(error);
            pilotSensor.setAileron(aileronControl);*/

            controller.setPID(SingletonCollection.getPIDObject().getP(), SingletonCollection.getPIDObject().getI(), SingletonCollection.getPIDObject().getD());
            controller.getInput(CraftInformation.getInstance().getRoll());
            pilotSensor.setAileron(new Double(controller.performPID()).floatValue());
        }
	}
}
