package pilot.obss.com.autopilot.brain;

import pilot.obss.com.autopilot.util.ActionProcess;
import pilot.obss.com.autopilot.util.types.CLIMBTYPE;
import pilot.obss.com.autopilot.util.types.StabilizationType;
import pilot.obss.com.autopilot.util.types.TURN_TYPES;
import pilot.obss.com.autopilot.util.types.WayPoint;

public interface AutoPilot {
	ActionProcess actionProcess = new ActionProcess();

	void setStabilizationMode(StabilizationType stabilizationType);

	void cycle();

	void calculateActionProcess(TURN_TYPES turnType, float turnDegree);

	void calculateActionProcess(CLIMBTYPE up, float turnDegree);

	void setTargetLocation(WayPoint targetLocation);

}
