package pilot.obss.com.autopilot.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class OutputValues {
	private static Map<PinMap, Float> outputValues = new HashMap<PinMap, Float>();

	public OutputValues() {
		resetMotorSpeeds();
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
//		OutputValues.putValue(PinMap.MOTOR1, SingletonCollection.getStickValues().getThrottleRaw());
//		OutputValues.putValue(PinMap.MOTOR2, SingletonCollection.getStickValues().getThrottleRaw());
//		OutputValues.putValue(PinMap.MOTOR3, SingletonCollection.getStickValues().getThrottleRaw());
//		OutputValues.putValue(PinMap.MOTOR4, SingletonCollection.getStickValues().getThrottleRaw());
	}

}
