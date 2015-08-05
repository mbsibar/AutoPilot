package pilot.obss.com.autopilot.brain.pilots;

import pilot.obss.com.android.util.ApplicationCollection;
import pilot.obss.com.autopilot.brain.AutoPilot;
import pilot.obss.com.autopilot.commandExecution.fgPlanePilot.PIDProcessList;
import pilot.obss.com.autopilot.sensor.PilotSensor;
import pilot.obss.com.autopilot.util.SingletonCollection;
import pilot.obss.com.autopilot.util.command.CommandObject;
import pilot.obss.com.autopilot.util.constants.QuadcopterConstants;
import pilot.obss.com.autopilot.util.types.CLIMBTYPE;
import pilot.obss.com.autopilot.util.types.CraftInformation;
import pilot.obss.com.autopilot.util.types.CraftTypes;
import pilot.obss.com.autopilot.util.types.GpsLocation;
import pilot.obss.com.autopilot.util.types.StabilizationType;
import pilot.obss.com.autopilot.util.types.TURN_TYPES;
import pilot.obss.com.autopilot.util.types.WayPoint;

public class QuadCopterPilot implements AutoPilot {
	private QuadcopterConstants planeConstants = new QuadcopterConstants();
	private CraftInformation craftInformation = CraftInformation.getInstance();;
 	private boolean completed = false;
	private WayPoint targetwayPoint = null;
    private boolean stabilizationStarted = false;

	@Override
	public void cycle() {
		updatePlaneStatistics();
		executeTarget();
		for (PIDProcessList processList : PIDProcessList.getPIDPRocessList(CraftTypes.QUADCOPTER_X)) {
			processList.execute(actionProcess, ApplicationCollection.getPilotSensor());
		}
        writeCraftData();
	}

    private void writeCraftData() {
    	ApplicationCollection.getPilotSensor().updateSensorList();
    }

    private void executeTarget() {
        CommandObject commandObject = CommandObject.getInstance();
        if (!commandObject.getMission().getWayPointList().isEmpty()) {
            commandObject.getMission().setCurrentPosition(craftInformation.getGpsLocation());
            double bearing = commandObject.getMission().getBearing(craftInformation.getGpsLocation());
            actionProcess.setHeadingDegree((float) bearing);
        }
    }

	private void updatePlaneStatistics() {
        PilotSensor pilotSensor = ApplicationCollection.getPilotSensor();
		craftInformation.setAirSpeed(pilotSensor.getAirSpeed());
        craftInformation.setAltitudeFt(pilotSensor.getAltituteFt());
		craftInformation.setGpsLocation(new GpsLocation(pilotSensor.getLatitude(), pilotSensor.getLongtitude(), craftInformation.getAltitudeFt()));
        craftInformation.setHeading(pilotSensor.getHeading());
        craftInformation.setRoll(pilotSensor.getRoll());
        craftInformation.setPitch(pilotSensor.getPitch());
        craftInformation.setPitchRate(pilotSensor.getPitchRate());
        craftInformation.setVerticalSpeed(pilotSensor.getVerticalSpeed());
		SingletonCollection.getUserInterface().updateCraftInfo(craftInformation);
	}

	@Override
	public void setStabilizationMode(StabilizationType stabilizationType) {
		if (StabilizationType.Stabilize.equals(stabilizationType)) {
			startStabilization();
		} else if (StabilizationType.Stop.equals(stabilizationType)) {
			stopStabilization();
		}
	}

	private void startStabilization() {
        CommandObject commandObject = CommandObject.getInstance();
		actionProcess.setPitchDegree(0f);
		actionProcess.setHeadingDegree(craftInformation.getHeading());
		actionProcess.setRollDegree(0f);
		commandObject.getStblCommand().setStabilizeAileron(true);
		commandObject.getStblCommand().setStabilizeElevator(true);
		commandObject.getStblCommand().setStabilizeRudder(true);
		commandObject.getStblCommand().setStabilizeAirSpeed(true);
        stabilizationStarted = true;
		cycle();
	}

	private void stopStabilization() {
        CommandObject commandObject = CommandObject.getInstance();
		commandObject.getStblCommand().setStabilizeAileron(false);
		commandObject.getStblCommand().setStabilizeElevator(false);
		commandObject.getStblCommand().setStabilizeRudder(false);
		commandObject.getStblCommand().setStabilizeAirSpeed(false);
		cycle();
	}

	@Override
	public void calculateActionProcess(TURN_TYPES turnType, float turnDegree) {
		if (TURN_TYPES.LEFT.equals(turnType)) {
            CommandObject commandObject = CommandObject.getInstance();
			actionProcess.setRollDegree(-turnDegree);
			actionProcess.setPitchDegree(turnDegree / planeConstants.getTurnPitchMultiplier());
			actionProcess.setHeadingDegree(0f);
			commandObject.getStblCommand().setStabilizeAileron(true);
			commandObject.getStblCommand().setStabilizeElevator(true);
			commandObject.getStblCommand().setStabilizeAirSpeed(true);
			commandObject.getStblCommand().setStabilizeRudder(false);
		} else if (TURN_TYPES.RIGHT.equals(turnType)) {
            CommandObject commandObject = CommandObject.getInstance();
			actionProcess.setRollDegree(turnDegree);
			actionProcess.setPitchDegree(turnDegree / planeConstants.getTurnPitchMultiplier());
			actionProcess.setHeadingDegree(0f);
			commandObject.getStblCommand().setStabilizeAileron(true);
			commandObject.getStblCommand().setStabilizeElevator(true);
			commandObject.getStblCommand().setStabilizeAirSpeed(true);
			commandObject.getStblCommand().setStabilizeRudder(false);
		} else if (TURN_TYPES.STABLE.equals(turnType)) {
            CommandObject commandObject = CommandObject.getInstance();
			actionProcess.setRollDegree(0f);
			actionProcess.setPitchDegree(0f);
			actionProcess.setHeadingDegree(craftInformation.getHeading());
			commandObject.getStblCommand().setStabilizeAileron(true);
			commandObject.getStblCommand().setStabilizeElevator(true);
			commandObject.getStblCommand().setStabilizeRudder(true);
			commandObject.getStblCommand().setStabilizeAirSpeed(true);
		}
	}

	@Override
	public void calculateActionProcess(CLIMBTYPE climbType, float climbDegree) {
		if (CLIMBTYPE.UP.equals(climbType)) {
//			actionProcess.setPitchDegree(climbDegree);
		} else if (CLIMBTYPE.DOWN.equals(climbType)) {
//			actionProcess.setPitchDegree(-climbDegree);
		} else if (CLIMBTYPE.STABLE.equals(climbType)) {
//			actionProcess.setPitchDegree(0f);
		}
	}

	@Override
	public void setTargetLocation(WayPoint targetLocation) {
		this.targetwayPoint  = targetLocation;
	}

}
