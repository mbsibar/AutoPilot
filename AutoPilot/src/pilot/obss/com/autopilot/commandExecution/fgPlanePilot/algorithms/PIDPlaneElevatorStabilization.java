package pilot.obss.com.autopilot.commandExecution.fgPlanePilot.algorithms;

import java.util.Date;

import pilot.obss.com.autopilot.commandExecution.PIDProcess;
import pilot.obss.com.autopilot.sensor.PilotSensor;
import pilot.obss.com.autopilot.util.ActionProcess;
import pilot.obss.com.autopilot.util.Converter;
import pilot.obss.com.autopilot.util.PIDController;
import pilot.obss.com.autopilot.util.SingletonCollection;
import pilot.obss.com.autopilot.util.command.CommandObject;
import pilot.obss.com.autopilot.util.types.AlgorithmObject;
import pilot.obss.com.autopilot.util.types.CraftInformation;
import pilot.obss.com.autopilot.util.types.PIDObject;

public class PIDPlaneElevatorStabilization extends PIDProcess {

    PIDController controller = new PIDController(0.01, 0.01, 0.01);

    public PIDPlaneElevatorStabilization(AlgorithmObject pidObject) {
        super(pidObject);
        controller.setSetpoint(0);
        controller.setOutputRange(-90, 90);
        SingletonCollection.setPidController(controller);
    }

    @Override
    public void execute(ActionProcess actionProcess, PilotSensor pilotSensor) {
        if (CommandObject.getInstance().getStblCommand().isStabilizeElevator() && !Float.isNaN(actionProcess.getPitchDegree())) {
           /* float error = actionProcess.getPitchDegree() - CraftInformation.getInstance().getPitch();
            pidObject.addIntegral(error * (float) actionProcess.getHertz());
            pidObject.setDerivative((error - pidObject.getPreError()) / (float) actionProcess.getHertz());
            float elevatorControl = pidObject.getTotal(error);
            pidObject.setPreError(error);
            pilotSensor.setElevator(elevatorControl);*/
            controller.setPID(SingletonCollection.getPIDObject().getP(), SingletonCollection.getPIDObject().getI(), SingletonCollection.getPIDObject().getD());
            controller.getInput(CraftInformation.getInstance().getPitch());
            pilotSensor.setElevator(new Double(controller.performPID()).floatValue());
        }
    }
}
