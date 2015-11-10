package pilot.obss.com.autopilot.util;

import pilot.obss.com.autopilot.util.constants.ScaleFactorConstants;
import pilot.obss.com.autopilot.util.constants.Settings;
import pilot.obss.com.autopilot.util.types.CraftTypes;

public enum ScaleFactor {
	QC_AILERON_STICK(1100, 1947, -50, 50, CraftTypes.QUADCOPTER_X, ScaleFactorConstants.AILERON),
	QC_ELEVATOR_STICK(1083, 1922, -50, 50, CraftTypes.QUADCOPTER_X, ScaleFactorConstants.ELEVATOR),
	QC_THROTTLE_STICK(1040, 1937, -10, 10, CraftTypes.QUADCOPTER_X, ScaleFactorConstants.THROTTLE),
	QC_RUDDER_STICK(1080, 1920, -100, 100, CraftTypes.QUADCOPTER_X, ScaleFactorConstants.RUDDER),
	QC_MODE_STICK(1124, 1960, -100, 100, CraftTypes.QUADCOPTER_X, ScaleFactorConstants.MODE),
	QC_P_STICK(1050, 1890, 0.5f, 1.5f, CraftTypes.QUADCOPTER_X, ScaleFactorConstants.P),
	QC_I_STICK(1050, 1890, 0, 80f, CraftTypes.QUADCOPTER_X, ScaleFactorConstants.I),
	QC_D_STICK(1050, 1890, 0, 5, CraftTypes.QUADCOPTER_X, ScaleFactorConstants.D);

	public int minPWMValue;
	public int maxPWMValue;
	public float minValue;
	public float maxValue;
	public CraftTypes craftType;
	public ScaleFactorConstants type;

	ScaleFactor(int minPWMValue, int maxPWMValue, float minValue, float maxValue, CraftTypes craftType, ScaleFactorConstants type) {
		this.minPWMValue = minPWMValue;
		this.maxPWMValue = maxPWMValue;
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.craftType = craftType;
		this.type = type;
	}

	public static ScaleFactor getFactor(ScaleFactorConstants type) {
		for(ScaleFactor factor : ScaleFactor.values()){
			if(factor.craftType.equals(Settings.craftType) && factor.type.equals(type))
				return factor;
		}
		return null;
	}
}
