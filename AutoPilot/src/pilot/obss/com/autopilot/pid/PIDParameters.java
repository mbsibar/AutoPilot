package pilot.obss.com.autopilot.pid;


public class PIDParameters {
	public static final PIDController planeAileronPid = new PIDController(0f, 0f, 0f, 0, 0, 0);
	public static final PIDController planeElevatorPid = new PIDController(0f, 0f, 0f, 0, 0, 0);

    public static final PIDController dwAileronPid = new PIDController(0f, 0f, 0f, 0, 0, 0);
    public static final PIDController dwElevatorPid = new PIDController(0f, 0f, 0f, 0, 0, 0);

    public static final PIDController planeRudderPid = new PIDController(0f, 0f, 0f, 0, 0, 0);
	public static final PIDController planeThrottlePid = new PIDController(0f, 0f, 0f, 0, 0, 0);
	public static final PIDController planeVerticalSpeedPid = new PIDController(0f, 0f, 0f, 0, 0, 0);

    public static final PIDController qcAileronPid = new PIDController(0f, 0f, 0f, 0, 0, 0);
    public static final PIDController qcElevatorPid = new PIDController(0f, 0f, 0f, 0, 0, 0);
    public static final PIDController qcElevatorRatePid = new PIDController(0f, 0f, 0f, 0, 0, 0);
    public static final PIDController qcAileronRatePid = new PIDController(0f, 0f, 0f, 0, 0, 0);
    public static final PIDController qcRudderPid = new PIDController(10, 0, 0, 300, 20, 0.0025f);
    public static final PIDController qcThrottlePid = new PIDController(0f, 0f, 0f, 0, 0, 0);
    public static final PIDController qcVerticalSpeedPid = new PIDController( 0f, 0f, 0f, 0, 0, 0);
    public static final PIDController qcBaroAltitudePid = new PIDController(3f, 0f, 0f, 20, 20, 0.0025f);
    public static final PIDController qcRateBaroAltitudePid = new PIDController(7.0f, 9.0f, 0f, 500, 20, 0.0025f);
    public static final PIDController qcRangeAltitudePid = new PIDController(3f, 0f, 0f, 20, 20, 0.0025f);
    public static final PIDController qcRateRangeAltitudePid = new PIDController(7.0f, 9.0f, 0f, 500, 20, 0.0025f);
    
}
