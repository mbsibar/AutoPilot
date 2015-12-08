package pilot.obss.com.autopilot.util;

import pilot.obss.com.autopilot.brain.Navigator;
import pilot.obss.com.autopilot.util.command.OrientationCommand;
import pilot.obss.com.autopilot.util.types.CraftTypes;
import pilot.obss.com.autopilot.util.types.StickValues;

public class SingletonCollection {
	private static StickValues stickValues = new StickValues();
	private static OrientationCommand orientationCommand = new OrientationCommand();
	private static Navigator navigator = null;
	private static boolean started = false;
	public static final CraftTypes craftType = CraftTypes.QUADCOPTER_X;

	public static void setStarted(boolean bool) {
		started = bool;
	}

	public static boolean getStarted() {
		return started;
	}

	public static void setStickValues(StickValues newStickValues) {
		if (stickValues == null) {
			stickValues = newStickValues;
		}
	}

	public static StickValues getStickValues() {
		return stickValues;
	}

	public static void setOrientationCommand(OrientationCommand newOrientationCommand) {
		if (orientationCommand == null) {
			orientationCommand = newOrientationCommand;
		}
	}

	public static OrientationCommand getOrientationCommand() {
		return orientationCommand;
	}

	public static void setNavigator(Navigator newNavigator) {
		if (navigator == null) {
			navigator = newNavigator;
		}
	}

	public static Navigator getNavigator() {
		return navigator;
	}
}
