package pilot.obss.com.autopilot.util.command;

public class OrientationCommand {
	private float aileron;
	private float elevator;
	private float rudder;
	private float rudderRate;
	private float throttle;
	private float headingDegree;

	public float getAileron() {
		return aileron;
	}

	public void setAileron(float aileron) {
		this.aileron = aileron;
	}

	public float getElevator() {
		return elevator;
	}

	public void setElevator(float elevator) {
		this.elevator = elevator;
	}

	public float getRudder() {
		return rudder;
	}

	public void setRudder(float rudder) {
		this.rudder = rudder;
	}

	public float getThrottle() {
		return throttle;
	}

	public void setThrottle(float throttle) {
		this.throttle = throttle;
	}

	public float getRudderRate() {
		return rudderRate;
	}

	public void setRudderRate(float rudderRate) {
		this.rudderRate = rudderRate;
	}

	public float getHeadingDegree() {
		return headingDegree;
	}

	public void setHeadingDegree(float headingDegree) {
		this.headingDegree = headingDegree;
	}
	
}
