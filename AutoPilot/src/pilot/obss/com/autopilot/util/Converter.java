package pilot.obss.com.autopilot.util;

import java.math.BigDecimal;

/**
 * Created by burak on 27.4.2015.
 */
public class Converter {

    public static float pwmToDegreeConverter(float pwm, float basePwm, float topPwm, float degreeBase, float degreeTop){
        float range = topPwm-basePwm;
        float difference = pwm-basePwm;
        float degreeRange = degreeTop - degreeBase;
        return degreeBase + ((degreeRange/range)*difference);
    }

    public static float degreeToPwmConverter(float degree, float degreeBase, float degreeTop, float basePwm, float topPwm){
        float range = degreeTop-degreeBase;
        float difference = degree-degreeBase;
        float pwmRange = topPwm - basePwm;
        return basePwm + ((pwmRange/range)*difference);
        /*return round(basePwm + ((pwmRange/range)*difference), 1);*/
    }

    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
}
