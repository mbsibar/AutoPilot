package pilot.obss.com.autopilot.sensor;

public interface PilotSensor {

	float getHeading();
   
    float getRoll();

	float getPitch();
	
	double getPitchRate();
	
	float getAirSpeed();
	
	double getAltituteFt();

	void setAileron(float aileronDegree);

	void setElevator(float elevatorDegree);

	void setRudder(float rudderDegree);
	
	void setThrottle(float throttle);

	float getVerticalSpeed();
	
	float getLongtitude();
	
	float getLatitude();

    void start();

    void updateSensorList();
}
