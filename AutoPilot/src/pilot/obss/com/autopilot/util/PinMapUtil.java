package pilot.obss.com.autopilot.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pilot.obss.com.autopilot.util.constants.ScaleFactorConstants;
import pilot.obss.com.autopilot.util.types.PinMap;
import pilot.obss.com.autopilot.util.types.PinMode;

public class PinMapUtil {
	private static Map<PinMode, List<PinMap>> fastPinMap = new HashMap<PinMode, List<PinMap>>();
	private static Map<ScaleFactorConstants, PinMap> fastPinMapType = new HashMap<ScaleFactorConstants, PinMap>();

	public static PinMap getPinMap(ScaleFactorConstants type) {
		if (fastPinMapType.get(type) == null) {
			for (PinMap pinMap : PinMap.values()) {
				if (pinMap.getType().equals(type)) {
					fastPinMapType.put(type, pinMap);
				}
			}
		}
		return fastPinMapType.get(type);
	}
	
	public static List<PinMap> getPinMapList(PinMode pinMode) {
		if (fastPinMap.get(pinMode) == null) {
			List<PinMap> tempPinMapList = new ArrayList<>();
			for (PinMap pinMap : PinMap.values()) {
				if (pinMap.getPinMode().equals(pinMode)) {
					tempPinMapList.add(pinMap);
				}
			}
			fastPinMap.put(pinMode, tempPinMapList);
		}

		return fastPinMap.get(pinMode);
	}

}
