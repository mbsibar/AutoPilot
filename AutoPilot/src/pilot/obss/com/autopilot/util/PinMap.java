package pilot.obss.com.autopilot.util;

import pilot.obss.com.autopilot.util.constants.ScaleFactorConstants;

public enum PinMap {
	MOTOR1(22, ScaleFactorConstants.MOTOR1, PinMode.OUTPUT),
	MOTOR2(4, ScaleFactorConstants.MOTOR2, PinMode.OUTPUT),
	MOTOR3(17, ScaleFactorConstants.MOTOR3, PinMode.OUTPUT),
	MOTOR4(27, ScaleFactorConstants.MOTOR4, PinMode.OUTPUT),
	
	/*AILERON_OUT(4, ScaleFactorConstants.AILERON_OUT, PinMode.OUTPUT),
	ELEVATOR_OUT(17, ScaleFactorConstants.ELEVATOR_OUT, PinMode.OUTPUT),
	THROTTLE_OUT(27, ScaleFactorConstants.THROTTLE_OUT, PinMode.OUTPUT),
	RUDDER_OUT(22, ScaleFactorConstants.RUDDER_OUT, PinMode.OUTPUT),
	*/
	P(20, ScaleFactorConstants.P, PinMode.INPUT),
	I(6, ScaleFactorConstants.I, PinMode.INPUT),
	D(7, ScaleFactorConstants.D, PinMode.INPUT),
	
//	THROTTLE(26, ScaleFactorConstants.THROTTLE, PinMode.INPUT),
//	RUDDER(19, ScaleFactorConstants.RUDDER, PinMode.INPUT),
//	ELEVATOR(5, ScaleFactorConstants.ELEVATOR, PinMode.INPUT),
//	AILERON(21, ScaleFactorConstants.AILERON, PinMode.INPUT),
//	MODE(13, ScaleFactorConstants.MODE, PinMode.INPUT);
	
	THROTTLE(0, ScaleFactorConstants.THROTTLE, PinMode.INPUT),
	RUDDER(1, ScaleFactorConstants.RUDDER, PinMode.INPUT),
	ELEVATOR(2, ScaleFactorConstants.ELEVATOR, PinMode.INPUT),
	AILERON(3, ScaleFactorConstants.AILERON, PinMode.INPUT);
	
	private int pin;
	private ScaleFactorConstants type;
	private PinMode pinMode;
	
	PinMap(int pin, ScaleFactorConstants type, PinMode pinMode){
		this.pin = pin;
		this.type = type;
		this.pinMode = pinMode;
	}
	
	public int getPin() {
		return pin;
	}
	
	public PinMode getPinMode() {
		return pinMode;
	}
	
	public ScaleFactorConstants getType() {
		return type;
	}
	
}
