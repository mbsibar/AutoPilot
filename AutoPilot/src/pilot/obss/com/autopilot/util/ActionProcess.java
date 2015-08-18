
package pilot.obss.com.autopilot.util;

public class ActionProcess {
	private Float rollDegree = 0f;
	private Float pitchDegree = 0f;
	private Float headingDegree = 0f;
	private Float airSpeedDegree = 80f;
	private Float rudderDegree = 0f;
	private Double altitudeFt = 600d;
	private Float verticalSpeed = 10f;
	private Float rudderConstantValue = 0f;
	private double hertz = 0;
	private boolean started = true;
	
	private Float pitchStickValue = 0f;
	private Float aileronStickValue = 0f;
	private Float rudderStickValue = 0f;

	public Float getRollDegree() {
		return rollDegree;
	}

	public void setRollDegree(Float rollDegree) {
		this.rollDegree = rollDegree;
	}
	
	public Float getPitchStickValue() {
		return pitchStickValue;
	}
	
	public Float getRudderDegree() {
		return rudderDegree;
	}

	public void setRudderDegree(Float rudderDegree) {
		this.rudderDegree = rudderDegree;
	}

	public void setPitchStickValue(Float pitchStickValue) {
		this.pitchStickValue = pitchStickValue;
	}

	public Float getAileronStickValue() {
		return aileronStickValue;
	}

	public void setAileronStickValue(Float aileronStickValue) {
		this.aileronStickValue = aileronStickValue;
	}
	
	public Float getRudderStickValue() {
		return rudderStickValue;
	}

	public void setRudderStickValue(Float rudderStickValue) {
		this.rudderStickValue = rudderStickValue;
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

	public Float getRudderConstantValue() {
		return rudderConstantValue;
	}

	public void setRudderConstantValue(Float rudderConstantValue) {
		this.rudderConstantValue = rudderConstantValue;
	}
	
}
