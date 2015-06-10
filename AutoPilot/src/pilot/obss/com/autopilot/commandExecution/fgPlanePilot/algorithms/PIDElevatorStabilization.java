package pilot.obss.com.autopilot.commandExecution.fgPlanePilot.algorithms;

import java.util.Date;

import pilot.obss.com.autopilot.commandExecution.PIDProcess;
import pilot.obss.com.autopilot.sensor.PilotSensor;
import pilot.obss.com.autopilot.util.ActionProcess;
import pilot.obss.com.autopilot.util.PIDController;
import pilot.obss.com.autopilot.util.SingletonCollection;
import pilot.obss.com.autopilot.util.command.CommandObject;
import pilot.obss.com.autopilot.util.types.AlgorithmObject;
import pilot.obss.com.autopilot.util.types.CraftInformation;
import pilot.obss.com.autopilot.util.types.PIDObject;

public class PIDElevatorStabilization extends PIDProcess {

    PIDController controller = new PIDController(0.5, 0.1, 0.01);

    public PIDElevatorStabilization(AlgorithmObject pidObject) {
        super(pidObject);
//        controller.setSetpoint(0);
       /* controller.setInputRange(-500, 500);*/
//        controller.setOutputRange(-500, 500);
        SingletonCollection.setPidController(controller);
    }

    @Override
    public void execute(ActionProcess actionProcess, PilotSensor pilotSensor) {
      /*   float error = actionProcess.getPitchDegree() - CraftInformation.getInstance().getPitch();
        pidObject.addIntegral(error * (float) actionProcess.getHertz());
		pidObject.setDerivative((error - pidObject.getPreError()) / (float) actionProcess.getHertz());
		double pitchControl = pidObject.getTotal(error);
		pidObject.setPreError(error);*/


     /*   controller.getInput(CraftInformation.getInstance().getPitch());
		pilotSensor.setElevator(new Double(controller.performPID()).floatValue());*/

       /* if (CommandObject.getInstance().getStblCommand().isStabilizeElevator()) {
            if (new Date().getTime() - prevDate.getTime() <= 100) {
                float input = getInput(0, CraftInformation.getInstance().getPitch(), new Date().getTime() - prevDate.getTime());
                pilotSensor.setElevator(input);
            }
            prevDate = new Date();
        }*/
    }

    public float integrator = 0f;
    public float differencesMean = 0f;
    public float previousDifference = 0f;
    public float smoothingStrength = 0.5f;
    public float kp = 0.15f;
    public float ki = 0.1f;
    public float kd = 0.1f;
    public Date prevDate = new Date();

    public float getInput(float targetAngle, float currentAngle, long dt) {
        float rawDifference = targetAngle - currentAngle;
        float difference = rawDifference;

        // Now, the PID computation can be done.
        float input = 0.0f;

        // Proportional part.
        input += difference * kp;

        // Integral part.
        integrator += difference * ki * dt;
        input += integrator;

        differencesMean = differencesMean * smoothingStrength + difference * (1 - smoothingStrength);
        float derivative = (differencesMean - previousDifference) / dt;
        previousDifference = differencesMean;
        input += derivative * kd;
        if (input > 1900)
            input = 1900;
        else if (input < 1100)
            input = 1100;
        return input;

    }
}
