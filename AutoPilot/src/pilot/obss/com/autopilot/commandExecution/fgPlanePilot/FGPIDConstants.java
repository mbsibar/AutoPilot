package pilot.obss.com.autopilot.commandExecution.fgPlanePilot;

import pilot.obss.com.autopilot.util.types.PIDObject;

public class FGPIDConstants {
	public static final PIDObject aileronPid = new PIDObject("aileron", 0.015f, 0.001f, 0f, 50, -1f, 1f);
	public static final PIDObject elevatorPid = new PIDObject("elevator", 0.005f, 0.0005f, 0.001f, 50, -10f, 10f);
	public static final PIDObject rudderPid = new PIDObject("rudder", 0.005f, 0.01f, 0.01f, 10, -1f, 1f);
	public static final PIDObject throttlePid = new PIDObject("throttle", 0.035f, 0.001f, 0.001f, 500, 0f, 1f);
	public static final PIDObject verticalSpeedPid = new PIDObject("verticalspeed", 0.01f, 0.001f, 0.01f, 100, -500f, 500f);
}
