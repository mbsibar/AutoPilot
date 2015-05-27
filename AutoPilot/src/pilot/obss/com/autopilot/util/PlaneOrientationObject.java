package pilot.obss.com.autopilot.util;

public class PlaneOrientationObject {
	private float rollDegree = 0;
	private float pitchDegree = 0;
	private float rudderDegree = 0;

	public float getRollDegree() {
		return rollDegree;
	}

	public void setRollDegree(float rollDegree) {
		this.rollDegree = rollDegree;
	}

	public float getPitchDegree() {
		return pitchDegree;
	}

	public void setPitchDegree(float pitchDegree) {
		this.pitchDegree = pitchDegree;
	}

	public float getRudderDegree() {
		return rudderDegree;
	}

	public void setRudderDegree(float rudderDegree) {
		this.rudderDegree = rudderDegree;
	}
	
	public void changeRollDegree(float increaseDegree){
		rollDegree += increaseDegree;
	}
	
	public void changePitchDegree(float increaseDegree){
		pitchDegree += increaseDegree;
	}
	
	public void changeRudderDegree(float increaseDegree){
		rudderDegree += increaseDegree;
	}

}
