package pilot.obss.com.autopilot.util.types;

import pilot.obss.com.autopilot.util.Mission;

public class CraftInformation {
	private static CraftInformation craftInformation;

	private float airSpeed;
	private GpsLocation gpsLocation;
	private Mission mission;
    private float heading;
    private float roll;
    private float pitch;
    private float rudder;
    private double pitchRate;
    private double rollRate;
    private double altitudeFt;
    private float verticalSpeed;
    private double rudderRate;
    private double gyroCalRoll;
    private double gyroCalPitch;
    private double gyroCalYaw;

    private float aileron = 90;
    private float elevator = 90;

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

    public float getHeading(){
        return heading;
    }

    public void setHeading(float heading){
        this.heading = heading;
    }

    public float getRoll(){
        return roll;
    }

    public void setRoll(float roll){
        this.roll = roll;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }
    
    public float getRudder() {
		return heading;
	}

	public void setRudder(float rudder) {
		this.rudder = rudder;
	}
	
	public double getPitchRate() {
		return pitchRate - gyroCalPitch;
	}

	public void setPitchRate(double pitchRate) {
		this.pitchRate = pitchRate;
	}
	
	public double getRudderRate() {
		return rudderRate - gyroCalYaw;
	}

	public void setRudderRate(double rudderRate) {
		this.rudderRate = rudderRate;
	}

	public double getRollRate() {
		return rollRate - gyroCalRoll;
	}

	public void setRollRate(double rollRate) {
		this.rollRate = rollRate;
	}

	public double getAltitudeFt() {
        return altitudeFt;
    }

    public void setAltitudeFt(double altitudeFt) {
        this.altitudeFt = altitudeFt;
    }

    public float getVerticalSpeed() {
        return verticalSpeed;
    }

    public void setVerticalSpeed(float verticalSpeed) {
        this.verticalSpeed = verticalSpeed;
    }

    private float getPwmValue(float motor) {
        if(motor > 1900){
            motor = 1900;
        }else if(motor < 1100) {
            motor = 1100;
        }
        return motor;
    }

    public void setAileron(float aileron){
        this.aileron = aileron;
    }

    public float getAileron(){
        return aileron;
    }

    public float getElevator() {
        return elevator;
    }

    public void setElevator(float elevator) {
        this.elevator = elevator;
    }

	public double getGyroCalRoll() {
		return gyroCalRoll;
	}

	public void setGyroCalRoll(double gyroCalRoll) {
		this.gyroCalRoll = gyroCalRoll;
	}

	public double getGyroCalPitch() {
		return gyroCalPitch;
	}

	public void setGyroCalPitch(double gyroCalPitch) {
		this.gyroCalPitch = gyroCalPitch;
	}

	public double getGyroCalYaw() {
		return gyroCalYaw;
	}

	public void setGyroCalYaw(double gyroCalYaw) {
		this.gyroCalYaw = gyroCalYaw;
	}
    
}
