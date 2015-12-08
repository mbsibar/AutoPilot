package pilot.obss.com.autopilot.util.command;

import pilot.obss.com.autopilot.util.Mission;

public class CommandObject {
	private StabilizationCommandObject stabilizationCommand = new StabilizationCommandObject();
	private UserCommandObject userCommand = new UserCommandObject();
	private Mission mission = new Mission();

	private static CommandObject commandObject;
	public static CommandObject getInstance() {
		if (commandObject == null) {
			commandObject = new CommandObject();
		}
		return commandObject;
	}

	private CommandObject() {

	}

	public StabilizationCommandObject getStblCommand() {
		return stabilizationCommand;
	}

	public UserCommandObject getUserCommand() {
		return userCommand;
	}

	public void newCommand(UserCommandType type) {
		newCommand(type, null);
	}

	public void newCommand(UserCommandType type, Object value) {
		userCommand = new UserCommandObject();
		userCommand.setCommandType(type);
		userCommand.setValue(value);
	}

	public void resetUserCommand() {
		userCommand = null;
	}

	public Mission getMission() {
		return mission;
	}

	public void setMission(Mission mission) {
		this.mission = mission;
	}
	

}
