package pilot.obss.com.autopilot.util.types;

public class GpsLocation {
	private float longtitude;
	private float latitude;
	private double altitude;

	public GpsLocation(float latitude, float longtitude, double altitude) {
		this.latitude = latitude;
		this.longtitude = longtitude;
		this.altitude = altitude;
	}

	public float getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(float longtitude) {
		this.longtitude = longtitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public double getAltitude() {
		return altitude;
	}

	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}

}
