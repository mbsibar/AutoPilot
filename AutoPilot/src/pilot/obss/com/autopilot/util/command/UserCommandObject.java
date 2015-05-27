package pilot.obss.com.autopilot.util.command;

public class UserCommandObject {
	private UserCommandType commandType;
	private Object value;

	public UserCommandType getCommandType() {
		return commandType;
	}

	public void setCommandType(UserCommandType commandType) {
		this.commandType = commandType;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
