package pilot.obss.com.autopilot.commandExecution.fgPlanePilot;

import pilot.obss.com.autopilot.util.types.AlgorithmObject;
import pilot.obss.com.autopilot.util.types.PIDObject;
import pilot.obss.com.autopilot.util.types.RateObject;

public class ArduinoPIDConstants {
	public static final AlgorithmObject planeAileronPid = new PIDObject("aileron", 0.002f, 0.002f, 0f, 50, -180f, 180f);
	public static final AlgorithmObject planeElevatorPid = new PIDObject("elevator", 0.002f, 0.002f, 0f, 50, -180f, 180f);

    public static final AlgorithmObject dwAileronPid = new PIDObject("aileron", 1f, 0.01f, 0f, 50, -180f, 180f);
    public static final AlgorithmObject dwElevatorPid = new PIDObject("elevator", 1f, 0.01f, 0f, 50, -180f, 180f);

    public static final AlgorithmObject planeRudderPid = new PIDObject("rudder", 0.5f, 0.01f, 0.01f, 10, 0f, 1024f);
	public static final AlgorithmObject planeThrottlePid = new PIDObject("throttle", 0.5f, 0.1f, 0.001f, 500, 0f, 90f);
	public static final AlgorithmObject planeVerticalSpeedPid = new PIDObject("verticalspeed", 0.5f, 0.01f, 0.01f, 100, -500f, 500f);

    public static final AlgorithmObject qcAileronPid = new PIDObject("aileron", 250f, 100f, 0.005f, 200, -180f, 180f);
    public static final AlgorithmObject qcElevatorPid = new PIDObject("elevator", 250f, 100f, 0.005f, 200, 1100f, 1900f);
    public static final AlgorithmObject qcElevatorRatePid = new PIDObject("elevatorRate", 250f, 100f, 0.005f, 200, 1100f, 1900f);
    public static final AlgorithmObject qcRudderPid = new PIDObject("rudder", 0.4f, 0.01f, 0.01f, 10, 0f, 1024f);
    public static final AlgorithmObject qcThrottlePid = new PIDObject("throttle", 0.4f, 0.1f, 0.001f, 500, 0f, 180f);
    public static final AlgorithmObject qcVerticalSpeedPid = new PIDObject("verticalspeed", 0.4f, 0.01f, 0.01f, 100, -500f, 500f);
}
