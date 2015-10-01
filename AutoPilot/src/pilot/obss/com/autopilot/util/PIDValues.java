package pilot.obss.com.autopilot.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class PIDValues {
	private static Map<PinMap, Float> outputValues = new HashMap<PinMap, Float>();

	public PIDValues() {
	}

	public static void putValue(PinMap pin, float value) {
		outputValues.put(pin, value);
	}

	public static float getValue(PinMap pin) {
		return outputValues.get(pin);
	}

	public static Set<Entry<PinMap, Float>> getAllList() {
		return outputValues.entrySet();
	}

	public static void resetMotorSpeeds() {
		PIDValues.putValue(PinMap.MOTOR1, 0);
		PIDValues.putValue(PinMap.MOTOR2, 0);
		PIDValues.putValue(PinMap.MOTOR3, 0);
		PIDValues.putValue(PinMap.MOTOR4, 0);
	}

}
