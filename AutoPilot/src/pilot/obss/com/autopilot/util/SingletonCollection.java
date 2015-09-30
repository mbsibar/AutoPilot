package pilot.obss.com.autopilot.util;

import pilot.obss.com.autopilot.brain.Navigator;
import pilot.obss.com.autopilot.commandExecution.OrientationCommand;
import pilot.obss.com.autopilot.util.types.UserInterface;

public class SingletonCollection {
    private static UserInterface userInterface;
    private static PIDController5 pidController;
    private static PIDTunings pidTunings = new PIDTunings();
    private static StickValues stickValues = new StickValues();
    private static OrientationCommand orientationCommand = new OrientationCommand();
    private static Navigator navigator = null;
    
    private static boolean started = false;

    public static void setUserInterface(UserInterface intf) {
        if (userInterface == null) {
            userInterface = intf;
        }
    }

    public static UserInterface getUserInterface() {
        return userInterface;
    }

    public static void setPidController(PIDController5 newController) {
        if (pidController == null) {
            pidController = newController;
        }
    }

    public static PIDController5 getPidController() {
        return pidController;
    }

    public static void setStarted(boolean bool) {
        started = bool;
    }

    public static boolean getStarted() {
        return started;
    }
    
    public static void setStickValues(StickValues newStickValues) {
        if (stickValues == null) {
        	stickValues = newStickValues;
        }
    }

    public static StickValues getStickValues() {
        return stickValues;
    }
    
    public static void setPidTunings(PIDTunings newPidTunings) {
        if (pidTunings == null) {
        	pidTunings = newPidTunings;
        }
    }

    public static PIDTunings getPidTunings() {
        return pidTunings;
    }
    
    public static void setOrientationCommand(OrientationCommand newOrientationCommand) {
        if (orientationCommand == null) {
        	orientationCommand = newOrientationCommand;
        }
    }

    public static OrientationCommand getOrientationCommand() {
        return orientationCommand;
    }
    
    public static void setNavigator(Navigator newNavigator) {
        if (navigator == null) {
        	navigator = newNavigator;
        }
    }

    public static Navigator getNavigator() {
        return navigator;
    }
}
