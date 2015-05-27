package pilot.obss.com.autopilot.util.types;

import java.util.ArrayList;
import java.util.List;

import pilot.obss.com.autopilot.commandExecution.PIDProcess;
import pilot.obss.com.autopilot.sensor.PilotSensor;
import pilot.obss.com.autopilot.util.ActionProcess;

/**
 * Created by burak on 19.3.2015.
 */
public class RateObject extends AlgorithmObject{
    private List<Float> integralList = new ArrayList<Float>();
    private Float derivative = 0f;
    private int integralIterator = 0;
    private int maxListSize = 0;
    private float preError = 0;
    private float minimumLimit = 0;
    private float maximumLimit = 0;
    private float p;
    private float i;
    private float d;
    private String name;

    public RateObject(String name, float p, float i, float d, int maxListSize, float minOutputLimit, float maxOutputLimit) {
        this.name = name;
        this.maxListSize = maxListSize;
        this.minimumLimit = minOutputLimit;
        this.maximumLimit = maxOutputLimit;
        this.p = p;
        this.i = i;
        this.d = d;
    }

    public void addIntegral(float increment) {
        if (integralList.size() == integralIterator) {
            integralList.add(increment);
        } else {
            integralList.set(integralIterator, increment);
        }
        integralIterator++;
        integralIterator = integralIterator % maxListSize;
    }

    public void setDerivative(float derivative) {
        this.derivative = derivative;
    }

    public float getDerivative() {
        return derivative;
    }

    public float getIntegralSum() {
        float sum = 0;
        for (float error : integralList) {
            sum += error;
        }
        return sum;
    }

    public void setPreError(float error) {
        this.preError = error;
    }

    public float getPreError() {
        return preError;
    }

    public float getTotal(float error) {
       return error*p;
    }

}
