
package pilot.obss.com.autopilot.util;

public class ActionProcess {
	private Float rollDegree = 0f;
	private Float pitchDegree = 0f;
	private Float headingDegree = 0f;
	private Float airSpeedDegree = 80f;
	private Double altitudeFt = 600d;
	private Float pitchStickValue = 0f;
	private Float verticalSpeed = 10f;
	private double hertz = 0;
	private boolean started = true;

	public Float getRollDegree() {
		return rollDegree;
	}

	public void setRollDegree(Float rollDegree) {
		this.rollDegree = rollDegree;
	}
	
	public Float getPitchStickValue() {
		return pitchStickValue;
	}

	public void setPitchStickValue(Float pitchStickValue) {
		this.pitchStickValue = pitchStickValue;
	}

	public Float getPitchDegree() {
		return pitchDegree;
	}

	public void setPitchDegree(Float pitchDegree) {
		this.pitchDegree = pitchDegree;
	}

	public Float getHeadingDegree() {
		return headingDegree;
	}

	public void setHeadingDegree(Float headingDegree) {
		this.headingDegree = headingDegree;
	}

	public Float getAirSpeedDegree() {
		return airSpeedDegree;
	}

	public void setAirSpeedDegree(Float airSpeedDegree) {
		this.airSpeedDegree = airSpeedDegree;
	}

	public Double getAltitudeFt() {
		return altitudeFt;
	}

	public void setAltitudeFt(Double altitudeFt) {
		this.altitudeFt = altitudeFt;
	}

	public Float getVerticalSpeed() {
		return verticalSpeed;
	}

	public void setVerticalSpeed(Float verticalSpeed) {
		this.verticalSpeed = verticalSpeed;
	}

	public double getHertz() {
		return hertz;
	}

	public void setHertz(double hertz) {
		this.hertz = hertz;
	}

	public boolean isStarted() {
		return started;
	}

	public void setStarted(boolean started) {
		this.started = started;
	}
	
}
