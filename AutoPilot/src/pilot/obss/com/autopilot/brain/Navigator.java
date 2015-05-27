package pilot.obss.com.autopilot.brain;

import java.util.Date;

import pilot.obss.com.autopilot.util.SingletonCollection;
import pilot.obss.com.autopilot.util.command.CommandObject;
import pilot.obss.com.autopilot.util.command.UserCommandType;
import pilot.obss.com.autopilot.util.types.CLIMBTYPE;
import pilot.obss.com.autopilot.util.types.CraftInformation;
import pilot.obss.com.autopilot.util.types.StabilizationType;
import pilot.obss.com.autopilot.util.types.TURN_TYPES;

public class Navigator implements Runnable {
	private AutoPilot pilot;
	private CommandObject command;

	public Navigator(AutoPilot pilot) {
		this.pilot = pilot;
		this.command = CommandObject.getInstance();
        pilot.setStabilizationMode(StabilizationType.Stabilize);
		/*command.getMission().addNewLocation(new GpsLocation(37.59531615f, -122.34475315f, 1000d));
		command.getMission().addNewLocation(new GpsLocation(37.61360815f, -122.30733115f, 1000d));
		command.getMission().addNewLocation(new GpsLocation(37.59358315f, -122.28306915f, 1000d));*/
	}

	// PlanePilot'a stabilize, turn45DegreeLeft vs. gibi komutlar g�nder. Buras� navigator b�l�m�. Otopilot kendi i�ine bakacak. Navigator ne yapmas� gerekti�ini s�yleecek.
	@Override
	public void run() {
		try {
			while (true) {
                Date startDate = new Date();
				if (command.getUserCommand() != null) {
					executeUserCommand();
				}
				pilot.cycle();
				Date endDate = new Date();
                Double timeDiff = (endDate.getTime() - startDate.getTime()) * 0.001;
				pilot.actionProcess.setHertz(timeDiff);
                Thread.sleep(1);
			}
		} catch (Exception e) {
			SingletonCollection.getUserInterface().writeToTextView(e.getMessage());
		}


	}
    String getStackTrace(Exception e) {
        String message = e.getLocalizedMessage();
        for (StackTraceElement a : e.getStackTrace()) {
            message = message + a + "\n";
        }
        return message;
    }
	private void executeUserCommand() {
		if (UserCommandType.START_STABILIZATION.equals(command.getUserCommand().getCommandType())) {
			pilot.setStabilizationMode(StabilizationType.Stabilize);
		} else if (UserCommandType.STOP_STABILIZATION.equals(command.getUserCommand().getCommandType())) {
			pilot.setStabilizationMode(StabilizationType.Stop);
		} else if (UserCommandType.TURN.equals(command.getUserCommand().getCommandType())) {
			float turnDegree = (Float) command.getUserCommand().getValue();
			TURN_TYPES turnType = TURN_TYPES.LEFT;
			if (turnDegree > 0) {
				turnType = TURN_TYPES.RIGHT;
			}
			pilot.calculateActionProcess(turnType, turnDegree);
		} else if (UserCommandType.STOP_TURN.equals(command.getUserCommand().getCommandType())) {
			pilot.calculateActionProcess(TURN_TYPES.STABLE, 0f);
		} else if (UserCommandType.RESET.equals(command.getUserCommand().getCommandType())) {
			// TODO
		} else if (UserCommandType.UP.equals(command.getUserCommand().getCommandType())) {
			pilot.calculateActionProcess(CLIMBTYPE.UP, 7f);
		} else if (UserCommandType.DOWN.equals(command.getUserCommand().getCommandType())) {
			pilot.calculateActionProcess(CLIMBTYPE.DOWN, 7f);
		} else if (UserCommandType.PITCHSTABLE.equals(command.getUserCommand().getCommandType())) {
			pilot.calculateActionProcess(CLIMBTYPE.STABLE, 0);
		}else if (UserCommandType.ENABLEROC.equals(command.getUserCommand().getCommandType())) {
			command.getStblCommand().setROCLock(true);
		}else if (UserCommandType.DISABLEROC.equals(command.getUserCommand().getCommandType())) {
			command.getStblCommand().setROCLock(false);
		}else if (UserCommandType.START_MISSION.equals(command.getUserCommand().getCommandType())) {
			CraftInformation.getInstance().setMission(command.getMission());
			//pilot.getUserInterface().displayMission();

		}

		command.resetUserCommand();
	}
	
	

}
