package pilot.obss.com.autopilot.util.types;

import pilot.obss.com.autopilot.sensor.PilotSensor;
import pilot.obss.com.autopilot.util.Mission;
import pilot.obss.com.util.ApplicationCollection;

public class CraftInformation {
	private static CraftInformation craftInformation;

	private GpsLocation gpsLocation;
	private Mission mission;
	private AHRSData ahrsData = new AHRSData();
	private FlightStatus flightStatus = FlightStatus.INITIALIZED;
	
	private double sonarAltitude;
	private double baroAltitude;
	private float verticalSpeed;
	private float gravity;
	private float airSpeed;

	private CraftInformation() {

	}

	public static CraftInformation getInstance() {
		if (craftInformation == null) {
			craftInformation = new CraftInformation();
		}
		return craftInformation;
	}

	public float getAirSpeed() {
		return airSpeed;
	}

	public void setAirSpeed(float airSpeed) {
		this.airSpeed = airSpeed;
	}

	public GpsLocation getGpsLocation() {
		// return gpsLocation;
		gpsLocation.setAltitude(10);
		gpsLocation.setLatitude(40.919210f);
		gpsLocation.setLongtitude(29.315685f);
		return gpsLocation;
	}

	public void setGpsLocation(GpsLocation gpsLocation) {
		this.gpsLocation = gpsLocation;
	}

	public void setMission(Mission mission) {
		this.mission = mission;
	}

	public Mission getMission() {
		return mission;
	}

	public AHRSData getAHRSData() {
		return ahrsData;
	}

	public double getBaroAltitude() {
		return baroAltitude;
	}
	
	public double getSonarAltitude() {
		return sonarAltitude;
	}

	public void setBaroAltitude(double baroAltitude) {
		this.baroAltitude = baroAltitude;
	}
	
	public void setSonarAltitude(double sonarAltitude) {
		this.sonarAltitude = sonarAltitude;
	}

	public float getVerticalSpeed() {
		return verticalSpeed;
	}

	public void setVerticalSpeed(float verticalSpeed) {
		this.verticalSpeed = verticalSpeed;
	}

	public float getGravity() {
		return gravity;
	}

	public void setGravity(float gravity) {
		this.gravity = gravity;
	}

	public FlightStatus getFlightStatus() {
		return flightStatus;
	}

	public void setFlightStatus(FlightStatus flightStatus) {
		this.flightStatus = flightStatus;
	}
	
	public void updateData() {
		PilotSensor pilotSensor = ApplicationCollection.getPilotSensor();
		airSpeed = pilotSensor.getAirSpeed();
		baroAltitude = pilotSensor.getAltituteFt();
		gpsLocation = new GpsLocation(pilotSensor.getLatitude(), pilotSensor.getLongtitude(), craftInformation.getBaroAltitude());
		ahrsData.heading = pilotSensor.getHeading();
		ahrsData.roll = pilotSensor.getRoll();
		ahrsData.pitch = pilotSensor.getPitch();
		ahrsData.setPitchRate(pilotSensor.getPitchRate());
		ahrsData.setYawRate(pilotSensor.getYawRate());
		ahrsData.setRollRate(pilotSensor.getRollRate());
		verticalSpeed = pilotSensor.getVerticalSpeed();
		gravity = pilotSensor.getGravity();
	}

}
