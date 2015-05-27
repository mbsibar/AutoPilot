package pilot.obss.com.autopilot.util.types;

import pilot.obss.com.autopilot.brain.AutoPilot;
import pilot.obss.com.autopilot.brain.pilots.PlanePilot;
import pilot.obss.com.autopilot.brain.pilots.QuadCopterPilot;

/**
 * Created by burak on 4.4.2015.
 */
public enum CraftTypes {
    PLANE(new PlanePilot()),
    DELTAWING(new PlanePilot()),
    QUADCOPTER_X(new QuadCopterPilot()),
    FG_PLANE(null);

    private AutoPilot autoPilot;

    CraftTypes(AutoPilot autoPilot){
        this.autoPilot = autoPilot;
    }
    
    public AutoPilot getAutoPilot(){
    	return autoPilot;
    }
}
