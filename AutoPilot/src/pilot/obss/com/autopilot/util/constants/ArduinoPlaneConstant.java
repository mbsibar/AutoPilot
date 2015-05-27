package pilot.obss.com.autopilot.util.constants;

public class ArduinoPlaneConstant implements PlaneConstants {
	private static final float ROLL_MULTIPLIER = -0.008f;
	private static final float PITCH_MULTIPLIER = 0.017f;
	private static final float RUDDER_MULTIPLIER = 0.008f;
	private static final float TURN_PITCH_MULTIPLIER = 8f;
	private static final float AIR_SPEED_MULTIPLIER = 0.01f;
	
	public float getRollMultiplier() {
		return ROLL_MULTIPLIER;
	}
	
	public float getPitchMultiplier() {
		return PITCH_MULTIPLIER;
	}
	
	public float getRudderMultiplier() {
		return RUDDER_MULTIPLIER;
	}

	@Override
	public float getTurnPitchMultiplier() {
		return TURN_PITCH_MULTIPLIER;
	}

	@Override
	public float getAirSpeedMultiplier() {
		return AIR_SPEED_MULTIPLIER;
	}
	
}
