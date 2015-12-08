package pilot.obss.com.autopilot.pid;

import pilot.obss.com.autopilot.sensor.PilotSensor;
import pilot.obss.com.autopilot.util.SingletonCollection;
import pilot.obss.com.autopilot.util.command.CommandObject;
import pilot.obss.com.autopilot.util.command.OrientationCommand;
import pilot.obss.com.autopilot.util.types.CraftInformation;
import pilot.obss.com.autopilot.util.types.StickValues;

public abstract class PIDProcess {
	protected StickValues stickValues = SingletonCollection.getStickValues();
	protected PIDController pidController;
	protected OrientationCommand orientationCommand = SingletonCollection.getOrientationCommand();
	protected CommandObject commandObject = CommandObject.getInstance();
	protected CraftInformation craftInformation = CraftInformation.getInstance();

	public PIDProcess(PIDController pidController) {
		this.pidController = pidController;
	}

	public abstract void execute(PilotSensor pilotSensor);

	public void resetI(){
		pidController.reset_I();
	}

}
