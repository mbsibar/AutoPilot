package pilot.obss.com.autopilot.util.command;

public class StabilizationCommandObject {
	private boolean stabilizeElevator = false;
	private boolean stabilizeAileron = false;
	private boolean stabilizeRudder = false;
	private boolean stabilizeAirSpeed = false;
	private boolean ROCLock = false;
	
	public StabilizationCommandObject() {

	}

	public void startAllStabilization() {
		setStabilizeAileron(true);
		setStabilizeElevator(true);
		setStabilizeRudder(true);
	}

	public void stopAllStabilization(){
		setStabilizeElevator(false);
		setStabilizeAileron(false);
		setStabilizeRudder(false);
	}

	public boolean isStabilizeElevator() {
		return stabilizeElevator;
	}

	public void setStabilizeElevator(boolean stabilizeElevator) {
		this.stabilizeElevator = stabilizeElevator;
	}

	public boolean isStabilizeAileron() {
		return stabilizeAileron;
	}

	public void setStabilizeAileron(boolean stabilizeAileron) {
		this.stabilizeAileron = stabilizeAileron;
	}

	public boolean isStabilizeRudder() {
		return stabilizeRudder;
	}

	public void setStabilizeRudder(boolean stabilizeRudder) {
		this.stabilizeRudder = stabilizeRudder;
	}

	public boolean isStabilizeAirSpeed() {
		return stabilizeAirSpeed;
	}

	public void setStabilizeAirSpeed(boolean stabilizeAirSpeed) {
		this.stabilizeAirSpeed = stabilizeAirSpeed;
	}

	public boolean isROCLock() {
		return ROCLock;
	}

	public void setROCLock(boolean rOCLock) {
		ROCLock = rOCLock;
	}

}
