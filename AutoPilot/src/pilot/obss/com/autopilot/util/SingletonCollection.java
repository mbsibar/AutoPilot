package pilot.obss.com.autopilot.util;

import pilot.obss.com.autopilot.util.types.PIDComClass;
import pilot.obss.com.autopilot.util.types.UserInterface;

public class SingletonCollection {
    private static UserInterface userInterface;
    private static PIDController pidController;
    private static PIDComClass pid = new PIDComClass();
    private static boolean started = false;

    public static void setUserInterface(UserInterface intf) {
        if (userInterface == null) {
            userInterface = intf;
        }
    }

    public static UserInterface getUserInterface() {
        return userInterface;
    }

    public static void setPidController(PIDController newController) {
        if (pidController == null) {
            pidController = newController;
        }
    }

    public static PIDController getPidController() {
        return pidController;
    }

    public static void setPIDObject(PIDComClass newPidObject) {
        if (pid == null) {
            pid = newPidObject;
        }
    }

    public static PIDComClass getPIDObject() {
        return pid;
    }

    public static void setStarted(boolean bool) {
        started = bool;
    }

    public static boolean getStarted() {
        return started;
    }
}
