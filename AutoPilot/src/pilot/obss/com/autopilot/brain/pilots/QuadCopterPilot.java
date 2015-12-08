package pilot.obss.com.autopilot.brain.pilots;

import pilot.obss.com.autopilot.modes.FlightModes;
import pilot.obss.com.autopilot.pid.PIDProcessList;
import pilot.obss.com.autopilot.util.types.CraftInformation;
import pilot.obss.com.autopilot.util.types.CraftTypes;
import pilot.obss.com.autopilot.util.types.PIDOutputValues;
import pilot.obss.com.util.ApplicationCollection;

public class QuadCopterPilot implements AutoPilotIntf {
	private CraftInformation craftInformation = CraftInformation.getInstance();;

	@Override
	public void cycle() {
		updateSensorList();
		updatePlaneStatistics();
		executeFlightMode();
		for (PIDProcessList processList : PIDProcessList.getPIDPRocessList(CraftTypes.QUADCOPTER_X)) {
			processList.execute(ApplicationCollection.getPilotSensor());
		}
	}

	private void executeFlightMode() {
		FlightModes.getFlightMode().execute();
	}

	private void updateSensorList() {
		ApplicationCollection.getPilotSensor().updateSensorList();
	}

	private void updatePlaneStatistics() {
		craftInformation.updateData();
		
		PIDOutputValues.resetMotorSpeeds();
	}

}
