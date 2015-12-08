package pilot.obss.com.autopilot.util.types;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class PIDOutputValues {
	private static Map<PinMap, Float> outputValues = new HashMap<PinMap, Float>();
	static {
		outputValues.put(PinMap.THURSTOUT, 1100f);
	}

	public PIDOutputValues() {
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

	}

}
