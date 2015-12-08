package pilot.obss.com.autopilot.pid;

import java.util.ArrayList;
import java.util.List;

import pilot.obss.com.autopilot.pid.pidlist.PIDPlaneAileronStabilization;
import pilot.obss.com.autopilot.pid.pidlist.PIDPlaneElevatorStabilization;
import pilot.obss.com.autopilot.pid.pidlist.PIDQCBaroAltitudeStabilization;
import pilot.obss.com.autopilot.pid.pidlist.PIDQCBaroRateThrottleStabilization;
import pilot.obss.com.autopilot.pid.pidlist.PIDQCRangeAltitudeStabilization;
import pilot.obss.com.autopilot.pid.pidlist.PIDQCRangeRateThrottleStabilization;
import pilot.obss.com.autopilot.pid.pidlist.PIDQCRudderStabilization;
import pilot.obss.com.autopilot.sensor.PilotSensor;
import pilot.obss.com.autopilot.util.types.CraftTypes;

public enum PIDProcessList {
    PL_AILERON(new PIDPlaneAileronStabilization(PIDParameters.planeAileronPid), CraftTypes.PLANE),
    PL_ELEVATOR(new PIDPlaneElevatorStabilization(PIDParameters.planeElevatorPid), CraftTypes.PLANE),
    //PL_RUDDER(new PIDRudderStabilization(ArduinoPIDConstants.planeRudderPid), CraftTypes.PLANE),
    //PL_THROTTLE(new PIDThrottleStabilization(ArduinoPIDConstants.planeThrottlePid), CraftTypes.PLANE),
    //PL_ALTITUDE(new PIDVerticalSpeedStabilization(ArduinoPIDConstants.planeVerticalSpeedPid), CraftTypes.PLANE),

    DW_AILERON(new PIDPlaneAileronStabilization(PIDParameters.dwAileronPid), CraftTypes.DELTAWING),
    DW_ELEVATOR(new PIDPlaneElevatorStabilization(PIDParameters.dwElevatorPid), CraftTypes.DELTAWING),

    QC_RUDDER(new PIDQCRudderStabilization(PIDParameters.qcRudderPid), CraftTypes.QUADCOPTER_X),
    QC_BARO_ALTITIUDE(new PIDQCBaroAltitudeStabilization(PIDParameters.qcBaroAltitudePid), CraftTypes.QUADCOPTER_X),
    QC_RATE_BARO_ALTITIUDE(new PIDQCBaroRateThrottleStabilization(PIDParameters.qcRateBaroAltitudePid), CraftTypes.QUADCOPTER_X),
    QC_RANGE_ALTITIUDE(new PIDQCRangeAltitudeStabilization(PIDParameters.qcRangeAltitudePid), CraftTypes.QUADCOPTER_X),
    QC_RATE_RANGE_ALTITIUDE(new PIDQCRangeRateThrottleStabilization(PIDParameters.qcRateRangeAltitudePid), CraftTypes.QUADCOPTER_X);
    
//    QC_AILERON(new PIDAileronStabilization(ArduinoPIDConstants.qcAileronPid), CraftTypes.QUADCOPTER_X),
//    QC_ELEVATOR(new PIDElevatorStabilization(ArduinoPIDConstants.qcElevatorPid), CraftTypes.QUADCOPTER_X),
//    QC_RATE_ELEVATOR(new PIDRateElevatorStabilization(ArduinoPIDConstants.qcElevatorRatePid), CraftTypes.QUADCOPTER_X),
//    QC_RATE_AILERON(new PIDRateAileronStabilization(ArduinoPIDConstants.qcAileronRatePid), CraftTypes.QUADCOPTER_X),
//    QC_RATE_RUDDER(new PIDRateRudderStabilization(ArduinoPIDConstants.qcAileronRatePid), CraftTypes.QUADCOPTER_X);
//    QC_THROTTLE(new PIDThrottleStabilization(ArduinoPIDConstants.qcThrottlePid), CraftTypes.QUADCOPTER_X),
//    QC_ALTITUDE(new PIDVerticalSpeedStabilization(ArduinoPIDConstants.qcVerticalSpeedPid), CraftTypes.QUADCOPTER_X);*/
	
    private PIDProcess pidProcess;
	private CraftTypes craftType;

	PIDProcessList(PIDProcess pidProcess, CraftTypes craftType) {
		this.pidProcess = pidProcess;
		this.craftType = craftType;
	}

	public void execute(PilotSensor pilotSensor) {
		pidProcess.execute(pilotSensor);
	}

	public PIDProcess getProcess() {
		return pidProcess;
	}

	public static List<PIDProcessList> getPIDPRocessList(CraftTypes craftTypes) {
		List<PIDProcessList> pidProcessList = new ArrayList<PIDProcessList>();
		for (PIDProcessList pidProcess : PIDProcessList.values()) {
			if (craftTypes.equals(pidProcess.craftType)) {
				pidProcessList.add(pidProcess);
			}
		}
		return pidProcessList;
	}
}
