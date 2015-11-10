package pilot.obss.com.autopilot.commandExecution.fgPlanePilot;

import java.util.ArrayList;
import java.util.List;

import pilot.obss.com.autopilot.commandExecution.PIDProcess;
import pilot.obss.com.autopilot.commandExecution.fgPlanePilot.algorithms.PIDQCAltitudeStabilization;
import pilot.obss.com.autopilot.commandExecution.fgPlanePilot.algorithms.PIDPlaneAileronStabilization;
import pilot.obss.com.autopilot.commandExecution.fgPlanePilot.algorithms.PIDPlaneElevatorStabilization;
import pilot.obss.com.autopilot.commandExecution.fgPlanePilot.algorithms.PIDQCRateThrottleStabilization;
import pilot.obss.com.autopilot.sensor.PilotSensor;
import pilot.obss.com.autopilot.util.ActionProcess;
import pilot.obss.com.autopilot.util.types.CraftTypes;

public enum PIDProcessList {
    PL_AILERON(new PIDPlaneAileronStabilization(ArduinoPIDConstants.planeAileronPid), true, CraftTypes.PLANE),
    PL_ELEVATOR(new PIDPlaneElevatorStabilization(ArduinoPIDConstants.planeElevatorPid), true, CraftTypes.PLANE),
    //PL_RUDDER(new PIDRudderStabilization(ArduinoPIDConstants.planeRudderPid), true, CraftTypes.PLANE),
    //PL_THROTTLE(new PIDThrottleStabilization(ArduinoPIDConstants.planeThrottlePid), true, CraftTypes.PLANE),
    //PL_ALTITUDE(new PIDVerticalSpeedStabilization(ArduinoPIDConstants.planeVerticalSpeedPid), true, CraftTypes.PLANE),

    DW_AILERON(new PIDPlaneAileronStabilization(ArduinoPIDConstants.dwAileronPid), true, CraftTypes.DELTAWING),
    DW_ELEVATOR(new PIDPlaneElevatorStabilization(ArduinoPIDConstants.dwElevatorPid), true, CraftTypes.DELTAWING),

//    QC_ALTITIUDE(new PIDQCAltitudeStabilization(ArduinoPIDConstants.qcAltitudePid), true, CraftTypes.QUADCOPTER_X),
    QC_RATE_ALTITIUDE(new PIDQCRateThrottleStabilization(ArduinoPIDConstants.qcRateThrottlePid), true, CraftTypes.QUADCOPTER_X);
    
//    QC_AILERON(new PIDAileronStabilization(ArduinoPIDConstants.qcAileronPid), true, CraftTypes.QUADCOPTER_X),
//    QC_ELEVATOR(new PIDElevatorStabilization(ArduinoPIDConstants.qcElevatorPid), true, CraftTypes.QUADCOPTER_X),
//    QC_RATE_ELEVATOR(new PIDRateElevatorStabilization(ArduinoPIDConstants.qcElevatorRatePid), true, CraftTypes.QUADCOPTER_X),
//    QC_RATE_AILERON(new PIDRateAileronStabilization(ArduinoPIDConstants.qcAileronRatePid), true, CraftTypes.QUADCOPTER_X),
//    QC_RUDDER(new PIDRudderStabilization(ArduinoPIDConstants.qcRudderPid), true, CraftTypes.QUADCOPTER_X);
//    QC_RATE_RUDDER(new PIDRateRudderStabilization(ArduinoPIDConstants.qcAileronRatePid), true, CraftTypes.QUADCOPTER_X);
//    QC_THROTTLE(new PIDThrottleStabilization(ArduinoPIDConstants.qcThrottlePid), true, CraftTypes.QUADCOPTER_X),
//    QC_ALTITUDE(new PIDVerticalSpeedStabilization(ArduinoPIDConstants.qcVerticalSpeedPid), true, CraftTypes.QUADCOPTER_X);*/

  /*  AILERON(new PIDAileronStabilization(FGPIDConstants.planeAileronPid), true),
    ELEVATOR(new PIDElevatorStabilization(FGPIDConstants.planeElevatorPid), true),
    RUDDER(new PIDRudderStabilization(FGPIDConstants.rudderPid), true),
    THROTTLE(new PIDThrottleStabilization(FGPIDConstants.planeThrottlePid), true),
    ALTITUDE(new PIDVerticalSpeedStabilization(FGPIDConstants.verticalSpeedPid), true);
*/
	private PIDProcess pidProcess;
	private boolean activated;
    private CraftTypes craftType;

	PIDProcessList(PIDProcess pidProcess, boolean activated, CraftTypes craftType) {
		this.pidProcess = pidProcess;
		this.activated = activated;
        this.craftType = craftType;
	}

	public void execute(ActionProcess actionProcess, PilotSensor pilotSensor) {
		if (activated) {
			pidProcess.execute(actionProcess, pilotSensor);
		}
	}
	
	public PIDProcess getProcess(){
		return pidProcess;
	}

    public boolean isActivated() {
        return activated;
    }

    public static List<PIDProcessList> getPIDPRocessList(CraftTypes craftTypes) {
        List<PIDProcessList> pidProcessList = new ArrayList<PIDProcessList>();
        for (PIDProcessList pidProcess : PIDProcessList.values()) {
            if (craftTypes.equals(pidProcess.craftType) && pidProcess.activated) {
                pidProcessList.add(pidProcess);
            }
        }
        return pidProcessList;
    }
}
