package pilot.obss.com.autopilot.util.types;

public class WayPoint {
	private GpsLocation gpsLocation;
	private boolean completed;

	public GpsLocation getGpsLocation() {
		return gpsLocation;
	}

	public void setGpsLocation(GpsLocation gpsLocation) {
		this.gpsLocation = gpsLocation;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

}
